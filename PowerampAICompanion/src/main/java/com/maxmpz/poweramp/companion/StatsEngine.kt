package com.maxmpz.poweramp.companion

import android.content.Context
import android.net.Uri
import android.util.Log
import com.maxmpz.poweramp.companion.db.ScrobbleDatabase
import com.maxmpz.poweramp.companion.db.ScrobbleEntity
import com.maxmpz.poweramp.player.PowerampAPI
import com.maxmpz.poweramp.player.TableDefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.Calendar

class StatsEngine(private val context: Context) {
    private val db = ScrobbleDatabase.getDatabase(context).scrobbleDao()

    enum class ItemType {
        TRACK,
        ARTIST,
        ALBUM,
        GENRE,
        TIME
    }

    data class StatItem(
        val title: String,
        val subtitle: String,
        val album: String = "",
        val playCount: Int,
        val firstScrobbleTime: Long = 0,
        val type: ItemType,
        var pampId: Long = -1L,
        var albumArtUri: Uri? = null,
        var artistId: Long = -1L,
        var albumId: Long = -1L,
        var matchQuality: Int = 0 // 0: none, 1: normalized, 2: exact
    )

    enum class TimeRange {
        LAST_24_HOURS,
        LAST_7_DAYS,
        LAST_30_DAYS,
        LAST_60_DAYS,
        LAST_180_DAYS,
        LAST_365_DAYS,
        ALL_TIME
    }

    private fun getRawColName(col: String): String {
        val dot = col.indexOf('.')
        if (dot >= 0 && dot + 1 < col.length) {
            return col.substring(dot + 1)
        }
        return col
    }

    private fun getAlbumArtUri(type: String, id: Long): Uri {
        return PowerampAPI.AA_ROOT_URI.buildUpon()
            .appendEncodedPath(type)
            .appendEncodedPath(id.toString())
            .build()
    }

