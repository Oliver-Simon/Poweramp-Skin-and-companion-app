package com.maxmpz.poweramp.companion

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URLEncoder

class ListenBrainzEngine {
    private val client = OkHttpClient()
    private val BASE_URL = "https://api.listenbrainz.org/1"

    /**
     * Looks up metadata for a track, primarily to find its MusicBrainz ID (MBID).
     */
    fun lookupTrack(artist: String, title: String): Map<String, String>? {
        val encodedArtist = URLEncoder.encode(artist, "UTF-8")
        val encodedTitle = URLEncoder.encode(title, "UTF-8")
        val url = "$BASE_URL/metadata/lookup/?artist_name=$encodedArtist&recording_name=$encodedTitle&metadata=True"

        return try {
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return null
                val body = response.body?.string() ?: return null
                val json = JSONObject(body)
                
                val result = mutableMapOf<String, String>()
                val mbid = json.optString("recording_mbid", "")
                if (mbid.isNotEmpty()) {
                    result["mbid"] = mbid
                }
                
                // We could also extract tags here if available
                result
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Attempts to fetch sonic features (BPM, Mood, etc.) using the MBID.
     * Note: This often relies on legacy AcousticBrainz data integrated into the ecosystem.
     */
    fun fetchSonicFeatures(mbid: String): SonicFeatures? {
        // Legacy AcousticBrainz endpoint might still work or there's a ListenBrainz metadata correlate
        // For now, let's try the metadata recording info which often contains tags
        val url = "$BASE_URL/metadata/recording/?recording_mbid=$mbid"
        
        return try {
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return null
                val body = response.body?.string() ?: return null
                val json = JSONObject(body)
                
                // Extract possible sonic attributes from tags/metadata
                var bpm: Float? = null
                var mood: String? = null
                var energy: Float? = null
                
                // ListenBrainz metadata recording info can have 'tags'
                val tagsArray = json.optJSONArray("tags")
                val tags = mutableListOf<String>()
                if (tagsArray != null) {
                    for (i in 0 until tagsArray.length()) {
                        val tag = tagsArray.getString(i).lowercase()
                        tags.add(tag)
                        
                        // Heuristic: Some tags might indicate BPM or energy
                        if (tag.contains("bpm")) {
                            // Try to extract number: "128bpm"
                            val match = Regex("(\\d+)").find(tag)
                            match?.value?.toFloatOrNull()?.let { bpm = it }
                        }
                        if (tag == "fast" || tag == "energetic" || tag == "workout") energy = 0.8f
                        if (tag == "slow" || tag == "chill" || tag == "relaxed") energy = 0.3f
                    }
                }
                
                if (mood == null && tags.isNotEmpty()) {
                    mood = tags.take(3).joinToString(", ")
                }

                SonicFeatures(
                    bpm = bpm,
                    energy = energy,
                    danceability = if (tags.contains("dance")) 0.8f else null,
                    mood = mood
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    data class SonicFeatures(
        val bpm: Float?,
        val energy: Float?,
        val danceability: Float?,
        val mood: String?
    )
}
