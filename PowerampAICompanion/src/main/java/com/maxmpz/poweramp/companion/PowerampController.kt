package com.maxmpz.poweramp.companion

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.maxmpz.poweramp.player.PowerampAPI
import com.maxmpz.poweramp.player.PowerampAPIHelper
import com.maxmpz.poweramp.player.TableDefs
import org.json.JSONArray
import org.json.JSONObject

data class QueueStats(val playedCount: Int, val totalCount: Int, val totalDurationMs: Long, val remainingDurationMs: Long)

class PowerampController(private val context: Context) {
    var currentQueueId: Long = -1L

    private val mainHandler = Handler(Looper.getMainLooper())
    private val random = java.util.Random()

    init {
        showToast("PowerampController v3.7 initialized")
        dumpQueueToLog()
    }

    fun playTrack(trackId: Long) {
        Log.d("StatsDEBUG", "PowerampController: playTrack called for trackId: $trackId")
        val trackUri = PowerampAPI.ROOT_URI.buildUpon()
            .appendEncodedPath("files")
            .appendEncodedPath(trackId.toString())
            .build()
        val playIntent = Intent(PowerampAPI.ACTION_API_COMMAND).apply {
            component = android.content.ComponentName(
                PowerampAPI.PACKAGE_NAME, PowerampAPI.API_RECEIVER_NAME
            )
            putExtra(PowerampAPI.EXTRA_COMMAND, PowerampAPI.Commands.OPEN_TO_PLAY)
            data = trackUri
        }
        try {
            Log.d("StatsDEBUG", "PowerampController: Sending broadcast intent: $playIntent with data: ${playIntent.data}")
            context.sendBroadcast(playIntent)
        } catch (e: Exception) {
            Log.e("StatsDEBUG", "PowerampController: Failed to send broadcast for playTrack", e)
        }
    }

    /**
     * Helper to process a track cursor and return a list of PowerampTrack objects.
     */
    private fun processTrackCursor(cursor: Cursor?): List<PowerampTrack> {
        val tracks = mutableListOf<PowerampTrack>()
        cursor?.use { c ->
            val idCol = c.getColumnIndex(TableDefs.Files._ID)
            val titleCol = c.getColumnIndex(TableDefs.Files.TITLE_TAG)
            val artistCol = c.getColumnIndex("artist")
            val durCol = c.getColumnIndex(TableDefs.Files.DURATION)
            val nameCol = c.getColumnIndex(TableDefs.Files.NAME)
            val artistTagCol = c.getColumnIndex("artist_tag")
            val playedTimesCol = c.getColumnIndex("played_times")

            while (c.moveToNext()) {
                val id = if (idCol != -1) c.getLong(idCol) else -1L
                val title = if (titleCol != -1) c.getString(titleCol) ?: "" else ""
                val name = if (nameCol != -1) c.getString(nameCol) ?: "" else ""
                val artistTag = if (artistTagCol != -1) c.getString(artistTagCol) ?: "" else ""
                val artistName = if (artistCol != -1) c.getString(artistCol) ?: "" else ""
                val artist = artistTag.ifEmpty { artistName }
                val duration = if (durCol != -1) c.getLong(durCol) else 0L
                val playedTimes = if (playedTimesCol != -1) c.getInt(playedTimesCol) else 0
                val albumArtUri = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(id.toString()).build()

                tracks.add(PowerampTrack(id, title.ifEmpty { name }, artist, duration, playedTimes, albumArtUri))
            }
        }
        return tracks
    }


