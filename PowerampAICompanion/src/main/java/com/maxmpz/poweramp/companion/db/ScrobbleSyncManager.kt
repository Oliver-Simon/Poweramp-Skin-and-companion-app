package com.maxmpz.poweramp.companion.db

import android.content.Context
import android.util.Log
import com.maxmpz.poweramp.player.PowerampAPI
import com.maxmpz.poweramp.player.TableDefs
import com.maxmpz.poweramp.companion.ListenBrainzEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class ScrobbleSyncManager(private val context: Context) {

    private val db = ScrobbleDatabase.getDatabase(context)
    private val dao = db.scrobbleDao()
    private val prefs = context.getSharedPreferences("PowerampCompanionPrefs", Context.MODE_PRIVATE)

    private fun getRawColName(col: String): String {
        val dot = col.indexOf('.')
        if (dot >= 0 && dot + 1 < col.length) {
            return col.substring(dot + 1)
        }
        return col
    }

    suspend fun syncFull() = withContext(Dispatchers.IO) {
        Log.d("ScrobbleSync", "--- FULL SYNC START ---")
        syncFromPoweramp()
        syncFromCsv()
        syncFromLastFmApi()
        enrichSonicData(limit = 50) // Enrich up to 50 tracks during a full sync
        Log.d("ScrobbleSync", "--- FULL SYNC END ---")
    }

    private suspend fun syncFromPoweramp() {
        Log.d("ScrobbleSync", "Starting Poweramp sync...")
        val filesUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build()
        // Use explicit table names for ALL columns to avoid ambiguity in JOINed views
        val projection = arrayOf(
            TableDefs.Files._ID,
            "artist",
            TableDefs.Files.TITLE_TAG,
            "album",
            TableDefs.Files.PLAYED_AT,
            TableDefs.Files.DURATION
        )
        
        val scrobbles = mutableListOf<ScrobbleEntity>()
        try {
            // Poweramp's provider sometimes fails with ambiguity errors when selection is used.
            // We'll query all files and filter in Kotlin for safety.
            context.contentResolver.query(filesUri, projection, null, null, null)?.use { cursor ->
                val idCol = cursor.getColumnIndex(getRawColName(TableDefs.Files._ID))
                val artistCol = cursor.getColumnIndex("artist")
                val titleCol = cursor.getColumnIndex(getRawColName(TableDefs.Files.TITLE_TAG))
                val albumCol = cursor.getColumnIndex("album")
                val playedAtCol = cursor.getColumnIndex(getRawColName(TableDefs.Files.PLAYED_AT))
                val durCol = cursor.getColumnIndex(getRawColName(TableDefs.Files.DURATION))

                Log.d("ScrobbleSync", "Poweramp query returned ${cursor.count} results")

                while (cursor.moveToNext()) {
                    val trackId = if (idCol != -1) cursor.getLong(idCol) else -1L
                    val artist = if (artistCol != -1) cursor.getString(artistCol) ?: "Unknown" else "Unknown"
                    val title = if (titleCol != -1) cursor.getString(titleCol) ?: "Unknown" else "Unknown"
                    val album = if (albumCol != -1) cursor.getString(albumCol) ?: "" else ""
                    var timestamp = if (playedAtCol != -1) cursor.getLong(playedAtCol) else 0L
                    val duration = if (durCol != -1) cursor.getInt(durCol).toLong() else 0L

                    if (timestamp > 0 && timestamp < 10000000000L) {
                        timestamp *= 1000L
                    }

                    if (timestamp > 0) {
                        scrobbles.add(ScrobbleEntity(
                            trackId = trackId,
                            title = title,
                            artist = artist,
                            album = album,
                            timestamp = timestamp,
                            durationMs = duration
                        ))
                    }
                }
            }
            if (scrobbles.isNotEmpty()) {
                dao.insertAll(scrobbles)
            }
            Log.d("ScrobbleSync", "Poweramp sync completed. Processed: ${scrobbles.size}")
        } catch (e: Exception) {
            Log.e("ScrobbleSync", "Poweramp sync failed", e)
        }
    }

    private suspend fun syncFromCsv() {
        Log.d("ScrobbleSync", "Starting CSV import...")
        
        val scrobbles = mutableListOf<ScrobbleEntity>()
        try {
            val resId = context.resources.getIdentifier("lastfm_history", "raw", context.packageName)
            if (resId == 0) {
                Log.w("ScrobbleSync", "lastfm_history.csv not found in res/raw")
                return
            }

            val inputStream = context.resources.openRawResource(resId)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US)

            reader.useLines { lines ->
                lines.forEach { line ->
                    val parts = line.split(",")
                    if (parts.size >= 4) {
                        try {
                            val artist = parts[0].trim()
                            val album = parts[1].trim()
                            val title = parts[2].trim()
                            val dateStr = parts[3].trim()
                            val date = sdf.parse(dateStr)
                            
                            if (date != null) {
                                scrobbles.add(ScrobbleEntity(
                                    trackId = -1,
                                    title = title,
                                    artist = artist,
                                    album = album,
                                    timestamp = date.time,
                                    durationMs = 0
                                ))
                            }
                        } catch (e: Exception) {
                            // Suppress per-line errors
                        }
                    }
                }
            }
            
            // Batch insert for performance
            if (scrobbles.isNotEmpty()) {
                // Split into chunks to avoid SQLite bind limit if needed, but insertAll should handle reasonably
                scrobbles.chunked(500).forEach { chunk ->
                    dao.insertAll(chunk)
                }
            }
            
            prefs.edit().putBoolean("csv_imported", true).apply()
            Log.d("ScrobbleSync", "CSV import completed. Processed: ${scrobbles.size} lines")
        } catch (e: Exception) {
            Log.e("ScrobbleSync", "CSV import failed", e)
        }
    }

    private suspend fun syncFromLastFmApi() {
        val apiKey = prefs.getString("lastfm_api_key", "")
        val username = prefs.getString("lastfm_username", "")
        
        if (apiKey.isNullOrBlank() || username.isNullOrBlank()) {
            Log.w("ScrobbleSync", "Last.fm credentials missing, skipping API sync")
            return
        }

        Log.d("ScrobbleSync", "Starting Last.fm API sync for $username...")
        val scrobbles = mutableListOf<ScrobbleEntity>()
        try {
            val urlString = "https://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=$username&api_key=$apiKey&format=json&limit=200"
            val url = URL(urlString)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connectTimeout = 5000
            conn.readTimeout = 5000
            
            val response = conn.inputStream.bufferedReader().use { it.readText() }
            val json = JSONObject(response)
            
            if (!json.has("recenttracks")) {
                Log.e("ScrobbleSync", "Invalid API response: $response")
                return
            }
            
            val tracks = json.getJSONObject("recenttracks").optJSONArray("track")
            if (tracks != null) {
                for (i in 0 until tracks.length()) {
                    val trackObj = tracks.getJSONObject(i)
                    val artist = trackObj.getJSONObject("artist").getString("#text")
                    val album = trackObj.getJSONObject("album").getString("#text")
                    val title = trackObj.getString("name")
                    
                    if (trackObj.has("date")) {
                        val timestamp = trackObj.getJSONObject("date").getLong("uts") * 1000L
                        scrobbles.add(ScrobbleEntity(
                            trackId = -1,
                            title = title,
                            artist = artist,
                            album = album,
                            timestamp = timestamp,
                            durationMs = 0
                        ))
                    }
                }
            }
            if (scrobbles.isNotEmpty()) {
                dao.insertAll(scrobbles)
            }
            Log.d("ScrobbleSync", "Last.fm API sync completed. Processed: ${scrobbles.size}")
        } catch (e: Exception) {
            Log.e("ScrobbleSync", "Last.fm API sync failed", e)
        }
    }

    suspend fun fetchLastFmTopTracks(limit: Int = 100): List<Pair<String, String>> = withContext(Dispatchers.IO) {
        val apiKey = prefs.getString("lastfm_api_key", "")
        val username = prefs.getString("lastfm_username", "")
        val results = mutableListOf<Pair<String, String>>()
        
        if (apiKey.isNullOrBlank() || username.isNullOrBlank()) {
            Log.w("ScrobbleSync", "Last.fm credentials missing, skipping getTopTracks")
            return@withContext results
        }

        try {
            val urlString = "https://ws.audioscrobbler.com/2.0/?method=user.gettoptracks&user=$username&api_key=$apiKey&format=json&limit=$limit&period=overall"
            val url = URL(urlString)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connectTimeout = 3000
            conn.readTimeout = 3000
            
            val response = conn.inputStream.bufferedReader().use { it.readText() }
            val json = JSONObject(response)
            
            val tracks = json.optJSONObject("toptracks")?.optJSONArray("track")
            if (tracks != null) {
                for (i in 0 until tracks.length()) {
                    val trackObj = tracks.getJSONObject(i)
                    val artist = trackObj.getJSONObject("artist").getString("name")
                    val title = trackObj.getString("name")
                    results.add(Pair(artist, title))
                }
            }
        } catch (e: Exception) {
            Log.e("ScrobbleSync", "Last.fm getTopTracks failed", e)
        }
        return@withContext results
    }

    suspend fun enrichSonicData(limit: Int = 20, forceTracks: List<ScrobbleEntity>? = null) = withContext(Dispatchers.IO) {
        Log.d("ScrobbleSync", "Starting sonic enrichment for ${forceTracks?.size ?: limit} tracks...")
        val engine = ListenBrainzEngine()
        val unenriched = forceTracks ?: dao.getUnenrichedTracks(limit)
        
        if (unenriched.isEmpty()) {
            Log.d("ScrobbleSync", "No tracks to enrich.")
            return@withContext
        }

        var successCount = 0
        for (scrobble in unenriched) {
            try {
                Log.d("ScrobbleSync", "Enriching: ${scrobble.artist} - ${scrobble.title}")
                val trackInfo = engine.lookupTrack(scrobble.artist, scrobble.title)
                val mbid = trackInfo?.get("mbid")
                
                if (mbid != null) {
                    val features = engine.fetchSonicFeatures(mbid)
                    dao.updateSonicData(
                        artist = scrobble.artist,
                        title = scrobble.title,
                        mbid = mbid,
                        bpm = features?.bpm,
                        energy = features?.energy,
                        danceability = features?.danceability,
                        mood = features?.mood
                    )
                    successCount++
                } else {
                    // Mark as "not found" by setting a placeholder MBID to prevent re-querying every time
                    dao.updateSonicData(
                        artist = scrobble.artist,
                        title = scrobble.title,
                        mbid = "not_found",
                        bpm = null, energy = null, danceability = null, mood = null
                    )
                }
                
                // Be nice to ListenBrainz API
                delay(1000L)
            } catch (e: Exception) {
                Log.e("ScrobbleSync", "Failed to enrich ${scrobble.title}", e)
            }
        }
        Log.d("ScrobbleSync", "Sonic enrichment pass finished. Success: $successCount/${forceTracks?.size ?: limit}")
    }

    suspend fun enrichSpecificTracks(tracks: List<Pair<String, String>>) {
        val entities = tracks.mapNotNull { (artist, title) ->
            dao.getScrobble(artist, title) // Assuming this exists or we need to add it
        }.filter { it.musicbrainzId == null }
        
        if (entities.isNotEmpty()) {
            enrichSonicData(forceTracks = entities)
        }
    }
}