    suspend fun getTopTracks(range: TimeRange, limit: Int = 50, timeMachineYear: Int? = null): List<StatItem> {
        return withContext(Dispatchers.IO) {
            val allScrobbles = if (timeMachineYear != null) {
                getScrobblesForTimeMachine(range, timeMachineYear)
            } else {
                getMergedScrobbles(range)
            }

            // Group by lowercased Artist and Title to merge scrobbles from different apps/sources
            val countMap = mutableMapOf<Pair<String, String>, Triple<Int, Long, String>>() // (ArtistKey, TitleKey) -> (Count, FirstTime, Album)
            val displayNames = mutableMapOf<Pair<String, String>, Pair<String, String>>() // (ArtistKey, TitleKey) -> (DisplayArtist, DisplayTitle)

            for (s in allScrobbles) {
                val key = Pair(s.artist.lowercase().trim(), s.title.lowercase().trim())
                val cur = countMap[key]
                
                if (!displayNames.containsKey(key)) {
                    displayNames[key] = Pair(s.artist, s.title)
                }

                if (cur == null) {
                    countMap[key] = Triple(1, s.timestamp, s.album)
                } else {
                    val bestAlbum = if (cur.third.isBlank() && s.album.isNotBlank()) s.album else cur.third
                    countMap[key] = Triple(cur.first + 1, minOf(cur.second, s.timestamp), bestAlbum)
                }
            }

            val items = countMap.entries
                .sortedByDescending { it.value.first }
                .take(limit)
                .map {
                    val names = displayNames[it.key]!!
                    StatItem(
                        title = names.second,
                        subtitle = names.first,
                        album = it.value.third,
                        playCount = it.value.first,
                        firstScrobbleTime = it.value.second,
                        type = ItemType.TRACK
                    )
                }

            fun normalizeFast(s: String): String {
                if (s.isEmpty()) return ""
                val sb = StringBuilder()
                var inParen = 0
                for (char in s.lowercase()) {
                    if (char == '(' || char == '[') inParen++
                    else if ((char == ')' || char == ']') && inParen > 0) inParen--
                    else if (inParen == 0 && ((char in 'a'..'z') || (char in '0'..'9'))) {
                        sb.append(char)
                    }
                }
                return sb.toString().trim()
            }

            val normalizedMap = items.groupBy { normalizeFast(it.title) }

            try {
                val filesUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build()
                val projection = arrayOf(
                    TableDefs.Files._ID, 
                    TableDefs.Files.ALBUM_ID, 
                    TableDefs.Files.ARTIST_TAG, 
                    TableDefs.Files.TITLE_TAG, 
                    TableDefs.Files.NAME,
                    "artist"
                )

                context.contentResolver.query(filesUri, projection, null, null, null)?.use { c ->
                    val idCol = c.getColumnIndex(getRawColName(TableDefs.Files._ID))
                    val albumIdCol = c.getColumnIndex(getRawColName(TableDefs.Files.ALBUM_ID))
                    val titleCol = c.getColumnIndex(getRawColName(TableDefs.Files.TITLE_TAG))
                    val artistTagCol = c.getColumnIndex(getRawColName(TableDefs.Files.ARTIST_TAG))
                    val nameCol = c.getColumnIndex(getRawColName(TableDefs.Files.NAME))
                    val artistCol = c.getColumnIndex("artist")

                    if (idCol != -1) {
                        while (c.moveToNext()) {
                            val pTitle = if (titleCol >= 0) c.getString(titleCol) else null
                            val pName = if (nameCol >= 0) c.getString(nameCol) else ""
                            val displayTitle = if (!pTitle.isNullOrBlank()) pTitle else pName.substringBeforeLast(".")
                            val pTitleNorm = normalizeFast(displayTitle)
                            val candidates = normalizedMap[pTitleNorm] ?: continue
                            val id = c.getLong(idCol)
                            val pArtistTag = if (artistTagCol >= 0) c.getString(artistTagCol) ?: "" else ""
                            val pArtistName = if (artistCol >= 0) c.getString(artistCol) ?: "" else ""
                            val pArtist = pArtistTag.ifEmpty { pArtistName }

                            for (stat in candidates) {
                                if (stat.matchQuality == 2) continue
                                
                                val isExact = stat.subtitle.equals(pArtist, ignoreCase = true) &&
                                              stat.title.equals(displayTitle, ignoreCase = true)
                                
                                if (isExact) {
                                    stat.pampId = id
                                    stat.albumArtUri = getAlbumArtUri("files", id)
                                    stat.matchQuality = 2
                                } else if (stat.matchQuality == 0) {
                                    val sArtistNorm = normalizeFast(stat.subtitle)
                                    val pArtistNorm = normalizeFast(pArtist)
                                    if (sArtistNorm == pArtistNorm || sArtistNorm.isBlank() || pArtistNorm.isBlank()) {
                                        stat.pampId = id
                                        stat.albumArtUri = getAlbumArtUri("files", id)
                                        stat.matchQuality = 1
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("StatsDEBUG", "Error in getTopTracks matching", e)
            }
            items
        }
    }

    suspend fun getTopArtists(range: TimeRange, limit: Int = 50, timeMachineYear: Int? = null): List<StatItem> {
        return withContext(Dispatchers.IO) {
            val allScrobbles = if (timeMachineYear != null) {
                getScrobblesForTimeMachine(range, timeMachineYear)
            } else {
                getMergedScrobbles(range)
            }
            val countMap = mutableMapOf<String, Int>()
            val displayNames = mutableMapOf<String, String>()
            val splitRegex = Regex("(?i)\\s+(?:feat\\.|ft\\.|&|vs\\.)\\s+|,\\s+")
            for (s in allScrobbles) {
                val artists = s.artist.split(splitRegex).map { it.trim() }.filter { it.isNotEmpty() }
                for (artist in artists) {
                    val key = artist.lowercase()
                    if (!displayNames.containsKey(key)) displayNames[key] = artist
                    countMap[key] = (countMap[key] ?: 0) + 1
                }
            }
            val items = countMap.entries
                .sortedByDescending { it.value }
                .take(limit)
                .map { StatItem(title = displayNames[it.key]!!, subtitle = "${it.value} scrobbles", playCount = it.value, type = ItemType.ARTIST) }

            try {
                val artistsUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("artists").build()
                val projection = arrayOf(TableDefs.Artists._ID, "artist")
                context.contentResolver.query(artistsUri, projection, null, null, null)?.use { c ->
                    val idCol = c.getColumnIndexOrThrow("_id")
                    val artistCol = c.getColumnIndex("artist")
                    while (c.moveToNext()) {
                        val pArtist = if (artistCol >= 0) c.getString(artistCol) else ""
                        for (item in items) {
                            if (item.artistId == -1L && item.title.equals(pArtist, ignoreCase = true)) {
                                item.artistId = c.getLong(idCol)
                                item.pampId = item.artistId
                                item.albumArtUri = getAlbumArtUri("artists", item.artistId)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("StatsDEBUG", "Error matching artists", e)
            }
            items
        }
    }

    suspend fun getTopAlbums(range: TimeRange, limit: Int = 50, timeMachineYear: Int? = null): List<StatItem> {
        return withContext(Dispatchers.IO) {
            val allScrobbles = if (timeMachineYear != null) {
                getScrobblesForTimeMachine(range, timeMachineYear)
            } else {
                getMergedScrobbles(range)
            }
            val countMap = mutableMapOf<Pair<String, String>, Int>()
            val displayNames = mutableMapOf<Pair<String, String>, Pair<String, String>>()
            for (s in allScrobbles) {
                if (s.album.isNotBlank()) {
                    val key = Pair(s.album.lowercase().trim(), s.artist.lowercase().trim())
                    if (!displayNames.containsKey(key)) displayNames[key] = Pair(s.album, s.artist)
                    countMap[key] = (countMap[key] ?: 0) + 1
                }
            }
            val items = countMap.entries
                .sortedByDescending { it.value }
                .take(limit)
                .map {
                    val names = displayNames[it.key]!!
                    StatItem(title = names.first, subtitle = names.second, playCount = it.value, type = ItemType.ALBUM)
                }

            try {
                val albumsUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("albums").build()
                val projection = arrayOf(TableDefs.Albums._ID, "album", "artist")
                context.contentResolver.query(albumsUri, projection, null, null, null)?.use { c ->
                    val idCol = c.getColumnIndexOrThrow("_id")
                    val albumCol = c.getColumnIndex("album")
                    val artistCol = c.getColumnIndex("artist")
                    while (c.moveToNext()) {
                        val pAlbum = if (albumCol >= 0) c.getString(albumCol) else ""
                        val pArtist = if (artistCol >= 0) c.getString(artistCol) else ""
                        for (item in items) {
                            if (item.albumId == -1L && item.title.equals(pAlbum, ignoreCase = true) && 
                                item.subtitle.equals(pArtist, ignoreCase = true)) {
                                item.albumId = c.getLong(idCol)
                                item.pampId = item.albumId
                                item.albumArtUri = getAlbumArtUri("albums", item.albumId)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("StatsDEBUG", "Error matching albums", e)
            }
            items
        }
    }

    suspend fun getHourlyDistribution(range: TimeRange, timeMachineYear: Int? = null): Map<Int, Int> {
        return withContext(Dispatchers.IO) {
            val scrobbles = if (timeMachineYear != null) {
                getScrobblesForTimeMachine(range, timeMachineYear)
            } else {
                getMergedScrobbles(range)
            }
            val dist = mutableMapOf<Int, Int>()
            val cal = Calendar.getInstance()
            for (s in scrobbles) {
                cal.timeInMillis = s.timestamp
                val hour = cal.get(Calendar.HOUR_OF_DAY)
                dist[hour] = (dist[hour] ?: 0) + 1
            }
            dist
        }
    }

    suspend fun fetchTopTracksForTag(tag: String, apiKey: String): List<Pair<String, String>> {
        return getGlobalTopTracksForTag(tag, apiKey)
    }

    suspend fun getGlobalTopTracksForTag(tag: String, apiKey: String): List<Pair<String, String>> {
        val results = mutableListOf<Pair<String, String>>()
        try {
            val encodedTag = Uri.encode(tag)
            val url = URL("https://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&tag=$encodedTag&api_key=$apiKey&format=json&limit=100")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            if (connection.responseCode == 200) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val json = JSONObject(response)
                val tracks = json.optJSONObject("tracks")?.optJSONArray("track")
                if (tracks != null) {
                    for (i in 0 until tracks.length()) {
                        val trackObj = tracks.getJSONObject(i)
                        val trackName = trackObj.optString("name", "")
                        val artistName = trackObj.optJSONObject("artist")?.optString("name", "") ?: ""
                        if (trackName.isNotBlank() && artistName.isNotBlank()) {
                            results.add(Pair(artistName, trackName))
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("StatsEngine", "Error globally", e)
        }
        return results
    }

    suspend fun getMergedScrobbles(range: TimeRange): List<ScrobbleEntity> {
        val startTime = when (range) {
            TimeRange.LAST_24_HOURS -> getTimestampDaysAgo(1)
            TimeRange.LAST_7_DAYS -> getTimestampDaysAgo(7)
            TimeRange.LAST_30_DAYS -> getTimestampDaysAgo(30)
            TimeRange.LAST_60_DAYS -> getTimestampDaysAgo(60)
            TimeRange.LAST_180_DAYS -> getTimestampDaysAgo(180)
            TimeRange.LAST_365_DAYS -> getTimestampDaysAgo(365)
            TimeRange.ALL_TIME -> 0L
        }
        return db.getScrobblesSince(startTime)
    }

    private suspend fun getScrobblesForTimeMachine(range: TimeRange, yearsAgo: Int): List<ScrobbleEntity> {
        val calendar = Calendar.getInstance()
        
        // 1. Set endTime to "now" shifted back by N years
        calendar.add(Calendar.YEAR, -yearsAgo)
        val end = calendar.timeInMillis
        
        // 2. Set startTime based on range, relative to the shifted endTime
        val start = when (range) {
            TimeRange.LAST_24_HOURS -> {
                calendar.add(Calendar.HOUR_OF_DAY, -24)
                calendar.timeInMillis
            }
            TimeRange.LAST_7_DAYS -> {
                calendar.add(Calendar.DAY_OF_YEAR, -7)
                calendar.timeInMillis
            }
            TimeRange.LAST_30_DAYS -> {
                calendar.add(Calendar.DAY_OF_YEAR, -30)
                calendar.timeInMillis
            }
            TimeRange.LAST_60_DAYS -> {
                calendar.add(Calendar.DAY_OF_YEAR, -60)
                calendar.timeInMillis
            }
            TimeRange.LAST_180_DAYS -> {
                calendar.add(Calendar.DAY_OF_YEAR, -180)
                calendar.timeInMillis
            }
            TimeRange.LAST_365_DAYS -> {
                calendar.add(Calendar.DAY_OF_YEAR, -365)
                calendar.timeInMillis
            }
            TimeRange.ALL_TIME -> 0L
        }
        
        Log.d("StatsEngine", "Time Machine Shifted: range=$range, yearsAgo=$yearsAgo -> targetRange=$start to $end")
        return db.getScrobblesInRange(start, end)
    }

    suspend fun getTracksOnThisDay(yearsAgo: Int): List<StatItem> {
        val cal = Calendar.getInstance()
        val currentDayOfYear = cal.get(Calendar.DAY_OF_YEAR)
        
        val yearScrobbles = getScrobblesForTimeMachine(TimeRange.ALL_TIME, yearsAgo)
        val onThisDayScrobbles = yearScrobbles.filter { s ->
            cal.timeInMillis = s.timestamp
            cal.get(Calendar.DAY_OF_YEAR) == currentDayOfYear
        }

        return onThisDayScrobbles.map { s ->
            StatItem(
                title = s.title,
                subtitle = s.artist,
                album = s.album,
                playCount = 1,
                firstScrobbleTime = s.timestamp,
                type = ItemType.TRACK,
                pampId = s.trackId,
                albumArtUri = getAlbumArtUri("files", s.trackId)
            )
        }
    }


    private fun getTimestampDaysAgo(days: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -days)
        return calendar.timeInMillis
    }
}