    fun findTracks(recommendedTracks: List<Pair<String, String>>, onLog: ((String) -> Unit)? = null): List<PowerampTrack> {
        val foundTracks = mutableSetOf<PowerampTrack>()
        val filesUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build()
        val projection = arrayOf(
            TableDefs.Files._ID,
            TableDefs.Files.TITLE_TAG,
            TableDefs.Files.NAME,
            TableDefs.Files.ALBUM_ID,
            TableDefs.Files.DURATION,
            "artist" // Joined column from artists table
        )

        val keywordSearchToken = recommendedTracks.find { it.first.startsWith("MAGIC_TOKEN_KEYWORD_SEARCH|") }
        val isNewTracksRequest = recommendedTracks.any { it.first == "MAGIC_TOKEN_NEW_TRACKS" }
        val isUnderratedRequest = recommendedTracks.any { it.first == "MAGIC_TOKEN_UNDERRATED_TRACKS" }
        val isForgottenFavoritesRequest = recommendedTracks.any { it.first == "MAGIC_TOKEN_FORGOTTEN_FAVORITES" }
        val isFallbackRequest = recommendedTracks.any { it.first == "MAGIC_TOKEN_FALLBACK" }
        
        // ListenBrainz Sonic Tokens
        val sonicHighEnergyRequest = recommendedTracks.any { it.first == "MAGIC_TOKEN_SONIC_HIGH_ENERGY" }
        val sonicLowEnergyRequest = recommendedTracks.any { it.first == "MAGIC_TOKEN_SONIC_LOW_ENERGY" }
        val sonicPartyRequest = recommendedTracks.any { it.first == "MAGIC_TOKEN_SONIC_PARTY" }
        val sonicEveningRequest = recommendedTracks.any { it.first == "MAGIC_TOKEN_SONIC_RELAXED_EVENING" }
        val sonicMoodHappyRequest = recommendedTracks.any { it.first == "MAGIC_TOKEN_SONIC_MOOD_HAPPY" }
        val sonicMoodSadRequest = recommendedTracks.any { it.first == "MAGIC_TOKEN_SONIC_MOOD_SAD" }
        val sonicBpmToken = recommendedTracks.find { it.first.startsWith("MAGIC_TOKEN_SONIC_BPM_") && !it.first.startsWith("MAGIC_TOKEN_SONIC_BPM_RANGE") }
        val sonicBpmRangeToken = recommendedTracks.find { it.first.startsWith("MAGIC_TOKEN_SONIC_BPM_RANGE|") }
        val artistMatchToken = recommendedTracks.find { it.first.startsWith("MAGIC_TOKEN_ARTIST_MATCH|") }

        if (keywordSearchToken != null || isNewTracksRequest || isUnderratedRequest || isForgottenFavoritesRequest || isFallbackRequest || 
            sonicHighEnergyRequest || sonicLowEnergyRequest || sonicPartyRequest || sonicEveningRequest || 
            sonicMoodHappyRequest || sonicMoodSadRequest || sonicBpmToken != null || sonicBpmRangeToken != null || artistMatchToken != null) {
            try {
                if (keywordSearchToken != null) {
                    val keywordsStr = keywordSearchToken.first.substringAfter("MAGIC_TOKEN_KEYWORD_SEARCH|")
                    val keywords = keywordsStr.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                    
                    val selectionBuilder = StringBuilder("(")
                    val selectionArgs = mutableListOf<String>()
                    
                    keywords.forEachIndexed { index, k ->
                        if (index > 0) selectionBuilder.append(" OR ")
                        selectionBuilder.append("(${TableDefs.Files.TITLE_TAG} LIKE ? OR album LIKE ?)")
                        selectionArgs.add("%$k%")
                        selectionArgs.add("%$k%")
                    }
                    selectionBuilder.append(")")
                    
                    val cursor = context.contentResolver.query(
                        filesUri, projection, selectionBuilder.toString(), selectionArgs.toTypedArray(), "played_times DESC LIMIT 100"
                    )
                    cursor?.use { c ->
                        val idColumn = c.getColumnIndexOrThrow("_id")
                        val artistTagColumn = c.getColumnIndex("artist_tag")
                        val artistNameColumn = c.getColumnIndex("artist")
                        val titleColumn = c.getColumnIndex("title_tag")
                        val nameColumn = c.getColumnIndex("name")
                        val durColumn = c.getColumnIndex("duration")
                        while (c.moveToNext()) {
                            val id = c.getLong(idColumn)
                            val artistTag = if (artistTagColumn >= 0) c.getString(artistTagColumn) ?: "" else ""
                            val artistName = if (artistNameColumn >= 0) c.getString(artistNameColumn) ?: "" else ""
                            val artist = artistTag.ifEmpty { artistName }
                            val title = if (titleColumn >= 0) c.getString(titleColumn) ?: "" else ""
                            val name = if (nameColumn >= 0) c.getString(nameColumn) ?: "" else ""
                            val duration = if (durColumn >= 0) c.getLong(durColumn) else 0L
                            val albumArtUri = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(id.toString()).build()
                            foundTracks.add(PowerampTrack(id, title.ifEmpty { name }, artist, duration, albumArtUri = albumArtUri))
                        }
                    }
                } else if (artistMatchToken != null) {
                    val targetArtist = artistMatchToken.first.substringAfter("MAGIC_TOKEN_ARTIST_MATCH|")
                    val cursor = context.contentResolver.query(
                        filesUri, projection, "artist LIKE ?", arrayOf("%$targetArtist%"), "played_times DESC LIMIT 100"
                    )
                    cursor?.use { c ->
                        val idColumn = c.getColumnIndexOrThrow("_id")
                        val artistTagColumn = c.getColumnIndex("artist_tag")
                        val artistNameColumn = c.getColumnIndex("artist")
                        val titleColumn = c.getColumnIndex("title_tag")
                        val nameColumn = c.getColumnIndex("name")
                        val durColumn = c.getColumnIndex("duration")
                        while (c.moveToNext()) {
                            val id = c.getLong(idColumn)
                            val artistTag = if (artistTagColumn >= 0) c.getString(artistTagColumn) ?: "" else ""
                            val artistName = if (artistNameColumn >= 0) c.getString(artistNameColumn) ?: "" else ""
                            val artist = artistTag.ifEmpty { artistName }
                            val title = if (titleColumn >= 0) c.getString(titleColumn) ?: "" else ""
                            val name = if (nameColumn >= 0) c.getString(nameColumn) ?: "" else ""
                            val duration = if (durColumn >= 0) c.getLong(durColumn) else 0L
                            val albumArtUri = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(id.toString()).build()
                            foundTracks.add(PowerampTrack(id, title.ifEmpty { name }, artist, duration, albumArtUri = albumArtUri))
                        }
                    }
                } else if (isNewTracksRequest) {
                    // "Best of New": Get the 100 most recently added tracks
                    val recentCursor = context.contentResolver.query(
                        filesUri, projection, null, null, "file_created_at DESC LIMIT 100"
                    )
                    
                    val tempNewTracks = mutableListOf<PowerampTrack>()
                    recentCursor?.use { c ->
                        val idColumn = c.getColumnIndexOrThrow("_id")
                        val artistTagColumn = c.getColumnIndex("artist_tag")
                        val artistNameColumn = c.getColumnIndex("artist")
                        val titleColumn = c.getColumnIndex("title_tag")
                        val nameColumn = c.getColumnIndex("name")
                        val durColumn = c.getColumnIndex("duration")
                        val playedColumn = c.getColumnIndex("played_times")

                        while (c.moveToNext()) {
                            val id = c.getLong(idColumn)
                            val artistTag = if (artistTagColumn >= 0) c.getString(artistTagColumn) ?: "" else ""
                            val artistName = if (artistNameColumn >= 0) c.getString(artistNameColumn) ?: "" else ""
                            val artist = artistTag.ifEmpty { artistName }
                            val title = if (titleColumn >= 0) c.getString(titleColumn) ?: "" else ""
                            val name = if (nameColumn >= 0) c.getString(nameColumn) ?: "" else ""
                            val duration = if (durColumn >= 0) c.getLong(durColumn) else 0L
                            val playedTimes = if (playedColumn >= 0) c.getInt(playedColumn) else 0
                            val albumArtUri = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(id.toString()).build()
                            tempNewTracks.add(PowerampTrack(id, title.ifEmpty { name }, artist, duration, playedTimes, albumArtUri))
                        }
                    }
                    
                    // Take a random amount (50-100) most played among the 100 newest
                    val randomLimit = java.util.Random().nextInt(51) + 50 
                    val bestOfNew = tempNewTracks.sortedByDescending { it.playedTimes }.take(randomLimit)
                    foundTracks.addAll(bestOfNew)
                    
                    // Mix in extra random top favorites for a "smart" feel (up to 100 tracks total)
                    val topCursor = context.contentResolver.query(
                        filesUri, arrayOf(TableDefs.Files._ID), "played_times > 0", null, "played_times DESC LIMIT 300"
                    )
                    val tempIds = mutableListOf<Long>()
                    topCursor?.use { c ->
                        val idColumn = c.getColumnIndexOrThrow("_id")
                        while (c.moveToNext()) {
                            tempIds.add(c.getLong(idColumn))
                        }
                    }
                    tempIds.shuffle()
                    
                    val neededCount = maxOf(0, 100 - foundTracks.size)
                    val selectedIds = tempIds.filter { id -> foundTracks.none { it.id == id } }.take(neededCount)
                    foundTracks.addAll(getTracksByIds(selectedIds))
                } else if (isUnderratedRequest) {
                    val cursor = context.contentResolver.query(
                        filesUri, projection, "played_times > 0 AND played_times <= 5", null, null
                    )
                    val tempUnderrated = mutableListOf<PowerampTrack>()
                    cursor?.use { c ->
                        val idColumn = c.getColumnIndexOrThrow("_id")
                        val artistTagColumn = c.getColumnIndex("artist_tag")
                        val artistNameColumn = c.getColumnIndex("artist")
                        val titleColumn = c.getColumnIndex("title_tag")
                        val nameColumn = c.getColumnIndex("name")
                        val durColumn = c.getColumnIndex("duration")
                        val playedColumn = c.getColumnIndex("played_times")

                        while (c.moveToNext()) {
                            val id = c.getLong(idColumn)
                            val artistTag = if (artistTagColumn >= 0) c.getString(artistTagColumn) ?: "" else ""
                            val artistName = if (artistNameColumn >= 0) c.getString(artistNameColumn) ?: "" else ""
                            val artist = artistTag.ifEmpty { artistName }
                            val title = if (titleColumn >= 0) c.getString(titleColumn) ?: "" else ""
                            val name = if (nameColumn >= 0) c.getString(nameColumn) ?: "" else ""
                            val duration = if (durColumn >= 0) c.getLong(durColumn) else 0L
                            val playedTimes = if (playedColumn >= 0) c.getInt(playedColumn) else 0
                            val albumArtUri = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(id.toString()).build()
                            tempUnderrated.add(PowerampTrack(id, title.ifEmpty { name }, artist, duration, playedTimes, albumArtUri))
                        }
                    }
                    val randomLimit = java.util.Random().nextInt(51) + 50 // 50-100 tracks
                    foundTracks.addAll(tempUnderrated.shuffled().take(randomLimit))
                } else if (isForgottenFavoritesRequest) {
                    val cutoffMillis = System.currentTimeMillis() - (180L * 24 * 60 * 60 * 1000L) // 180 days ago in millis
                    // Include tracks with played_at=0 (old tracks before Poweramp tracked this) OR played_at older than cutoff
                    val cursor = context.contentResolver.query(
                        filesUri, projection, "played_times >= 10 AND (folder_files.played_at = 0 OR folder_files.played_at < ?)", arrayOf(cutoffMillis.toString()), "folder_files.played_times DESC"
                    )
                    val tempForgotten = mutableListOf<PowerampTrack>()
                    cursor?.use { c ->
                        val idColumn = c.getColumnIndexOrThrow("_id")
                        val artistTagColumn = c.getColumnIndex("artist_tag")
                        val artistNameColumn = c.getColumnIndex("artist")
                        val titleColumn = c.getColumnIndex("title_tag")
                        val nameColumn = c.getColumnIndex("name")
                        val durColumn = c.getColumnIndex("duration")
                        val playedColumn = c.getColumnIndex("played_times")

                        while (c.moveToNext()) {
                            val id = c.getLong(idColumn)
                            val artistTag = if (artistTagColumn >= 0) c.getString(artistTagColumn) ?: "" else ""
                            val artistName = if (artistNameColumn >= 0) c.getString(artistNameColumn) ?: "" else ""
                            val artist = artistTag.ifEmpty { artistName }
                            val title = if (titleColumn >= 0) c.getString(titleColumn) ?: "" else ""
                            val name = if (nameColumn >= 0) c.getString(nameColumn) ?: "" else ""
                            val duration = if (durColumn >= 0) c.getLong(durColumn) else 0L
                            val playedTimes = if (playedColumn >= 0) c.getInt(playedColumn) else 0
                            val albumArtUri = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(id.toString()).build()
                            tempForgotten.add(PowerampTrack(id, title.ifEmpty { name }, artist, duration, playedTimes, albumArtUri))
                        }
                    }
                    val randomLimit = java.util.Random().nextInt(21) + 80 // 80-100 tracks
                    foundTracks.addAll(tempForgotten.shuffled().take(randomLimit))
                } else if (sonicHighEnergyRequest || sonicLowEnergyRequest || sonicPartyRequest || 
                           sonicEveningRequest || sonicMoodHappyRequest || sonicMoodSadRequest || sonicBpmToken != null || sonicBpmRangeToken != null) {
                    val scrobbleDao = com.maxmpz.poweramp.companion.db.ScrobbleDatabase.getDatabase(context).scrobbleDao()
                    
                    val filtered = when {
                        sonicMoodHappyRequest -> scrobbleDao.getHappyScrobbles()
                        sonicMoodSadRequest -> scrobbleDao.getSadScrobbles()
                        sonicHighEnergyRequest -> scrobbleDao.getHighEnergyScrobbles()
                        sonicLowEnergyRequest -> scrobbleDao.getLowEnergyScrobbles()
                        sonicPartyRequest -> scrobbleDao.getPartyScrobbles()
                        sonicEveningRequest -> scrobbleDao.getEveningScrobbles()
                        sonicBpmToken != null -> {
                            val targetBpm = sonicBpmToken.first.substringAfter("MAGIC_TOKEN_SONIC_BPM_").toFloatOrNull() ?: 125f
                            scrobbleDao.getScrobblesByBpm(targetBpm)
                        }
                        sonicBpmRangeToken != null -> {
                            val rangeStr = sonicBpmRangeToken.first.substringAfter("MAGIC_TOKEN_SONIC_BPM_RANGE|")
                            val minBpm = rangeStr.substringBefore("-").toFloatOrNull() ?: 120f
                            val maxBpm = rangeStr.substringAfter("-").toFloatOrNull() ?: 140f
                            scrobbleDao.getScrobblesByBpmRange(minBpm, maxBpm)
                        }
                        else -> emptyList()
                    }
                    
                    val resultPairs = filtered.map { Pair(it.artist, it.title) }.distinct().shuffled().take(100)
                    if (resultPairs.isNotEmpty()) {
                        val actualTracks = findTracks(resultPairs, onLog) // Recursively call with pairs
                        foundTracks.addAll(actualTracks)
                    }
                    
                    if (foundTracks.isEmpty()) {
                        // If no sonic matches in DB, fallback gracefully
                        onLog?.invoke("Wenig Sonic-Werte vorhanden. Suche stattdessen nach passenden Begriffen in deinen Titeln...")
                        // Fetch a broader pool of tracks safely (up to 5000), then filter using Kotlin to bypass SQLite SecurityExceptions
                        var cursor = context.contentResolver.query(filesUri, arrayOf(TableDefs.Files._ID, TableDefs.Files.NAME, "artist"), "played_times > 0", null, "played_times DESC LIMIT 5000")
                        val tempIds = mutableListOf<Long>()
                        
                        cursor?.use { c ->
                            val idCol = c.getColumnIndexOrThrow("_id")
                            val nameCol = c.getColumnIndexOrThrow("name")
                            val artistCol = c.getColumnIndex("artist")
                            
                            while (c.moveToNext()) { 
                                val name = c.getString(nameCol)?.lowercase() ?: ""
                                val artist = if (artistCol >= 0) c.getString(artistCol)?.lowercase() ?: "" else ""
                                val fullSearch = "$name $artist"
                                
                                val matches = if (sonicLowEnergyRequest || sonicEveningRequest) {
                                    fullSearch.contains("acoustic") || fullSearch.contains("chill") || fullSearch.contains("ambient") || 
                                    fullSearch.contains("lofi") || fullSearch.contains("unplugged") || fullSearch.contains("piano") || 
                                    fullSearch.contains("sleep") || fullSearch.contains("night") || fullSearch.contains("quiet") || 
                                    fullSearch.contains("soft") || fullSearch.contains("slow") || fullSearch.contains("calm") || 
                                    fullSearch.contains("minimal") || fullSearch.contains("jazz")
                                } else if (sonicHighEnergyRequest || sonicPartyRequest) {
                                    fullSearch.contains("remix") || fullSearch.contains("mix") || fullSearch.contains("live") || 
                                    fullSearch.contains("club") || fullSearch.contains("dance") || fullSearch.contains("party") || 
                                    fullSearch.contains("bass") || fullSearch.contains("hard") || fullSearch.contains("fast") || 
                                    fullSearch.contains("power") || fullSearch.contains("drums") || fullSearch.contains("electro") || 
                                    fullSearch.contains("energy") || fullSearch.contains("metal") || fullSearch.contains("rock")
                                } else if (sonicMoodHappyRequest) {
                                    fullSearch.contains("happy") || fullSearch.contains("sun") || fullSearch.contains("good") || 
                                    fullSearch.contains("smile") || fullSearch.contains("joy") || fullSearch.contains("summer") || 
                                    fullSearch.contains("light") || fullSearch.contains("bright") || fullSearch.contains("up") || 
                                    fullSearch.contains("pop") || fullSearch.contains("fun") || fullSearch.contains("love")
                                } else if (sonicMoodSadRequest) {
                                    fullSearch.contains("sad") || fullSearch.contains("cry") || fullSearch.contains("tears") || 
                                    fullSearch.contains("melancholy") || fullSearch.contains("dark") || fullSearch.contains("rain") || 
                                    fullSearch.contains("blue") || fullSearch.contains("alone") || fullSearch.contains("heart") || 
                                    fullSearch.contains("lost") || fullSearch.contains("memory") || fullSearch.contains("winter")
                                } else {
                                    false
                                }
                                
                                if (matches) {
                                    tempIds.add(c.getLong(idCol))
                                }
                            }
                        }
                        
                        // Ultimate fallback if the semantic keyword search is also empty
                        if (tempIds.isEmpty()) {
                            onLog?.invoke("Keine passenden Stichwörter gefunden. Generiere einen komplett zufälligen Favoriten-Mix...")
                            cursor = context.contentResolver.query(filesUri, arrayOf(TableDefs.Files._ID), "played_times > 0", null, "played_times DESC LIMIT 300")
                            cursor?.use { c ->
                                val idCol = c.getColumnIndexOrThrow("_id")
                                while (c.moveToNext()) { tempIds.add(c.getLong(idCol)) }
                            }
                        }
                        
                        foundTracks.addAll(getTracksByIds(tempIds.shuffled().take(80))) // Increased from 50 to 80 for more variety
                    }
                } else {
                    // Fallback: Random mix of your top 500 played tracks
                    val randomLimit = java.util.Random().nextInt(81) + 20 // 20-100
                    val cursor = context.contentResolver.query(
                        filesUri, arrayOf(TableDefs.Files._ID), "played_times > 0", null, "played_times DESC LIMIT 300"
                    )
                    val tempFallbackIds = mutableListOf<Long>()
                    cursor?.use { c ->
                        val idColumn = c.getColumnIndexOrThrow("_id")
                        while (c.moveToNext()) {
                            tempFallbackIds.add(c.getLong(idColumn))
                        }
                    }
                    tempFallbackIds.shuffle()
                    val selectedIds = tempFallbackIds.take(randomLimit)
                    foundTracks.addAll(getTracksByIds(selectedIds))
                }
                
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return foundTracks.toList().shuffled()
        }

        try {
            val cursor = context.contentResolver.query(filesUri, projection, null, null, null)

            cursor?.use { c ->
                val idColumn = c.getColumnIndexOrThrow("_id")
                val artistTagColumn = c.getColumnIndex("artist_tag")
                val artistNameColumn = c.getColumnIndex("artist")
                val titleColumn = c.getColumnIndex("title_tag")
                val nameColumn = c.getColumnIndex("name")
                val durColumn = c.getColumnIndex("duration")
                
                // Pre-compute lookup for strict matches
                val strictLookup = recommendedTracks.groupBy { "${it.first.trim().lowercase()}|${it.second.trim().lowercase()}" }
                
                val bestMatches = mutableMapOf<Pair<String, String>, PowerampTrack>()
                val matchQuality = mutableMapOf<Pair<String, String>, Int>() // 1: fuzzy, 2: strict

                while (c.moveToNext()) {
                    val id = c.getLong(idColumn)
                    val artistTag = if (artistTagColumn >= 0) c.getString(artistTagColumn) ?: "" else ""
                    val artistName = if (artistNameColumn >= 0) c.getString(artistNameColumn) ?: "" else ""
                    val artist = artistTag.ifEmpty { artistName }
                    val title = if (titleColumn >= 0) c.getString(titleColumn) ?: "" else ""
                    val name = if (nameColumn >= 0) c.getString(nameColumn) ?: "" else ""
                    val duration = if (durColumn >= 0) c.getLong(durColumn) else 0L

                    val albumArtUri = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(id.toString()).build()
                    val currentTrack = PowerampTrack(id, title.ifEmpty { name }, artist, duration, albumArtUri = albumArtUri)

                    // 1. Strict ID3 Match
                    val matchKey = "${artist.trim().lowercase()}|${title.trim().lowercase()}"
                    val strictRecs = strictLookup[matchKey]
                    if (strictRecs != null) {
                        for (rec in strictRecs) {
                            bestMatches[rec] = currentTrack
                            matchQuality[rec] = 2
                        }
                    }

                    // 2. Fuzzy Filename Match (only for those that don't have a strict match yet)
                    for (rec in recommendedTracks) {
                        if ((matchQuality[rec] ?: 0) < 2) {
                            val rTitle = rec.second.trim()
                            val rArtist = rec.first.trim()
                            
                            if (rTitle.length >= 3) {
                                val isTitleInName = name.contains(rTitle, ignoreCase = true)
                                val isArtistInFile = rArtist.isBlank() || 
                                    artist.contains(rArtist, ignoreCase = true) || 
                                    name.contains(rArtist, ignoreCase = true)
                                    
                                if (isTitleInName && isArtistInFile) {
                                    if ((matchQuality[rec] ?: 0) < 1) {
                                        bestMatches[rec] = currentTrack
                                        matchQuality[rec] = 1
                                    }
                                }
                            }
                        }
                    }
                }
                foundTracks.addAll(bestMatches.values.distinctBy { it.id })
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("Failed to query Poweramp. Did you grant data permissions?")
        }
        
        return foundTracks.toList()
    }
    
    fun getFullLibraryJsonForGemini(): String {
        val filesUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build()
        val projection = arrayOf(
            TableDefs.Files._ID,
            TableDefs.Files.ARTIST_TAG,
            TableDefs.Files.TITLE_TAG,
            TableDefs.Files.ALBUM_TAG,
            TableDefs.Files.NAME,
            TableDefs.Files.PLAYED_TIMES,
            TableDefs.Files.FILE_CREATED_AT,
            "artist",
            "album"
        )
        
        val jsonArray = JSONArray()
        var genreCount = 0
        var totalCount = 0
        
        try {
            val cursor = context.contentResolver.query(filesUri, projection, null, null, null)
            cursor?.use { c ->
                val idColumn = c.getColumnIndexOrThrow("_id")
                val artistTagColumn = c.getColumnIndex("artist_tag")
                val artistNameColumn = c.getColumnIndex("artist")
                val finalArtistColumn = if (artistNameColumn != -1) artistNameColumn else -1
                val titleColumn = c.getColumnIndex("title_tag")
                val albumTagColumn = c.getColumnIndex("album_tag")
                val albumNameColumn = c.getColumnIndex("album")
                val nameColumn = c.getColumnIndex("name")
                val playedColumn = c.getColumnIndex("played_times")
                val createdColumn = c.getColumnIndex("file_created_at")

                while (c.moveToNext()) {
                    val id = c.getLong(idColumn)
                    val artistTag = if (artistTagColumn >= 0) c.getString(artistTagColumn) ?: "" else ""
                    val artistName = if (finalArtistColumn >= 0) c.getString(finalArtistColumn) ?: "" else ""
                    val finalArtist = artistTag.ifEmpty { artistName }
                    val title = if (titleColumn >= 0) c.getString(titleColumn) ?: "" else ""
                    val name = if (nameColumn >= 0) c.getString(nameColumn) ?: "" else ""
                    val finalTitle = title.ifEmpty { name }
                    val albumTag = if (albumTagColumn >= 0) c.getString(albumTagColumn) ?: "" else ""
                    val albumName = if (albumNameColumn >= 0) c.getString(albumNameColumn) ?: "" else ""
                    val finalAlbum = albumTag.ifEmpty { albumName }
                    val playedTimes = if (playedColumn >= 0) c.getInt(playedColumn) else 0
                    val fileCreatedAt = if (createdColumn >= 0) c.getLong(createdColumn) else 0
                    
                    totalCount++
                    
                    val obj = JSONObject().apply {
                        put("i", id)
                        put("a", finalArtist)
                        put("t", finalTitle)
                        put("b", finalAlbum)
                        put("p", playedTimes)
                        put("c", fileCreatedAt)
                    }
                    jsonArray.put(obj)
                }
            }
            android.util.Log.d("PowerampController", "Library JSON: $totalCount tracks, $genreCount with genre data.")
        } catch (e: Exception) {
            e.printStackTrace()
            return "[]"
        }
        
        return jsonArray.toString()
    }

    fun getTracksByIds(ids: List<Long>): List<PowerampTrack> {
        val tracks = mutableListOf<PowerampTrack>()
        val filesUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build()
        val projection = arrayOf(
            TableDefs.Files._ID, TableDefs.Files.ARTIST_TAG,            TableDefs.Files.NAME, TableDefs.Files.DURATION, "artist"
        )
        
        val idStrings = ids.joinToString(",") { it.toString() }
        val selection = "${TableDefs.Files._ID} IN ($idStrings)"
        
        try {
            val cursor = context.contentResolver.query(filesUri, projection, selection, null, null)
            cursor?.use { c ->
                val idCol = c.getColumnIndexOrThrow("_id")
                val artistTagCol = c.getColumnIndex("artist_tag")
                val artistNameCol = c.getColumnIndex("artist")
                val titleCol = c.getColumnIndex("title_tag")
                val nameCol = c.getColumnIndex("name")
                val durCol = c.getColumnIndex("duration")
                
                while (c.moveToNext()) {
                    val id = c.getLong(idCol)
                    val artistTag = if (artistTagCol >= 0) c.getString(artistTagCol) ?: "" else ""
                    val artistName = if (artistNameCol >= 0) c.getString(artistNameCol) ?: "" else ""
                    val artist = artistTag.ifEmpty { artistName }
                    val title = if (titleCol >= 0) c.getString(titleCol) ?: "" else ""
                    val name = if (nameCol >= 0) c.getString(nameCol) ?: "" else ""
                    val duration = if (durCol >= 0) c.getLong(durCol) else 0L
                    
                    val albumArtUri = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(id.toString()).build()
                    tracks.add(PowerampTrack(id, title.ifEmpty { name }, artist, duration, albumArtUri = albumArtUri))
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("PowerampController", "Error getting tracks by ids", e)
        }
        
        // Restore original order requested by Gemini/Engine
        return ids.mapNotNull { id -> tracks.find { it.id == id } }
    }

    /**
     * Returns statistics about the current Poweramp queue.
     */
    fun getQueueStats(): QueueStats {
        val queueUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("queue").build()
        var total = 0
        var played = 0
        var totalDuration = 0L
        var remainingDuration = 0L
        
        try {
            // Querying queue URI joins folder_files automatically in Poweramp
            // Use qualified _id to avoid ambiguity
            context.contentResolver.query(queueUri, arrayOf("queue._id", "folder_files.duration", "queue.created_at", "folder_files.played_at"), null, null, null)?.use { c ->
                total = c.count
                val durCol = c.getColumnIndex("duration").let { if (it == -1) c.getColumnIndex("folder_files.duration") else it }
                val createdAtCol = c.getColumnIndex("created_at").let { if (it == -1) c.getColumnIndex("queue.created_at") else it }
                val playedAtCol = c.getColumnIndex("played_at").let { if (it == -1) c.getColumnIndex("folder_files.played_at") else it }
                
                while (c.moveToNext()) {
                    val duration = if (durCol >= 0) c.getLong(durCol) else 0L
                    totalDuration += duration
                    
                    val createdAt = if (createdAtCol >= 0) c.getLong(createdAtCol) else 0L
                    val playedAt = if (playedAtCol >= 0) c.getLong(playedAtCol) else 0L
                    
                    if (playedAt < createdAt) {
                        remainingDuration += duration
                    }
                }
            }
            
            // Selection for played tracks based on TableDefs.Queue.CALC_PLAYED
            val selection = "${TableDefs.Files.PLAYED_AT} >= ${TableDefs.Queue.CREATED_AT}"
            context.contentResolver.query(queueUri, arrayOf(TableDefs.Queue._ID), selection, null, null)?.use { c ->
                played = c.count
            }
        } catch (e: Exception) {
            android.util.Log.e("PowerampController", "Error getting queue stats", e)
        }
        
        return QueueStats(played, total, totalDuration, remainingDuration)
    }

    fun getQueueTracks(): List<Pair<String, String>> {
        val tracks = mutableListOf<Pair<String, String>>()
        val queueUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("queue").build()
        val projection = arrayOf("artist", "title")
        try {
            context.contentResolver.query(queueUri, projection, null, null, "sort ASC")?.use { c ->
                val artistCol = c.getColumnIndex("artist")
                val titleCol = c.getColumnIndex("title")
                while (c.moveToNext()) {
                    val artist = if (artistCol >= 0) c.getString(artistCol) ?: "" else ""
                    val title = if (titleCol >= 0) c.getString(titleCol) ?: "" else ""
                    if (artist.isNotEmpty() && title.isNotEmpty()) {
                        tracks.add(Pair(artist, title))
                    }
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("PowerampController", "Error getting queue tracks", e)
        }
        return tracks
    }

    fun sendToPoweramp(fileIds: List<Long>) {
        val playlistsUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("playlists").build()
        val playlistName = "AI Companion (${System.currentTimeMillis() % 10000})"
        
        val values = ContentValues().apply {
            put("playlist", playlistName)
        }
        
        try {
            val playlistInsertedUri = context.contentResolver.insert(playlistsUri, values)
            
            if (playlistInsertedUri != null) {
                val playlistEntriesUri = playlistInsertedUri.buildUpon().appendEncodedPath("files").build()
                
                var sortOrder = 0
                var firstEntryUri: Uri? = null
                
                for (id in fileIds) {
                    val entryValues = ContentValues().apply {
                        put("folder_file_id", id)
                        put("sort", sortOrder++)
                    }
                    val entryUri = context.contentResolver.insert(playlistEntriesUri, entryValues)
                    if (firstEntryUri == null && entryUri != null) {
                        firstEntryUri = entryUri
                    }
                }
                
                Log.d("PowerampController", "Created playlist $playlistName with ${fileIds.size} tracks")
                
                val reloadIntent = Intent(PowerampAPI.ACTION_RELOAD_DATA).apply {
                    setPackage(PowerampAPI.PACKAGE_NAME)
                    putExtra(PowerampAPI.EXTRA_TABLE, "playlist_entries")
                }
                context.sendBroadcast(reloadIntent)

                if (firstEntryUri != null) {
                    val playIntent = Intent(PowerampAPI.ACTION_API_COMMAND).apply {
                        component = android.content.ComponentName(
                            PowerampAPI.PACKAGE_NAME, PowerampAPI.API_RECEIVER_NAME
                        )
                        putExtra(PowerampAPI.EXTRA_COMMAND, PowerampAPI.Commands.OPEN_TO_PLAY)
                        // OPEN_TO_PLAY specifically requires the ID of an entry *inside* the playlist/queue
                        data = firstEntryUri
                    }
                    Log.d("StatsDEBUG", "PowerampController: Sending playlist play broadcast: $playIntent")
                    context.sendBroadcast(playIntent)
                    showToast("Playing AI Playlist in Poweramp!")
                } else {
                    showToast("Playlist created but could not start playback.")
                }
            } else {
                showToast("Failed to create Poweramp playlist.")
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
            showToast("Security Exception: Poweramp Data Write Permission likely missing. Asking now...")
            askForPowerampPermission()
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("Error communicating with Poweramp: ${e.message}")
        }
    }

    private fun askForPowerampPermission() {
        val intent = Intent(PowerampAPI.ACTION_ASK_FOR_DATA_PERMISSION).apply {
            setPackage(PowerampAPI.PACKAGE_NAME)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            showToast("Could not request Poweramp permissions. Is Poweramp installed?")
        }
    }

    private fun performShuffleShift(queueUri: Uri, startShuffle: Long, shiftCount: Long) {
        val shuffleShiftCursor = context.contentResolver.query(queueUri, arrayOf(TableDefs.Queue._ID, TableDefs.Queue.SHUFFLE_ORDER), 
            "${TableDefs.Queue.SHUFFLE_ORDER} >= $startShuffle", null, "${TableDefs.Queue.SHUFFLE_ORDER} DESC")
        shuffleShiftCursor?.use { sc ->
            while (sc.moveToNext()) {
                val id = sc.getLong(0)
                val newShuffle = sc.getLong(1) + shiftCount
                val values = ContentValues().apply { put("shuffle_order", newShuffle) }
                context.contentResolver.update(queueUri, values, "_id = ?", arrayOf(id.toString()))
            }
        }
    }

    fun dumpQueueToLog() {
        val queueUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("queue").build()
        val projection = arrayOf(
            "queue._id", 
            "queue.sort", 
            "queue.shuffle_order", 
            "queue.folder_file_id", 
            "queue.created_at"
        )
        try {
            context.contentResolver.query(queueUri, projection, null, null, "queue.sort ASC")?.use { c ->
                val idCol = c.getColumnIndex("_id").let { if (it == -1) c.getColumnIndex("queue._id") else it }
                val sortCol = c.getColumnIndex("sort").let { if (it == -1) c.getColumnIndex("queue.sort") else it }
                val shuffleCol = c.getColumnIndex("shuffle_order").let { if (it == -1) c.getColumnIndex("queue.shuffle_order") else it }
                val fileIdCol = c.getColumnIndex("folder_file_id").let { if (it == -1) c.getColumnIndex("queue.folder_file_id") else it }
                val createdCol = c.getColumnIndex("created_at").let { if (it == -1) c.getColumnIndex("queue.created_at") else it }
                
                Log.e("PowerampController", "--- QUEUE DUMP START ---")
                while (c.moveToNext()) {
                    val id = c.getLong(idCol)
                    val sort = c.getInt(sortCol)
                    val shuffle = c.getLong(shuffleCol)
                    val fileId = c.getLong(fileIdCol)
                    val created = c.getLong(createdCol)
                    Log.e("PowerampController", "ID=$id, Sort=$sort, Shuffle=$shuffle, File=$fileId, Created=$created")
                }
                Log.e("PowerampController", "--- QUEUE DUMP END ---")
            }
        } catch (e: Exception) {
            Log.e("PowerampController", "Error dumping queue", e)
        }
    }

    private fun showToast(message: String) {
        mainHandler.post {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    fun sendToPowerampQueue(fileIds: List<Long>) {
        val queueUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("queue").build()

        try {
            val prefs = context.getSharedPreferences("PowerampCompanionPrefs", Context.MODE_PRIVATE)
            val interweaveShuffle = prefs.getBoolean("shuffle_queue_enabled", false)
            
            // 1. EVALUATE QUEUE STATE
            // We cannot run DELETE with joined columns like folder_files.played_at, so we must query first.
            // A queue item is "played" if its PLAYED_AT >= Queue.CREATED_AT
            val unplayedQueueIds = mutableListOf<Pair<Long, Int>>() // Pair(queue_id, current_sort)
            val playedQueueIds = mutableListOf<Pair<Long, Long>>() // _id to played_at
            
            var minUnplayedShuffle = Long.MAX_VALUE
            var currentPlayingShuffle = -1L
            var currentPlayingSort = -1

            if (currentQueueId != -1L) {
                context.contentResolver.query(queueUri, arrayOf(TableDefs.Queue.SHUFFLE_ORDER, "sort"), "${TableDefs.Queue._ID} = $currentQueueId", null, null)?.use { c ->
                    if (c.moveToFirst()) {
                        currentPlayingShuffle = c.getLong(0)
                        currentPlayingSort = c.getInt(1)
                    }
                }
            }

            context.contentResolver.query(
                queueUri, 
                arrayOf(TableDefs.Queue._ID, "sort", TableDefs.Queue.SHUFFLE_ORDER, TableDefs.Queue.CREATED_AT, TableDefs.Files.PLAYED_AT), 
                null, 
                null, 
                "sort ASC"
            )?.use { c ->
                val idCol = c.getColumnIndexOrThrow("_id")
                val sortCol = c.getColumnIndexOrThrow("sort")
                val shuffleCol = c.getColumnIndexOrThrow("shuffle_order")
                val createdCol = c.getColumnIndex(TableDefs.Queue.CREATED_AT)
                val playedCol = c.getColumnIndex(TableDefs.Files.PLAYED_AT)
                
                while (c.moveToNext()) {
                    val id = c.getLong(idCol)
                    val sort = c.getInt(sortCol)
                    val shuffle = c.getLong(shuffleCol)
                    val createdAt = if (createdCol >= 0) c.getLong(createdCol) else 0L
                    val playedAt = if (playedCol >= 0) c.getLong(playedCol) else 0L
                    
                    if (id == currentQueueId) {
                        // The current playing track should be structurally ignored from the unplayed mix offset 
                        // physically tracking it as 'played' safely enforces that.
                        playedQueueIds.add(Pair(id, playedAt))
                    } else if (playedAt >= createdAt && playedAt > 0) {
                        playedQueueIds.add(Pair(id, playedAt))
                    } else {
                        unplayedQueueIds.add(Pair(id, sort))
                        if (shuffle < minUnplayedShuffle) minUnplayedShuffle = shuffle
                    }
                }
            }

            if (currentPlayingShuffle != -1L) {
                minUnplayedShuffle = Math.max(minUnplayedShuffle, currentPlayingShuffle + 1000L)
            }

            if (minUnplayedShuffle == Long.MAX_VALUE || minUnplayedShuffle < 100_000L) {
                minUnplayedShuffle = 100_000L
            }

            val hasUnplayedTracks = unplayedQueueIds.isNotEmpty()

            // User requested: "only when all songs have been played should they go away"
            if (!hasUnplayedTracks) {
                // All songs have been played (or queue is empty), clear the entire queue
                val deletedCount = context.contentResolver.delete(queueUri, null, null)
                if (deletedCount > 0) {
                    Log.d("PowerampController", "Queue was entirely played. Cleared $deletedCount tracks before adding new ones.")
                }
            } else if (playedQueueIds.isNotEmpty()) {
                // FOSSILIZE played tracks: Poweramp natively compresses already played tracks to negative shuffle_order values
                // to permanently ensure they sit exactly at the beginning of the shuffle history sequence.
                val sortedPlayed = playedQueueIds.sortedBy { it.second }.map { it.first }
                var negativeShuffle = -(sortedPlayed.size.toLong())
                
                for (playedId in sortedPlayed) {
                    val cv = ContentValues().apply {
                        put("shuffle_order", negativeShuffle++)
                    }
                    context.contentResolver.update(queueUri, cv, "${TableDefs.Queue._ID} = $playedId", null)
                }
                Log.d("PowerampController", "Fossilized ${sortedPlayed.size} played tracks into negative shuffle_order.")
            }

        // DIAGNOSTIC TOAST
        showToast("Shuffle: ${if (interweaveShuffle) "AN" else "AUS"}")

        if (interweaveShuffle) {
            val shuffledFileIds = fileIds.shuffled()
            
            if (!hasUnplayedTracks) {
                // Queue was empty or fully played. We already cleared it.
                var sortOrder = 0 // Start fresh
                val now = System.currentTimeMillis()
                for (id in shuffledFileIds) {
                    val values = ContentValues().apply {
                        put("folder_file_id", id)
                        put("sort", sortOrder)
                        put("shuffle_order", kotlin.random.Random.nextLong(minUnplayedShuffle, Long.MAX_VALUE))
                        put("created_at", now)
                    }
                    context.contentResolver.insert(queueUri, values)
                    sortOrder += 10
                }
                Log.d("PowerampController", "Inserted ${shuffledFileIds.size} shuffled tracks into empty queue")
            } else {
                // INTERWEAVE: mixing with existing unplayed tracks using native-like random offset
                var minSort = unplayedQueueIds.minOfOrNull { it.second } ?: 0
                val maxSort = unplayedQueueIds.maxOfOrNull { it.second } ?: 0
                
                if (currentPlayingSort != -1 && minSort <= currentPlayingSort) {
                    minSort = currentPlayingSort + 1
                }

                val rangeSize = (maxSort - minSort) + (fileIds.size * 10)
                val safeRangeSize = if (rangeSize <= 0) 1000 else rangeSize
                
                val now = System.currentTimeMillis()
                for (id in shuffledFileIds) {
                    val randomOffset = random.nextInt(safeRangeSize)
                    val generatedSort = minSort + randomOffset
                    
                    val values = ContentValues().apply {
                        put("folder_file_id", id)
                        put("sort", generatedSort)
                        put("shuffle_order", kotlin.random.Random.nextLong(minUnplayedShuffle, Long.MAX_VALUE))
                        put("created_at", now)
                    }
                    context.contentResolver.insert(queueUri, values)
                }
                Log.d("PowerampController", "Interweaved ${shuffledFileIds.size} tracks seamlessly into existing queue")
            }
        } else {
            // NORMAL APPEND
            var sortOrder = 0
            
            context.contentResolver.query(queueUri, arrayOf("sort"), null, null, "sort DESC LIMIT 1")?.use { c ->
                if (c.moveToFirst()) sortOrder = c.getInt(0) + 10
            }

            val now = System.currentTimeMillis()
            for (id in fileIds) {
                val values = ContentValues().apply {
                    put("folder_file_id", id)
                    put("sort", sortOrder)
                    put("shuffle_order", kotlin.random.Random.nextLong(minUnplayedShuffle, Long.MAX_VALUE))
                    put("created_at", now)
                }
                context.contentResolver.insert(queueUri, values)
                sortOrder += 10
            }
            Log.d("PowerampController", "Appended ${fileIds.size} tracks to queue sequentially")
        }

        val updateIntent = Intent(PowerampAPI.ACTION_RELOAD_DATA).apply {
            putExtra(PowerampAPI.EXTRA_TABLE, "queue")
            setPackage(PowerampAPI.PACKAGE_NAME)
        }
        context.sendBroadcast(updateIntent)

        // Important: POS_SYNC forces the player to re-sync its internal "what's next" state with the DB
        val posSyncIntent = Intent(PowerampAPI.ACTION_API_COMMAND).apply {
            setPackage(PowerampAPI.PACKAGE_NAME)
            putExtra(PowerampAPI.EXTRA_COMMAND, PowerampAPI.Commands.POS_SYNC)
        }
        context.sendBroadcast(posSyncIntent)
        
        showToast("Added ${fileIds.size} tracks to Queue!")

        } catch (e: SecurityException) {
            e.printStackTrace()
            showToast("Security Exception: Poweramp Data Write Permission missing. Asking now...")
            askForPowerampPermission()
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("Error adding to queue: ${e.message}")
        }
    }



    /**
     * Inserts tracks at the top of the unplayed queue (Play Next).
     */
    fun sendToPowerampQueueNext(fileIds: List<Long>) {
        if (fileIds.isEmpty()) return
        dumpQueueToLog() // DEBUG: See what's currently in there (e.g. native tracks)
        val queueUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("queue").build()
        val shiftCount = fileIds.size
        try {
            var currentPlayingSort = -1
            var currentPlayingShuffle = -1L
            
            val prefs = context.getSharedPreferences("PowerampCompanionPrefs", Context.MODE_PRIVATE)
            val lastPlayNextId = prefs.getLong("last_play_next_id", -1L)
            val lastPlayNextTime = prefs.getLong("last_play_next_time", 0L)
            val now = System.currentTimeMillis()
            val PLAY_NEXT_THRESHOLD_MS = 5 * 60 * 1000 // 5 minutes

            // Try to find the reference track (Current playing OR the last added "Play Next" item for FIFO)
            var baseSort = -1
            var baseShuffle = -1L

            // TIER 1: If we are actively playing from the queue, insert after current track
            if (currentQueueId != -1L) {
                context.contentResolver.query(queueUri, arrayOf(TableDefs.Queue.SORT, TableDefs.Queue.SHUFFLE_ORDER), "${TableDefs.Queue._ID} = $currentQueueId", null, null)?.use { c ->
                    if (c.moveToFirst()) {
                        baseSort = c.getInt(0)
                        baseShuffle = c.getLong(1)
                        Log.d("PowerampController", "Play Next: Using current playing track as base (sort=$baseSort, shuffle=$baseShuffle)")
                    }
                }
            }

            // TIER 2: FIFO: Check if we added tracks recently. If so, append to THAT batch.
            if (baseSort == -1 && lastPlayNextId != -1L && (now - lastPlayNextTime) < PLAY_NEXT_THRESHOLD_MS) {
                context.contentResolver.query(queueUri, arrayOf(TableDefs.Queue.SORT, TableDefs.Queue.SHUFFLE_ORDER), "${TableDefs.Queue._ID} = $lastPlayNextId", null, null)?.use { c ->
                    if (c.moveToFirst()) {
                        baseSort = c.getInt(0)
                        baseShuffle = c.getLong(1)
                        Log.d("PowerampController", "Play Next FIFO: Found recent batch track as base (sort=$baseSort, shuffle=$baseShuffle)")
                    }
                }
            }

            // TIER 3: Fallback: Absolute beginning of the queue (if not playing or no recent batch)
            if (baseSort == -1) {
                Log.d("PowerampController", "Play Next: Fallback to Top of Queue")
                
                // For Sort: use minimum sort in the whole queue
                context.contentResolver.query(queueUri, arrayOf(TableDefs.Queue.SORT), null, null, "${TableDefs.Queue.SORT} ASC LIMIT 1")?.use { c ->
                    if (c.moveToFirst()) {
                        baseSort = c.getInt(0) - 1
                        Log.d("PowerampController", "Play Next Fallback: Found min sort=${c.getInt(0)}, baseSort set to $baseSort")
                    }
                }
                
                // For Shuffle: use minimum shuffle_order in the whole queue
                context.contentResolver.query(queueUri, arrayOf(TableDefs.Queue.SHUFFLE_ORDER), null, null, "${TableDefs.Queue.SHUFFLE_ORDER} ASC LIMIT 1")?.use { c ->
                    if (c.moveToFirst()) {
                        baseShuffle = c.getLong(0) - 1000L
                        Log.d("PowerampController", "Play Next Fallback: Found min shuffle=${c.getLong(0)}, baseShuffle set to $baseShuffle")
                    }
                }

                if (baseSort == -1) { // Queue truly empty
                    baseSort = 0
                    baseShuffle = 1000L
                    Log.d("PowerampController", "Play Next Fallback: Queue entirely empty. Using defaults.")
                }
            }
            
            showToast("Play Next Base: S=$baseSort, SH=$baseShuffle")

            val insertSortPos = baseSort + 1
            
            // SHUFFLE INTERPOLATION
            // Find the shuffle_order of the track that follows our base track in the sequence
            var nextShuffle = -1L
            context.contentResolver.query(queueUri, arrayOf(TableDefs.Queue.SHUFFLE_ORDER), "${TableDefs.Queue.SHUFFLE_ORDER} > $baseShuffle", null, "${TableDefs.Queue.SHUFFLE_ORDER} ASC LIMIT 1")?.use { c ->
                if (c.moveToFirst()) nextShuffle = c.getLong(0)
            }

            // If gap is too small or non-existent, shift all subsequent tracks forward
            val minGap = (shiftCount + 1).toLong()
            if (nextShuffle != -1L && (nextShuffle - baseShuffle) <= minGap) {
                Log.d("PowerampController", "Shuffle gap too small (${nextShuffle - baseShuffle}). Performing shift.")
                performShuffleShift(queueUri, nextShuffle, 1000000L * shiftCount) // Large shift to be safe
                nextShuffle += 1000000L * shiftCount // Adjust our local nextShuffle as well
            }

            // 1. Shift Sort (Always shift sort for visual order)
            val sortShiftCursor = context.contentResolver.query(queueUri, arrayOf(TableDefs.Queue._ID, TableDefs.Queue.SORT), "${TableDefs.Queue.SORT} >= $insertSortPos", null, "${TableDefs.Queue.SORT} DESC")
            sortShiftCursor?.use { sc ->
                while (sc.moveToNext()) {
                    val id = sc.getLong(0)
                    val newSort = sc.getInt(1) + shiftCount
                    val values = ContentValues().apply { put("sort", newSort) }
                    context.contentResolver.update(queueUri, values, "_id = ?", arrayOf(id.toString()))
                }
            }

            // 2. Insert new tracks
            var currentPos = insertSortPos
            
            // By assigning the EXACT SAME shuffle_order to the entire batch, we perfectly simulate
            // native Poweramp behaviour. This flawlessly allows the user to drag-and-drop to reorder 
            // the AI tracks manually in the Poweramp UI, because Poweramp natively resolves queue tied
            // shuffle_orders by falling back to the 'sort' column (which is modified by dragging).
            val targetShuffle = if (nextShuffle != -1L) baseShuffle + Math.max(1L, (nextShuffle - baseShuffle) / 2) else baseShuffle + 1000L
            
            val nowInsertion = System.currentTimeMillis()
            var lastInsertedQueueId = -1L
            
            for (id in fileIds) {
                val values = ContentValues().apply {
                    put("folder_file_id", id)
                    put("sort", currentPos++)
                    put("shuffle_order", targetShuffle)
                    put("created_at", nowInsertion)
                }
                val uri = context.contentResolver.insert(queueUri, values)
                if (uri != null) {
                    uri.lastPathSegment?.toLongOrNull()?.let { lastInsertedQueueId = it }
                }
            }

            if (lastInsertedQueueId != -1L) {
                prefs.edit()
                    .putLong("last_play_next_id", lastInsertedQueueId)
                    .putLong("last_play_next_time", nowInsertion)
                    .apply()
            }

            Log.d("PowerampController", "Play Next: Inserted ${fileIds.size} tracks @ sort $insertSortPos, start_shuffle $targetShuffle")

            val reloadIntent = Intent(PowerampAPI.ACTION_RELOAD_DATA).apply {
                putExtra(PowerampAPI.EXTRA_TABLE, "queue")
            }
            PowerampAPIHelper.sendPAIntent(context, reloadIntent, false)

            // Important: POS_SYNC forces the player to re-sync its internal "what's next" state with the DB
            val posSyncIntent = Intent(PowerampAPI.ACTION_API_COMMAND).apply {
                setPackage(PowerampAPI.PACKAGE_NAME)
                putExtra(PowerampAPI.EXTRA_COMMAND, PowerampAPI.Commands.POS_SYNC)
            }
            context.sendBroadcast(posSyncIntent)

            Log.e("PowerampController", "Play Next: Sent POS_SYNC for ${fileIds.size} tracks")
            dumpQueueToLog()
            showToast("Wird als Nächstes gespielt (${fileIds.size})")

        } catch (e: SecurityException) {
            e.printStackTrace()
            showToast("Security Permission missing.")
            askForPowerampPermission()
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("Error adding to queue: ${e.message}")
        }
    }

    fun getPlaylists(): List<Pair<Long, String>> {
        val out = mutableListOf<Pair<Long, String>>()
        try {
            val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("playlists").build()
            context.contentResolver.query(uri, arrayOf(TableDefs.Playlists._ID, TableDefs.Playlists.PLAYLIST), null, null, "playlist COLLATE NOCASE ASC")?.use { c ->
                while(c.moveToNext()) {
                    out.add(Pair(c.getLong(0), c.getString(1)))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Ensure Toast/permission prompt if SecurityException
        }
        return out
    }

    fun getPlaylistTracks(playlistId: Long): List<Pair<String, String>> {
        val out = mutableListOf<Pair<String, String>>()
        try {
            val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("playlists").appendEncodedPath(playlistId.toString()).appendEncodedPath("files").build()
            context.contentResolver.query(uri, arrayOf("title_tag", "artist_tag"), null, null, "sort ASC")?.use { c ->
                val titleCol = c.getColumnIndex("title_tag")
                val artistCol = c.getColumnIndex("artist_tag")
                while(c.moveToNext()) {
                    val title = if (titleCol >= 0) c.getString(titleCol) ?: "" else ""
                    val artist = if (artistCol >= 0) c.getString(artistCol) ?: "" else ""
                    if (title.isNotBlank()) {
                        out.add(Pair(title, artist))
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return out
    }

    fun addToPlaylist(folderFileId: Long, playlistId: Long): Boolean {
        return try {
            val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("playlists").appendEncodedPath(playlistId.toString()).appendEncodedPath("files").build()
            val values = ContentValues().apply {
                put("folder_file_id", folderFileId)
            }
            context.contentResolver.insert(uri, values)
            
            val reloadIntent = Intent(PowerampAPI.ACTION_RELOAD_DATA).apply {
                putExtra(PowerampAPI.EXTRA_TABLE, "playlists")
            }
            PowerampAPIHelper.sendPAIntent(context, reloadIntent, false)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getTopPlayedTracks(limit: Int = 40): List<Pair<String, String>> {
        val tracks = mutableListOf<Pair<String, String>>()
        val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build()

        try {
            val cursor = context.contentResolver.query(
                uri,
                arrayOf(TableDefs.Files.TITLE_TAG, TableDefs.Files.ARTIST_TAG, "played_times"),
                "played_times > 0",
                null,
                "played_times DESC LIMIT $limit"
            )

            cursor?.use { c ->
                val titleCol = c.getColumnIndex("title_tag")
                val artistCol = c.getColumnIndex("artist_tag")

                while (c.moveToNext()) {
                    val title = if (titleCol >= 0) c.getString(titleCol) ?: "" else ""
                    val artist = if (artistCol >= 0) c.getString(artistCol) ?: "" else ""
                    tracks.add(Pair(title.ifEmpty { "Titel unbekannt" }, artist))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("PowerampController", "Error getting top played tracks: ${e.message}")
        }
        return tracks
    }

    fun getRecentlyAddedTracks(limit: Int = 20): List<Pair<String, String>> {
        val tracks = mutableListOf<Pair<String, String>>()
        val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build()

        try {
            val cursor = context.contentResolver.query(
                uri,
                arrayOf(TableDefs.Files.TITLE_TAG, TableDefs.Files.ARTIST_TAG, "file_created_at"),
                null,
                null,
                "file_created_at DESC LIMIT $limit"
            )

            cursor?.use { c ->
                val titleCol = c.getColumnIndex("title_tag")
                val artistCol = c.getColumnIndex("artist_tag")

                while (c.moveToNext()) {
                    val title = if (titleCol >= 0) c.getString(titleCol) ?: "" else ""
                    val artist = if (artistCol >= 0) c.getString(artistCol) ?: "" else ""
                    tracks.add(Pair(title.ifEmpty { "Titel unbekannt" }, artist))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("PowerampController", "Error getting recently added tracks: ${e.message}")
        }
        return tracks
    }

    fun getQueueSize(): Int {
        val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("queue").build()
        var count = 0
        try {
            val cursor = context.contentResolver.query(uri, arrayOf("_id"), null, null, null)
            count = cursor?.count ?: 0
            cursor?.close()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("PowerampController", "Error getting queue size: ${e.message}")
        }
        return count
    }

    fun getQueuePosition(trackId: Long): Int {
        val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("queue").build()
        var position = -1
        try {
            val cursor = context.contentResolver.query(
                uri,
                arrayOf("_id", "folder_file_id"), 
                null,
                null,
                "sort ASC"
            )

            cursor?.use { c ->
                val fileIdCol = c.getColumnIndex("folder_file_id")
                val idCol = c.getColumnIndexOrThrow("_id")
                
                var currentIndex = 0
                while (c.moveToNext()) {
                    val currentId = c.getLong(idCol)
                    if (currentId == trackId) {
                        position = currentIndex
                        break
                    }
                    currentIndex++
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("PowerampController", "Error getting queue position for $trackId: ${e.message}")
        }
        return position
    }

    fun getRecentQueueHistory(currentTrackId: Long, limit: Int = 10): String {
        val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("queue").build()
        val history = mutableListOf<String>()
        try {
            val cursor = context.contentResolver.query(
                uri,
                arrayOf("_id", "folder_file_id", "title", "artist"),
                null,
                null,
                "sort_order ASC"
            )

            cursor?.use { c ->
                val fileIdCol = c.getColumnIndex("folder_file_id")
                val idColToUse = if (fileIdCol != -1) fileIdCol else c.getColumnIndexOrThrow("_id")
                val titleCol = c.getColumnIndexOrThrow("title")
                val artistCol = c.getColumnIndexOrThrow("artist")
                
                val allTracks = mutableListOf<String>()
                var currentTrackIndex = -1
                var index = 0
                
                while (c.moveToNext()) {
                    val currentId = c.getLong(idColToUse)
                    val title = c.getString(titleCol) ?: ""
                    val artist = c.getString(artistCol) ?: ""
                    
                    allTracks.add("$title by $artist")
                    
                    if (currentId == currentTrackId) {
                        currentTrackIndex = index
                    }
                    index++
                }
                
                if (currentTrackIndex != -1) {
                    val startIndex = Math.max(0, currentTrackIndex - limit)
                    for (i in startIndex..currentTrackIndex) {
                        history.add(allTracks[i])
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("PowerampController", "Error getting queue history: ${e.message}")
        }
        
        return if (history.isNotEmpty()) {
            history.joinToString(", ")
        } else {
            ""
        }
    }

    fun getTopPlayedArtists(limit: Int = 10): List<Pair<String, Int>> {
        val artists = mutableListOf<Pair<String, Int>>()
        val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("artists").build()
        try {
            val cursor = context.contentResolver.query(
                uri,
                arrayOf("artist", "num_files"), 
                null,
                null,
                "num_files DESC LIMIT $limit"
            )
            cursor?.use { c ->
                val artistCol = c.getColumnIndex("artist")
                val countCol = c.getColumnIndex("num_files")
                while (c.moveToNext()) {
                    val artist = if (artistCol >= 0) c.getString(artistCol) ?: "" else ""
                    val count = if (countCol >= 0) c.getInt(countCol) else 0
                    if (artist.isNotBlank()) artists.add(Pair(artist, count))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return artists
    }

    fun getTopGenres(limit: Int = 10): List<Pair<String, Int>> {
        val genres = mutableListOf<Pair<String, Int>>()
        val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("genres").build()
        try {
            val cursor = context.contentResolver.query(
                uri,
                arrayOf("genre", "num_files"),
                null,
                null,
                "num_files DESC LIMIT $limit"
            )
            cursor?.use { c ->
                val genreCol = c.getColumnIndex("genre")
                val countCol = c.getColumnIndex("num_files")
                while (c.moveToNext()) {
                    val genre = if (genreCol >= 0) c.getString(genreCol) ?: "" else ""
                    val count = if (countCol >= 0) c.getInt(countCol) else 0
                    if (genre.isNotBlank()) genres.add(Pair(genre, count))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return genres
    }

    fun findArtist(artistName: String): Boolean {
        val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("artists").build()
        try {
            val cursor = context.contentResolver.query(
                uri,
                arrayOf("artist"),
                "artist = ?",
                arrayOf(artistName),
                null
            )
            val found = (cursor?.count ?: 0) > 0
            cursor?.close()
            return found
        } catch (e: Exception) {
            return false
        }
    }

    fun findAlbum(artistName: String, albumName: String): Boolean {
        val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("albums").build()
        try {
            val cursor = context.contentResolver.query(
                uri,
                arrayOf("album"),
                "artist = ? AND album = ?",
                arrayOf(artistName, albumName),
                null
            )
            val found = (cursor?.count ?: 0) > 0
            cursor?.close()
            return found
        } catch (e: Exception) {
            return false
        }
    }

    fun getRandomLocalTracks(limit: Int): List<PowerampTrack> {
        val tempIds = mutableListOf<Long>()
        val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build()
        try {
            context.contentResolver.query(uri, arrayOf(TableDefs.Files._ID), "title_tag IS NOT NULL AND title_tag != ''", null, "${TableDefs.Files._ID} DESC LIMIT 100")?.use { c ->
                val idCol = c.getColumnIndexOrThrow("_id")
                while (c.moveToNext()) {
                    tempIds.add(c.getLong(idCol))
                }
            }
        } catch (e: Exception) { e.printStackTrace() }
        return getTracksByIds(tempIds.shuffled().take(limit))
    }

    fun getTracksByArtist(artistName: String): List<PowerampTrack> {
        val tempIds = mutableListOf<Long>()
        val uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build()
        try {
            context.contentResolver.query(uri, arrayOf(TableDefs.Files._ID), "artist LIKE ? OR artist_tag LIKE ?", arrayOf("%$artistName%", "%$artistName%"), "${TableDefs.Files._ID} DESC LIMIT 50")?.use { c ->
                val idCol = c.getColumnIndexOrThrow("_id")
                while (c.moveToNext()) {
                    tempIds.add(c.getLong(idCol))
                }
            }
        } catch (e: Exception) { e.printStackTrace() }
        return getTracksByIds(tempIds)
    }
}
