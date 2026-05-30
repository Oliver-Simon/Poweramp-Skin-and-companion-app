package com.maxmpz.poweramp.companion

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import android.net.Uri
import kotlinx.coroutines.*
import kotlinx.coroutines.runBlocking
import com.maxmpz.poweramp.companion.db.ScrobbleSyncManager
import org.json.JSONObject
import org.json.JSONArray
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaType
import java.net.HttpURLConnection
import java.net.URL

data class Scrobble(
    val artist: String,
    val album: String,
    val track: String,
    val timestamp: Long,
    val hour: Int,
    val year: Int,
    val dayOfYear: Int,
    val bpm: Float? = null,
    val energy: Float? = null,
    val danceability: Float? = null,
    val mood: String? = null
)

data class DiscoveryItem(
    val title: String,
    val artist: String,
    val reason: String,
    val type: String, // "ALBUM", "SONG", "ARTIST"
    var imageUrl: String? = null,
    val localTrackId: Long? = null,
    val targetPlaylistId: Long? = null,
    val targetPlaylistName: String? = null
)

data class DiscoveryIntent(
    val artists: List<String>,
    val keywords: List<String>,
    val tags: List<String>
)

data class ItunesResult(
    val artist: String,
    val title: String,
    val imageUrl: String,
    val genre: String?
)

class RecommendationEngine(private val context: Context, private val powerampController: PowerampController) {

    companion object {
        var lastDebugInfo = "No scan performed yet."
    }

    fun parseAndRecommend(prompt: String? = null, seedPlaylistId: Long? = null, tweakTracks: List<PowerampTrack>? = null, disableAi: Boolean = false, onLog: (String) -> Unit = {}): List<PowerampTrack> {
        val prefs = context.getSharedPreferences("PowerampCompanionPrefs", Context.MODE_PRIVATE)
        val provider = prefs.getString("ai_provider", "Gemini") ?: "Gemini"
        val apiKey = prefs.getString("ai_key_$provider", prefs.getString("gemini_api_key", "")) ?: ""
        val modelName = prefs.getString("ai_model", "gemini-2.0-flash") ?: "gemini-2.0-flash"
        val baseUrl = prefs.getString("ai_base_url", "") ?: ""
        val blacklist = prefs.getString("blacklist_filter", "") ?: ""
        
        // Early return: Megamix bypasses all other logic
        if (prompt != null && prompt.lowercase(java.util.Locale.getDefault()).contains("megamix")) {
            onLog("Megamix erkannt! Starte Multi-Source-Generierung...")
            return runMegamix(onLog)
        }
        
        // 1. Try AI Intent Extraction if API Key is configured and not explicitly disabled
        val isMagicToken = prompt?.startsWith("MAGIC_TOKEN_") == true
        
        var intent: DiscoveryIntent? = null
        if (!disableAi && !isMagicToken && !apiKey.isNullOrBlank() && (prompt != null && prompt.isNotBlank() || seedPlaylistId != null || tweakTracks != null)) {
            val seedContext = if (tweakTracks != null) {
                onLog("Analysiere Iterations-Wunsch über die ${tweakTracks.size} Tracks der aktiven Playlist...")
                val trackStrings = tweakTracks.take(100).joinToString(", ") { "${it.artist} - ${it.title}" }
                "\n\nDer User hat aktuell folgende Playlist vorliegen:\n$trackStrings\n\nSein Änderungswunsch: '$prompt'. Analysiere den Wunsch im Kontext der vorhandenen Playlist und generiere Tags/Artists, die diesen Mix musikalisch in die gewünschte Richtung korrigieren oder iterativ weiterentwickeln!"
            } else if (seedPlaylistId != null) {
                onLog("Analysiere Basis-Playlist für einen Mix...")
                val rawTracks = powerampController.getPlaylistTracks(seedPlaylistId).shuffled().take(200)
                val trackStrings = rawTracks.joinToString(", ") { "${it.first} - ${it.second}" }
                "\n\nDer User hat diese bestehende Playlist als Basis ausgewählt:\n$trackStrings\n\nAnalysiere den Vibe dieser Tracks. Generiere aus den erkannten Künstlern und Genres einen Mix, der den Stil der Playlist organisch weiterführt!"
            } else ""

            val combinedPrompt = if (tweakTracks != null) seedContext else (prompt ?: "") + seedContext

            try {
                onLog("Verwende $provider ($modelName)... Extrahiere Intent...")
                android.util.Log.d("RecommendationEngine", "Extracting intent with $provider model $modelName for: $combinedPrompt")
                intent = extractIntentWithAi(provider, apiKey, modelName, baseUrl, combinedPrompt, blacklist, onLog)
            } catch (e: Exception) {
                e.printStackTrace()
                onLog("Error während $provider Intent Extraction: ${e.message}")
                android.util.Log.e("RecommendationEngine", "Intent extraction error", e)
            }
        }
        
        // 2. Build local/global seed tracks based on intent or fallback
        if (intent != null) {
            onLog("Triggering targeted sonic enrichment for intent seeds...")
            
            if (seedPlaylistId != null || tweakTracks != null) {
                val baseArtists = if (tweakTracks != null) {
                    tweakTracks.map { it.artist }.filter { it.isNotBlank() }.distinct().shuffled().take(5)
                } else {
                    val baseTracks = powerampController.getPlaylistTracks(seedPlaylistId!!)
                    baseTracks.map { it.first }.filter { it.isNotBlank() }.distinct().shuffled().take(5)
                }
                
                val combinedArtists = intent!!.artists.toMutableSet()
                combinedArtists.addAll(baseArtists)
                intent = intent!!.copy(artists = combinedArtists.toList())
                onLog("Verstärke KI-Fokus durch Basis-Künstler: ${baseArtists.joinToString(", ")}")
            }
            
            val syncManager = ScrobbleSyncManager(context)
            val seedPairs = mutableListOf<Pair<String, String>>()
            intent!!.artists.forEach { artist -> seedPairs.add(Pair(artist, "")) }
            // Note: ScrobbleSyncManager.enrichSpecificTracks might need specific title too, 
            // but we'll try to enrich what we can find. For now, we'll let it happen in background.

            val hybridTracks = runHybridPlaylisting(prompt, intent, apiKey, onLog)
            if (hybridTracks.isNotEmpty()) {
                return hybridTracks
            }
        }
        
        if (seedPlaylistId != null && intent == null) {
            onLog("Erstelle lokalen Mix aus der Basis-Playlist (ohne KI)...")
            val rawTracks = powerampController.getPlaylistTracks(seedPlaylistId)
            val artists = rawTracks.map { it.first }.filter { it.isNotBlank() }.distinct().shuffled()
            val localTracks = mutableSetOf<PowerampTrack>()
            for (artist in artists.take(50)) {
                localTracks.addAll(powerampController.getTracksByArtist(artist))
            }
            if (localTracks.isNotEmpty()) {
                onLog("Lokaler Mix generiert!")
                val randomLimit = (100..300).random()
                return localTracks.shuffled().take(randomLimit)
            }
        }
        
        onLog("No Gemini Key, empty prompt, or API failed. Using local DB heuristics...")
        val recommendedPairs = runLocalHeuristics(prompt)
        
        if (recommendedPairs.isEmpty() || (recommendedPairs.size == 1 && recommendedPairs[0].first == "MAGIC_TOKEN_FALLBACK")) {
            onLog("Fallback activated: Selecting top favorite tracks instead.")
            return powerampController.findTracks(listOf(Pair("MAGIC_TOKEN_FALLBACK", "MAGIC_TOKEN_FALLBACK")), onLog)
        }
        
        onLog("Analyzed Last.fm / DB mapping.")
        val mappedTracks = powerampController.findTracks(recommendedPairs, onLog)
        
        if (mappedTracks.isEmpty()) {
            onLog("No exact matches found. Generating smart fallback mix...")
            return powerampController.findTracks(listOf(Pair("MAGIC_TOKEN_FALLBACK", "MAGIC_TOKEN_FALLBACK")), onLog)
        }
        
        return mappedTracks
    }
    
    private fun extractIntentWithAi(
        provider: String,
        apiKey: String,
        modelName: String,
        baseUrl: String,
        prompt: String,
        blacklist: String,
        onLog: (String) -> Unit
    ): DiscoveryIntent? {
        val systemPrompt = """
            You are a smart AI music assistant. Analyze the user's music request and extract the core intent into a JSON object.
            
            User request: "$prompt"
            Blacklist (ignore these): "$blacklist"
            
            IMPORTANT: Look for sonic and acoustic preferences:
            - If the user wants something fast/tempo-driven, include "High Energy" and a "BPM" keyword like "140 BPM".
            - If the user wants something calm/relaxed/slow, include "Low Energy".
            - If the user wants to dance/party, include "Party".
            - Map specific moods to tags (e.g., "sad" -> "Melancholic").
            
            Return exactly a JSON object with this schema:
            {
                "tags": ["genre or mood tag 1", "tag 2"],
                "artists": ["artist name 1"],
                "keywords": ["specific keywords, includes 'High Energy', 'Low Energy', 'Party' or 'XXX BPM' if applicable"]
            }
            
            Keep lists concise (max 3 items each). If none apply, leave the array empty.
        """.trimIndent()

        return if (provider == "Gemini") {
            extractIntentWithGeminiSDK(apiKey, modelName, systemPrompt, onLog)
        } else {
            extractIntentWithOpenAICompatible(provider, apiKey, modelName, baseUrl, systemPrompt, onLog)
        }
    }

    private fun extractIntentWithGeminiSDK(apiKey: String, modelName: String, systemPrompt: String, onLog: (String) -> Unit): DiscoveryIntent? {
        val generativeModel = GenerativeModel(
            modelName = modelName,
            apiKey = apiKey,
            generationConfig = generationConfig {
                responseMimeType = "application/json"
            }
        )
        
        val inputContent = content { text(systemPrompt) }
        
        val responseText = try {
            val response = runBlocking { generativeModel.generateContent(inputContent) }
            val text = response.text ?: "{}"
            android.util.Log.d("RecommendationEngine", "Gemini Intent Response: $text")
            text
        } catch (e: Exception) {
            e.printStackTrace()
            val isQuotaError = e.message?.contains("quota", ignoreCase = true) == true
            val errorDetails = if (isQuotaError) "Kontingent erschöpft (429)" else e.message?.take(100)
            
            onLog("Gemini Fehler: $errorDetails. Versuche Fallback 1.5-Flash...")
            android.util.Log.w("RecommendationEngine", "Gemini failed ($errorDetails), trying gemini-1.5-flash")
            
            try {
                val model15Flash = GenerativeModel("gemini-1.5-flash", apiKey, generationConfig { responseMimeType = "application/json" })
                val resp = runBlocking { model15Flash.generateContent(inputContent) }
                resp.text ?: "{}"
            } catch (e2: Exception) {
                "{}"
            }
        }
        return parseIntentJson(responseText)
    }

    private fun extractIntentWithOpenAICompatible(
        provider: String,
        apiKey: String,
        modelName: String,
        baseUrl: String,
        systemPrompt: String,
        onLog: (String) -> Unit
    ): DiscoveryIntent? {
        val client = OkHttpClient()
        
        val endpoint = when(provider) {
            "Groq" -> "https://api.groq.com/openai/v1/chat/completions"
            "OpenRouter" -> "https://openrouter.ai/api/v1/chat/completions"
            "Custom OpenAI" -> baseUrl
            else -> "https://api.openai.com/v1/chat/completions"
        }

        val jsonBody = JSONObject().apply {
            put("model", modelName)
            put("messages", JSONArray().apply {
                put(JSONObject().apply {
                    put("role", "user")
                    put("content", systemPrompt)
                })
            })
            put("response_format", JSONObject().apply { put("type", "json_object") })
        }

        val request = Request.Builder()
            .url(endpoint)
            .addHeader("Authorization", "Bearer $apiKey")
            .addHeader("Content-Type", "application/json")
            .apply {
                if (provider == "OpenRouter") {
                    addHeader("HTTP-Referer", "https://github.com/maxmpz/powerampapi")
                    addHeader("X-Title", "Poweramp AI Companion")
                }
            }
            .post(RequestBody.create("application/json".toMediaType(), jsonBody.toString()))
            .build()

        return try {
            val response = runBlocking(Dispatchers.IO) { client.newCall(request).execute() }
            val body = response.body?.string()
            android.util.Log.d("RecommendationEngine", "$provider Response: $body")
            
            if (response.isSuccessful && body != null) {
                val root = JSONObject(body)
                val choices = root.getJSONArray("choices")
                if (choices.length() > 0) {
                    val content = choices.getJSONObject(0).getJSONObject("message").getString("content")
                    parseIntentJson(content)
                } else null
            } else {
                onLog("$provider Fehler (${response.code})")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            onLog("$provider Exception: ${e.message}")
            null
        }
    }

    private fun parseIntentJson(json: String): DiscoveryIntent? {
        return try {
            val root = JSONObject(json)
            val tags = mutableListOf<String>()
            val tagsArray = root.optJSONArray("tags")
            if (tagsArray != null) {
                for (i in 0 until tagsArray.length()) {
                    tags.add(tagsArray.getString(i))
                }
            }
            
            val artists = mutableListOf<String>()
            val artistsArray = root.optJSONArray("artists")
            if (artistsArray != null) {
                for (i in 0 until artistsArray.length()) {
                    artists.add(artistsArray.getString(i))
                }
            }
            
            val keywords = mutableListOf<String>()
            val keywordsArray = root.optJSONArray("keywords")
            if (keywordsArray != null) {
                for (i in 0 until keywordsArray.length()) {
                    keywords.add(keywordsArray.getString(i))
                }
            }
            
            DiscoveryIntent(artists, keywords, tags)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun generateInsights(
        apiKey: String, // Kept for signature compatibility, but we now read more from prefs
        topTracks: List<Pair<String, String>>,
        recentTracks: List<Pair<String, String>>,
        blacklist: String,
        onLog: (String) -> Unit = {}
    ): String {
        return withContext(Dispatchers.IO) {
            try {
                val prefs = context.getSharedPreferences("PowerampCompanionPrefs", Context.MODE_PRIVATE)
                val provider = prefs.getString("ai_provider", "Gemini") ?: "Gemini"
                val apiKeyForProvider = prefs.getString("ai_key_$provider", prefs.getString("gemini_api_key", "")) ?: ""
                val modelName = prefs.getString("ai_model", "gemini-2.0-flash") ?: "gemini-2.0-flash"
                val baseUrl = prefs.getString("ai_base_url", "") ?: ""

                if (topTracks.isEmpty() && recentTracks.isEmpty()) {
                    return@withContext "Deine Bibliothek scheint noch ziemlich leer oder frisch zu sein. Hör erst ein paar Songs, damit ich deinen Musikgeschmack analysieren kann! ✨"
                }

                val promptLines = StringBuilder()
                promptLines.append("Du bist ein Musik-Experte, DJ und eine Art 'Spotify Wrapped' Analysator. Analysiere den folgenden Musikgeschmack basierend auf den meistgehörten und zuletzt hinzugefügten Songs aus einer lokalen Poweramp Bibliothek.\n\n")
                
                if (topTracks.isNotEmpty()) {
                    promptLines.append("MEISTGEHÖRTE SONGS (All-Time Favorites):\n")
                    topTracks.take(40).forEach { promptLines.append("- ${it.first} von ${it.second}\n") }
                }
                
                if (recentTracks.isNotEmpty()) {
                    promptLines.append("\nZULETZT GEHÖRT/HINZUGEFÜGT (Aktueller Vibe):\n")
                    recentTracks.take(20).forEach { promptLines.append("- ${it.first} von ${it.second}\n") }
                }

                val blacklistInstruction = if (blacklist.isNotBlank()) "WICHTIG: Folgende Künstler oder Genres GÄNZLICH IGNORIEREN UND AUSSCHLIESSEN (Blacklist): $blacklist\n" else ""
                
                promptLines.append("\nAUFGABE:\n")
                promptLines.append(blacklistInstruction)
                promptLines.append("1. Schreibe eine sehr kurze, knackige, lustige und emotionale Charakter-Analyse (max 4 Sätze) zu diesem Musikgeschmack.\n")
                promptLines.append("2. Schlage dann GENAU 3 empfehlenswerte, komplett NEUE Künstler oder Songs vor, die NICHT in der obigen Liste stehen, aber perfekt zu diesem aktuellen Vibe passen (Music Discovery!).\n")
                promptLines.append("Verwende Emojis. Mache es cool und modern. Antworte auf Deutsch.")

                val response = performAiCompletion(provider, apiKeyForProvider, modelName, baseUrl, promptLines.toString(), false, onLog)
                response ?: "Konnte keine Insights generieren."
            } catch (e: Exception) {
                e.printStackTrace()
                "Fehler bei der KI-Analyse: ${e.message}"
            }
        }
    }

    private suspend fun performAiCompletion(
        provider: String,
        apiKey: String,
        modelName: String,
        baseUrl: String,
        prompt: String,
        isJson: Boolean,
        onLog: (String) -> Unit
    ): String? {
        if (provider == "Gemini") {
            return try {
                val generationConfig = if (isJson) generationConfig { responseMimeType = "application/json" } else generationConfig {}
                val model = GenerativeModel(modelName = modelName, apiKey = apiKey, generationConfig = generationConfig)
                val response = model.generateContent(prompt)
                response.text
            } catch (e: Exception) {
                onLog("Gemini Fehler: ${e.message}. Versuche Fallback 1.5-Flash...")
                try {
                    val fallbackModel = GenerativeModel(modelName = "gemini-1.5-flash", apiKey = apiKey)
                    val response = fallbackModel.generateContent(prompt)
                    response.text
                } catch (e2: Exception) {
                    null
                }
            }
        } else {
            // OpenAI Compatible (Groq, OpenRouter, etc.)
            val client = okhttp3.OkHttpClient()
            val endpoint = when(provider) {
                "Groq" -> "https://api.groq.com/openai/v1/chat/completions"
                "OpenRouter" -> "https://openrouter.ai/api/v1/chat/completions"
                "Custom OpenAI" -> baseUrl
                else -> "https://api.openai.com/v1/chat/completions"
            }

            val jsonBody = org.json.JSONObject().apply {
                put("model", modelName)
                put("messages", org.json.JSONArray().apply {
                    put(org.json.JSONObject().apply {
                        put("role", "user")
                        put("content", prompt)
                    })
                })
                if (isJson) {
                    put("response_format", org.json.JSONObject().apply { put("type", "json_object") })
                }
            }

            val request = okhttp3.Request.Builder()
                .url(endpoint)
                .addHeader("Authorization", "Bearer $apiKey")
                .addHeader("Content-Type", "application/json")
                .apply {
                    if (provider == "OpenRouter") {
                        addHeader("HTTP-Referer", "https://github.com/maxmpz/powerampapi")
                        addHeader("X-Title", "Poweramp AI Companion")
                    }
                }
                .post(jsonBody.toString().toRequestBody("application/json".toMediaType()))
                .build()

            return try {
                val response = client.newCall(request).execute()
                val body = response.body?.string()
                if (response.isSuccessful && body != null) {
                    val root = org.json.JSONObject(body)
                    val choices = root.getJSONArray("choices")
                    if (choices.length() > 0) {
                        choices.getJSONObject(0).getJSONObject("message").getString("content")
                    } else null
                } else {
                    onLog("$provider Fehler (${response.code})")
                    null
                }
            } catch (e: Exception) {
                onLog("$provider Exception: ${e.message}")
                null
            }
        }
    }

    suspend fun scanForGaps(apiKey: String, type: String = "ALL", playlistId: Long? = null): List<DiscoveryItem> {
        lastDebugInfo = "Local Smart Scan started (Type: $type, Playlist: $playlistId)...\n"
        Log.d("RecommendationEngine", "scanForGaps invoked -> Type: $type, PlaylistId: $playlistId")
        return withContext(Dispatchers.IO) {
            try {
                if (playlistId != null) {
                    val plRes = runPlaylistDiscovery(apiKey, type, playlistId)
                    Log.d("RecommendationEngine", "runPlaylistDiscovery returned ${plRes.size} items.")
                    return@withContext plRes
                }

                // Replacing getTopPlayedArtists call with local calculation from full library
                val libraryJson = powerampController.getFullLibraryJsonForGemini()
                val allTopArtistsFull = JSONArray(libraryJson)
                val artistStatsMap = mutableMapOf<String, Int>()
                for (i in 0 until allTopArtistsFull.length()) {
                    val obj = allTopArtistsFull.getJSONObject(i)
                    val artist = obj.optString("a", "Unknown")
                    val played = obj.optInt("p", 0)
                    artistStatsMap[artist] = (artistStatsMap[artist] ?: 0) + played
                }
                val allTopArtists = artistStatsMap.toList().sortedByDescending { it.second }.take(50)
                if (allTopArtists.isEmpty()) {
                    lastDebugInfo = "No top artists found in Poweramp DB."
                    return@withContext emptyList()
                }

                val selectedArtists = allTopArtists.shuffled().take(10)
                lastDebugInfo += "Sampling from: ${selectedArtists.joinToString { it.first }}\n"

                val results = mutableListOf<DiscoveryItem>()
                val genresToExplore = mutableSetOf<String>()

                for (artistPair in selectedArtists) {
                    if (results.size >= 15) break
                    val artistName = artistPair.first

                    // 1. Check for Songs
                    if (type == "ALL" || type == "SONG") {
                        lastDebugInfo += "Checking songs for $artistName...\n"
                        val itunesSongs = fetchFromItunes(artistName, "musicTrack", 10)
                        for (song in itunesSongs) {
                            if (results.size >= 15) break
                            if (results.any { it.artist == artistName && it.type == "SONG" }) continue
                            
                            if (isSongMissingLocally(song.artist, song.title)) {
                                results.add(DiscoveryItem(
                                    title = song.title,
                                    artist = song.artist,
                                    reason = "Ein beliebter Song von $artistName, den du noch nicht hast.",
                                    type = "SONG",
                                    imageUrl = song.imageUrl
                                ))
                                song.genre?.let { genresToExplore.add(it) }
                            }
                        }
                    }

                    // 2. Check for Albums
                    if (results.size < 15 && (type == "ALL" || type == "ALBUM")) {
                        lastDebugInfo += "Checking albums for $artistName...\n"
                        val itunesAlbums = fetchFromItunes(artistName, "album", 5)
                        for (album in itunesAlbums) {
                            if (results.size >= 15) break
                            if (results.any { it.artist == artistName && it.type == "ALBUM" }) continue
                            
                            if (!powerampController.findAlbum(album.artist, album.title)) {
                                results.add(DiscoveryItem(
                                    title = album.title,
                                    artist = album.artist,
                                    reason = "Dieses Album von $artistName fehlt in deiner Sammlung.",
                                    type = "ALBUM",
                                    imageUrl = album.imageUrl
                                ))
                            }
                        }
                    }
                }

                // 3. Check for Artists (Similarity/Genre exploration)
                if (results.size < 15 && (type == "ALL" || type == "ARTIST")) {
                    if (genresToExplore.isEmpty()) {
                        lastDebugInfo += "No temporary genres found, fetching top genres from DB...\n"
                        // Replace getTopGenres with library scan if not available
                        val libraryJson = powerampController.getFullLibraryJsonForGemini()
                        // Fallback: If we can't get genres easily, just use a few common ones
                        genresToExplore.add("Rock")
                        genresToExplore.add("Pop")
                        genresToExplore.add("Electronic")
                    }
                    
                    if (genresToExplore.isNotEmpty()) {
                        val randomGenre = genresToExplore.shuffled().first()
                        lastDebugInfo += "Exploring new artists in genre: $randomGenre...\n"
                        val genericArtists = fetchFromItunes(randomGenre, "musicArtist", 10)
                        for (ga in genericArtists) {
                            if (results.size >= 15) break
                            if (!powerampController.findArtist(ga.artist)) {
                                results.add(DiscoveryItem(
                                    title = ga.artist,
                                    artist = "Neuer Künstler",
                                    reason = "Weil du gerne $randomGenre hörst.",
                                    type = "ARTIST",
                                    imageUrl = ga.imageUrl
                                ))
                            }
                        }
                    }
                }

                lastDebugInfo += "Success! Found ${results.size} unique discoveries."
                Log.d("RecommendationEngine", "Smart Scan Completed normally.")
                results.shuffled()
            } catch (e: Exception) {
                lastDebugInfo += "Radar Error: ${e.message}\n"
                Log.e("RecommendationEngine", "FATAL ERROR in scanForGaps: ${e.message}", e)
                e.printStackTrace()
                emptyList()
            }
        }
    }

    private fun isSongMissingLocally(artist: String, title: String): Boolean {
        val matches = powerampController.findTracks(listOf(Pair(artist, title)))
        return matches.isEmpty()
    }

    private fun fetchFromItunes(query: String, entity: String, limit: Int): List<ItunesResult> {
        val out = mutableListOf<ItunesResult>()
        try {
            val encoded = Uri.encode(query)
            val url = URL("https://itunes.apple.com/search?term=$encoded&limit=$limit&entity=$entity")
            val connection = url.openConnection() as HttpURLConnection
            val response = connection.inputStream.bufferedReader().use { it.readText() }
            val json = org.json.JSONObject(response)
            val jsonResults = json.getJSONArray("results")
            for (i in 0 until jsonResults.length()) {
                val obj = jsonResults.getJSONObject(i)
                val artistName = obj.optString("artistName", "")
                var title = ""
                val collectionName = obj.optString("collectionName", "")
                val trackName = obj.optString("trackName", "")
                val genre = obj.optString("primaryGenreName")
                var artwork = obj.optString("artworkUrl100").replace("100x100", "600x600")
                
                if (entity == "musicArtist") {
                    if (artwork.isBlank() || artwork == "null") {
                        artwork = fetchRepresentativeArtworkForArtist(artistName) ?: "https://cdn-icons-png.flaticon.com/512/3043/3043665.png"
                    }
                }
                
                if (entity == "album") {
                    title = collectionName
                } else if (entity != "musicArtist") {
                    title = trackName
                }
                
                if (artistName.isNotBlank() || title.isNotBlank()) {
                    out.add(ItunesResult(artistName, title, artwork, genre))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return out
    }

    private suspend fun runPlaylistDiscovery(apiKey: String, type: String, playlistId: Long): List<DiscoveryItem> {
        Log.d("RecommendationEngine", "runPlaylistDiscovery started with type=$type, playlistId=$playlistId")
        val results = mutableListOf<DiscoveryItem>()
        val playlistTracks = powerampController.getPlaylistTracks(playlistId)
        if (playlistTracks.isEmpty()) {
            lastDebugInfo += "Playlist is empty.\n"
            Log.w("RecommendationEngine", "runPlaylistDiscovery aborted: Playlist is empty!")
            return emptyList()
        }
        
        val playlistArtists = playlistTracks.map { it.second }.filter { it.isNotBlank() }.distinct()
        val playlistTitlesLowercase = playlistTracks.map { it.first.lowercase() }.toSet()
        
        Log.d("RecommendationEngine", "Playlist contains ${playlistTracks.size} tracks from ${playlistArtists.size} artists.")
        
        val isLocalMode = type == "PLAYLIST_LOCAL"
        
        val prefs = context.getSharedPreferences("PowerampCompanionPrefs", Context.MODE_PRIVATE)
        val provider = prefs.getString("ai_provider", "Gemini") ?: "Gemini"
        val modelName = prefs.getString("ai_model", "gemini-2.0-flash") ?: "gemini-2.0-flash"
        val baseUrl = prefs.getString("ai_base_url", "") ?: ""

        if (isLocalMode) {
            lastDebugInfo += "Running Pure-Local AI-Free Discovery...\n"
            val sameArtistCandidates = mutableListOf<DiscoveryItem>()
            for (artist in playlistArtists.shuffled().take(100)) {
                val localTracks = powerampController.getTracksByArtist(artist)
                for (t in localTracks) {
                    if (!playlistTitlesLowercase.contains(t.title.lowercase()) && sameArtistCandidates.size < 250) {
                        sameArtistCandidates.add(DiscoveryItem(t.title, t.artist, "Weitere lokale Songs von ${t.artist}.", "SONG", null, t.id, playlistId))
                    }
                }
            }
            results.addAll(sameArtistCandidates.shuffled().take(25))

            // Ultra Fallback if still totally empty
            if (results.isEmpty()) {
                val genericTracks = powerampController.getRandomLocalTracks(50)
                for (t in genericTracks) {
                    if (!playlistTitlesLowercase.contains(t.title.lowercase())) {
                         results.add(DiscoveryItem(t.title, t.artist, "Zufällige lokale Entdeckung.", "SONG", null, t.id, playlistId))
                         if (results.size >= 25) break
                    }
                }
            }
        } else {
            // GLOBAL MODE
            lastDebugInfo += "Running Dedicated Global Discovery via AI...\n"
            if (apiKey.isNotBlank()) {
                val prompt = """
                    Die Nutzer-Playlist hat folgende Tracks:
                    ${playlistTracks.take(30).joinToString("\n") { "- ${it.first} von ${it.second}" }}
                    
                    Analysiere den Vibe. Empfiehl 20 Songs, die musikalisch perfekt dazu passen, aber NICHT in der Liste stehen.
                    Antworte AUSSCHLIESSLICH mit einem JSON Objekt im Format:
                    { "recommendations": [ {"title": "Song", "artist": "Band"} ] }
                """.trimIndent()
                
                val aiResponse = performAiCompletion(provider, apiKey, modelName, baseUrl, prompt, true, { lastDebugInfo += "$it\n" })
                if (aiResponse != null) {
                    try {
                        val jsonStr = extractJsonObject(aiResponse) ?: aiResponse
                        val json = org.json.JSONObject(jsonStr)
                        val recs = json.optJSONArray("recommendations")
                        if (recs != null) {
                            for (i in 0 until recs.length()) {
                                val obj = recs.getJSONObject(i)
                                val title = obj.optString("title")
                                val artist = obj.optString("artist")
                                if (title.isBlank() || artist.isBlank()) continue
                                
                                val localMatches = powerampController.findTracks(listOf(Pair(artist, title)))
                                if (localMatches.isEmpty()) { // Only truly global tracks
                                    val artwork = fetchArtworkUrl(artist, title)
                                    results.add(DiscoveryItem(title, artist, "KI Empfehlung zum Neu-Entdecken.", "SONG", artwork))
                                }
                            }
                        }
                    } catch (e: Exception) {
                        lastDebugInfo += "JSON Parse error: ${e.message}\n"
                    }
                }
            }
            
            // Global Fallback
            if (results.size < 5 && playlistArtists.isNotEmpty()) {
                lastDebugInfo += "Activating Global Zero-AI Fallback...\n"
                val fallbackArtists = playlistArtists.shuffled().take(4)
                for (artist in fallbackArtists) {
                    val itunesSongs = fetchFromItunes(artist, "musicTrack", 10)
                    for (song in itunesSongs) {
                        val localMatches = powerampController.findTracks(listOf(Pair(song.artist, song.title)))
                        if (localMatches.isEmpty() && !playlistTitlesLowercase.contains(song.title.lowercase())) {
                            results.add(DiscoveryItem(song.title, song.artist, "Entdeckung von ${song.artist}.", "SONG", song.imageUrl))
                        }
                    }
                }
            }
        }
        
        return results.distinctBy { it.title.lowercase() + it.artist.lowercase() }.shuffled()
    }

    private fun fetchRepresentativeArtworkForArtist(artist: String): String? {
        return try {
            val encoded = Uri.encode(artist)
            val url = URL("https://itunes.apple.com/search?term=$encoded&limit=1&entity=musicTrack")
            val connection = url.openConnection() as HttpURLConnection
            val response = connection.inputStream.bufferedReader().use { it.readText() }
            val json = org.json.JSONObject(response)
            val results = json.getJSONArray("results")
            if (results.length() > 0) {
                results.getJSONObject(0).optString("artworkUrl100").replace("100x100", "600x600")
            } else null
        } catch (e: Exception) {
            null
        }
    }

    private fun extractJsonArray(input: String): String? {
        val start = input.indexOf("[")
        val end = input.lastIndexOf("]")
        return if (start != -1 && end != -1 && end > start) {
            input.substring(start, end + 1)
        } else null
    }

    private fun extractJsonObject(input: String): String? {
        val start = input.indexOf("{")
        val end = input.lastIndexOf("}")
        return if (start != -1 && end != -1 && end > start) {
            input.substring(start, end + 1)
        } else null
    }

    private fun fetchArtworkUrl(artist: String, title: String): String? {
        return try {
            val query = Uri.encode("$artist $title")
            val url = URL("https://itunes.apple.com/search?term=$query&limit=1&entity=musicTrack")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            val response = connection.inputStream.bufferedReader().use { it.readText() }
            val json = org.json.JSONObject(response)
            val results = json.getJSONArray("results")
            if (results.length() > 0) {
                results.getJSONObject(0).optString("artworkUrl100").replace("100x100", "600x600")
            } else null
        } catch (e: Exception) {
            null
        }
    }

    private fun runHybridPlaylisting(prompt: String?, intent: DiscoveryIntent?, apiKey: String?, onLog: (String) -> Unit): List<PowerampTrack> {
        val foundTracks = mutableSetOf<PowerampTrack>()
        val seedTracks = mutableListOf<PowerampTrack>()
        
        // 1. If we have tags, get global metadata from Last.fm
        if (intent != null && intent.tags.isNotEmpty()) {
            onLog("Fetching global top tracks for tags: ${intent.tags} from Last.fm...")
            val statsEngine = StatsEngine(context)
            val globalSeedPairs = mutableListOf<Pair<String, String>>()
            
            for (tag in intent.tags.take(2)) { // Limit to 2 tags to avoid slow API
                val topTracks = runBlocking { statsEngine.fetchTopTracksForTag(tag, apiKey ?: "") }
                globalSeedPairs.addAll(topTracks)
            }
            
            if (globalSeedPairs.isNotEmpty()) {
                onLog("Matching ${globalSeedPairs.size} global hits against local library...")
                val tempSeeds = powerampController.findTracks(globalSeedPairs, onLog)
                seedTracks.addAll(tempSeeds)
                foundTracks.addAll(tempSeeds)
                onLog("Found ${tempSeeds.size} matching seed tracks in local library.")
            }
        }
        
        // 2. Fallback to keyword search if no tag matches were found
        if (seedTracks.isEmpty() && intent != null && intent.keywords.isNotEmpty()) {
            onLog("No tag matches. Trying local keyword search: ${intent.keywords}...")
            for (keyword in intent.keywords) {
                val keywordMatches = runLocalHeuristics(keyword)
                val tempSeeds = powerampController.findTracks(keywordMatches, onLog)
                seedTracks.addAll(tempSeeds)
                foundTracks.addAll(tempSeeds)
            }
            onLog("Found ${seedTracks.size} local seed tracks based on keywords.")
        }
        
        // 3. Fallback to basic prompt search
        if (seedTracks.isEmpty() && prompt != null) {
            val promptMatches = runLocalHeuristics(prompt)
            val tempSeeds = powerampController.findTracks(promptMatches, onLog)
            seedTracks.addAll(tempSeeds)
            foundTracks.addAll(tempSeeds)
        }
        
        // 4. Local Session Clustering (THE MAGIC)
        if (seedTracks.isNotEmpty()) {
            onLog("Starting Local Session Clustering based on ${seedTracks.size} seeds...")
            val statsEngine = StatsEngine(context)
            val allScrobblesRaw = runBlocking { statsEngine.getMergedScrobbles(StatsEngine.TimeRange.ALL_TIME) }
            
            val clusteringCal = Calendar.getInstance()
            val allScrobbles = allScrobblesRaw.map { s ->
                clusteringCal.timeInMillis = s.timestamp
                Scrobble(
                    artist = s.artist,
                    album = s.album,
                    track = s.title,
                    timestamp = s.timestamp,
                    hour = clusteringCal.get(Calendar.HOUR_OF_DAY),
                    year = clusteringCal.get(Calendar.YEAR),
                    dayOfYear = clusteringCal.get(Calendar.DAY_OF_YEAR),
                    bpm = s.bpm,
                    energy = s.energy,
                    danceability = s.danceability,
                    mood = s.mood
                )
            }
            
            val seedArtists = seedTracks.map { it.artist.lowercase() }.toSet()
            val seedTitles = seedTracks.map { it.title.lowercase() }.toSet()
            
            // Find timestamps where seed tracks were played
            val seedTimestamps = mutableListOf<Long>()
            for (scrobble in allScrobbles) {
                // Optimize: check set containment instead of nested loop, and require BOTH title and artist match
                val isSeed = seedTitles.contains(scrobble.track.lowercase()) && 
                             seedArtists.contains(scrobble.artist.lowercase())
                if (isSeed) {
                    seedTimestamps.add(scrobble.timestamp)
                }
            }
            
            onLog("Seeds were played ${seedTimestamps.size} times historically. Clustering...")
            val TIME_WINDOW_MS = 30 * 60 * 1000L // +/- 30 minutes
            
            val clusterFrequency = mutableMapOf<Pair<String, String>, Int>()
            val clusterTargetSize = 300
            
            for (scrobble in allScrobbles) {
                // If this scrobble is within 30 minutes of ANY seed timestamp...
                val isNearSeed = seedTimestamps.any { Math.abs(it - scrobble.timestamp) <= TIME_WINDOW_MS }
                if (isNearSeed) {
                    val pair = Pair(scrobble.artist, scrobble.track)
                    clusterFrequency[pair] = clusterFrequency.getOrDefault(pair, 0) + 1
                }
            }
            
            val clusterEntries = clusterFrequency.entries.toList()
            val topClusters = clusterEntries
                .sortedByDescending { it.value }
                .take(clusterTargetSize / 2)
                .map { it.key }
                
            val randomClusters = clusterEntries
                .shuffled()
                .take(clusterTargetSize / 2)
                .map { it.key }
            
            val combinedClusters = (topClusters + randomClusters).distinct()
            
            onLog("Found ${combinedClusters.size} clustered tracks (Top + Random mix). Fetching from DB...")
            val clusterTracks = powerampController.findTracks(combinedClusters, onLog)
            
            // 5. Sonic Clustering (Experimental Enrichment)
            if (clusterTracks.isNotEmpty()) {
                onLog("Refining clusters with Sonic Data...")
                val seedSonicProfiles = allScrobbles.filter { s ->
                    seedTitles.contains(s.track.lowercase()) && seedArtists.contains(s.artist.lowercase())
                }
                
                val avgBpm = seedSonicProfiles.mapNotNull { it.bpm }.average().takeIf { !it.isNaN() }?.toFloat()
                val avgEnergy = seedSonicProfiles.mapNotNull { it.energy }.average().takeIf { !it.isNaN() }?.toFloat()
                
                if (avgBpm != null || avgEnergy != null) {
                    onLog("Avg Seed Profile: BPM=${avgBpm ?: "N/A"}, Energy=${avgEnergy ?: "N/A"}")
                    
                    // Boost tracks in cluster that match this profile
                    val sonicMatches = allScrobbles.filter { s ->
                        var match = false
                        if (avgBpm != null && s.bpm != null && Math.abs(s.bpm - avgBpm) < 10) match = true
                        if (avgEnergy != null && s.energy != null && Math.abs(s.energy - avgEnergy) < 0.2f) match = true
                        match
                    }.map { Pair(it.artist, it.track) }.toSet()
                    
                    val sonicBoosted = clusterTracks.filter { sonicMatches.contains(Pair(it.artist, it.title)) }
                    if (sonicBoosted.isNotEmpty()) {
                        onLog("Found ${sonicBoosted.size} sonic-resonant tracks in clusters.")
                        foundTracks.addAll(sonicBoosted)
                    }
                }
            }
            
            foundTracks.addAll(clusterTracks)
            onLog("Successfully interweaved global targets with personal session clusters.")
        } else {
             onLog("Could not find any seeds. Will return a random fallback mix.")
        }
        
        val randomLimit = (100..300).random()
        val resultList = foundTracks.distinctBy { it.id }.shuffled().take(randomLimit)
        if (resultList.isEmpty()) {
            return powerampController.findTracks(listOf(Pair("MAGIC_TOKEN_FALLBACK", "MAGIC_TOKEN_FALLBACK")), onLog)
        }
        return resultList
    }

    private fun runLocalHeuristics(prompt: String?): List<Pair<String, String>> {
        val statsEngine = StatsEngine(context)
        val rawScrobbles = runBlocking {
            statsEngine.getMergedScrobbles(StatsEngine.TimeRange.ALL_TIME)
        }
        
        val cal = Calendar.getInstance()
        val scrobbles = rawScrobbles.map { s ->
            cal.timeInMillis = s.timestamp
            Scrobble(
                artist = s.artist,
                album = s.album,
                track = s.title,
                timestamp = s.timestamp,
                hour = cal.get(Calendar.HOUR_OF_DAY),
                year = cal.get(Calendar.YEAR),
                dayOfYear = cal.get(Calendar.DAY_OF_YEAR),
                bpm = s.bpm,
                energy = s.energy,
                danceability = s.danceability,
                mood = s.mood
            )
        }
        
        val currentCal = Calendar.getInstance()
        val currentHour = currentCal.get(Calendar.HOUR_OF_DAY)
        val currentYear = currentCal.get(Calendar.YEAR)
        val currentDayOfYear = currentCal.get(Calendar.DAY_OF_YEAR)
        val lowerPrompt = prompt?.lowercase(Locale.getDefault()) ?: ""
        
        // 0. Extract Artists from "Passend zu" and context "basierend auf" prompts (Auto DJ structured prompt)
        if (lowerPrompt.contains("passend zu")) {
            val artists = mutableSetOf<String>()
            
            // Extract current artist
             if (lowerPrompt.contains(" von ")) {
                val artistStr = lowerPrompt.substringAfter(" von ").substringBefore(".").substringBefore("(").trim()
                if (artistStr.isNotEmpty()) artists.add(artistStr)
            }
            
            // Extract history context artists
            if (lowerPrompt.contains("basiert auf") || lowerPrompt.contains("basierend auf")) {
                val contextPart = lowerPrompt.substringAfter("auf:").substringBefore(")")
                val parts = contextPart.split(",")
                for (p in parts) {
                    if (p.contains(" von ")) {
                        val historyArtist = p.substringAfter(" von ").trim()
                        if (historyArtist.isNotEmpty()) artists.add(historyArtist)
                    }
                }
            }
            
            if (artists.isNotEmpty()) {
                android.util.Log.d("RecommendationEngine", "Local Heuristic: Matched artists: $artists")
                return artists.map { Pair("MAGIC_TOKEN_ARTIST_MATCH|$it", "MAGIC_TOKEN_ARTIST_MATCH|$it") }
            }
        }

        if (lowerPrompt.contains("vergessen") || lowerPrompt.contains("alt") || lowerPrompt.contains("forgotten") || lowerPrompt.contains("lange nicht")) {
             return listOf(Pair("MAGIC_TOKEN_FORGOTTEN_FAVORITES", "MAGIC_TOKEN_FORGOTTEN_FAVORITES"))
        }
        if (lowerPrompt.contains("neu") || lowerPrompt.contains("heruntergeladen")) {
             return listOf(Pair("MAGIC_TOKEN_NEW_TRACKS", "MAGIC_TOKEN_NEW_TRACKS"))
        }
        if (lowerPrompt.contains("underrated") || lowerPrompt.contains("unbekannt")) {
             return listOf(Pair("MAGIC_TOKEN_UNDERRATED_TRACKS", "MAGIC_TOKEN_UNDERRATED_TRACKS"))
        }
        if (lowerPrompt.contains("gerade gerne") || lowerPrompt.contains("favoriten") || lowerPrompt.contains("liebling")) {
             val seeds = getCurrentlyEnjoyingSeeds()
             if (seeds.isNotEmpty()) return seeds
             return listOf(Pair("MAGIC_TOKEN_FALLBACK", "MAGIC_TOKEN_FALLBACK"))
        }
        if (lowerPrompt.contains("daily mix") || lowerPrompt.contains("tagesmix") || lowerPrompt.contains("mix des tages")) {
             return listOf(
                 Pair("MAGIC_TOKEN_NEW_TRACKS", "MAGIC_TOKEN_NEW_TRACKS"),
                 Pair("MAGIC_TOKEN_UNDERRATED_TRACKS", "MAGIC_TOKEN_UNDERRATED_TRACKS"),
                 Pair("MAGIC_TOKEN_FALLBACK", "MAGIC_TOKEN_FALLBACK")
             ).shuffled().take(2)
        }
        if (lowerPrompt.contains("party") || lowerPrompt.contains("feier") || lowerPrompt.contains("dance") || lowerPrompt.contains("club") || lowerPrompt.contains("tanzbar")) {
             return listOf(Pair("MAGIC_TOKEN_SONIC_PARTY", "MAGIC_TOKEN_SONIC_PARTY"))
        }
        if (lowerPrompt.contains("low energy") || lowerPrompt.contains("ruhig") || lowerPrompt.contains("chill") || lowerPrompt.contains("entspannt") || lowerPrompt.contains("langsam") || lowerPrompt.contains("gemütlich")) {
             return listOf(Pair("MAGIC_TOKEN_SONIC_LOW_ENERGY", "MAGIC_TOKEN_SONIC_LOW_ENERGY"))
        }
        if (lowerPrompt.contains("high energy") || lowerPrompt.contains("energie") || lowerPrompt.contains("power") || lowerPrompt.contains("workout") || lowerPrompt.contains("schnell") || lowerPrompt.contains("tempo")) {
             return listOf(Pair("MAGIC_TOKEN_SONIC_HIGH_ENERGY", "MAGIC_TOKEN_SONIC_HIGH_ENERGY"))
        }
        if (lowerPrompt.contains("soundtrack") || lowerPrompt.contains("ost") || lowerPrompt.contains("filmmusik") || lowerPrompt.contains("score")) {
             return listOf(Pair("MAGIC_TOKEN_KEYWORD_SEARCH|Soundtrack,OST,Score,Filmmusik", "MAGIC_TOKEN_KEYWORD_SEARCH|Soundtrack,OST,Score,Filmmusik"))
        }
        if (lowerPrompt.contains("rock") || lowerPrompt.contains("guitar") || lowerPrompt.contains("gitarre")) {
             return listOf(Pair("MAGIC_TOKEN_KEYWORD_SEARCH|Rock,Hard Rock,Classic Rock,Alternative,Grunge", "MAGIC_TOKEN_KEYWORD_SEARCH|Rock,Hard Rock,Classic Rock,Alternative,Grunge"))
        }
        if (lowerPrompt.contains("pop") || lowerPrompt.contains("charts") || lowerPrompt.contains("top")) {
             return listOf(Pair("MAGIC_TOKEN_KEYWORD_SEARCH|Pop,Charts,Top Hits,Dance Pop", "MAGIC_TOKEN_KEYWORD_SEARCH|Pop,Charts,Top Hits,Dance Pop"))
        }
        if (lowerPrompt.contains("metal") || lowerPrompt.contains("heavy")) {
             return listOf(Pair("MAGIC_TOKEN_KEYWORD_SEARCH|Metal,Heavy Metal,Thrash Metal,Death Metal", "MAGIC_TOKEN_KEYWORD_SEARCH|Metal,Heavy Metal,Thrash Metal,Death Metal"))
        }
        if (lowerPrompt.contains("electro") || lowerPrompt.contains("techno") || lowerPrompt.contains("dance") || lowerPrompt.contains("edm")) {
             return listOf(Pair("MAGIC_TOKEN_SONIC_BPM_RANGE|120-140", "MAGIC_TOKEN_SONIC_BPM_RANGE|120-140"))
        }
        if (lowerPrompt.contains("hiphop") || lowerPrompt.contains("rap") || lowerPrompt.contains("trap")) {
             return listOf(Pair("MAGIC_TOKEN_KEYWORD_SEARCH|Hip Hop,Rap,Trap,RnB,Old School Hip Hop", "MAGIC_TOKEN_KEYWORD_SEARCH|Hip Hop,Rap,Trap,RnB,Old School Hip Hop"))
        }
        if (lowerPrompt.contains("jazz") || lowerPrompt.contains("blues") || lowerPrompt.contains("soul")) {
             return listOf(Pair("MAGIC_TOKEN_KEYWORD_SEARCH|Jazz,Blues,Soul,Funk", "MAGIC_TOKEN_KEYWORD_SEARCH|Jazz,Blues,Soul,Funk"))
        }
        if (lowerPrompt.contains("glücklich") || lowerPrompt.contains("happy") || lowerPrompt.contains("gute laune") || lowerPrompt.contains("freude")) {
             return listOf(Pair("MAGIC_TOKEN_SONIC_MOOD_HAPPY", "MAGIC_TOKEN_SONIC_MOOD_HAPPY"))
        }
        if (lowerPrompt.contains("traurig") || lowerPrompt.contains("sad") || lowerPrompt.contains("melancholisch") || lowerPrompt.contains("depri") || lowerPrompt.contains("heul")) {
             return listOf(Pair("MAGIC_TOKEN_SONIC_MOOD_SAD", "MAGIC_TOKEN_SONIC_MOOD_SAD"))
        }

        // ListenBrainz Sonic Tokens
        if (lowerPrompt.contains("workout") || lowerPrompt.contains("sport") || lowerPrompt.contains("energie") || lowerPrompt.contains("power")) {
            return listOf(Pair("MAGIC_TOKEN_SONIC_HIGH_ENERGY", "MAGIC_TOKEN_SONIC_HIGH_ENERGY"))
        }
        if (lowerPrompt.contains("laufen") || lowerPrompt.contains("jog") || lowerPrompt.contains("bpm")) {
            val bpmMatch = Regex("(\\d+)\\s*bpm").find(lowerPrompt)
            val targetBpm = bpmMatch?.groupValues?.get(1)?.toFloatOrNull() ?: 125f
            return listOf(Pair("MAGIC_TOKEN_SONIC_BPM_${targetBpm.toInt()}", "MAGIC_TOKEN_SONIC_BPM_${targetBpm.toInt()}"))
        }

        if (lowerPrompt.isNotBlank() && scrobbles.isEmpty()) {
             return listOf(Pair("MAGIC_TOKEN_FALLBACK", "MAGIC_TOKEN_FALLBACK"))
        }

        // 2. Statistical Filtering
        val filteredScrobbles: List<Scrobble> = when {
            lowerPrompt.startsWith("magic_token_history_") -> {
                val yearsAgo = lowerPrompt.removePrefix("magic_token_history_").toIntOrNull() ?: 1
                val targetYear = currentYear - yearsAgo
                scrobbles.filter { 
                    it.year == targetYear && (
                        Math.abs(it.dayOfYear - currentDayOfYear) <= 1 || 
                        Math.abs(it.dayOfYear - currentDayOfYear) >= 364
                    )
                }
            }
            lowerPrompt.contains("vergessen") || lowerPrompt.contains("alt") -> {
                scrobbles.filter { it.year < currentYear - 1 }
            }
            lowerPrompt.contains("abend") || lowerPrompt.contains("evening") -> {
                // Return magic token for sonic evening if the prompt is explicitly about the vibe
                return listOf(Pair("MAGIC_TOKEN_SONIC_RELAXED_EVENING", "MAGIC_TOKEN_SONIC_RELAXED_EVENING"))
            }
            lowerPrompt.contains("nacht") -> {
                scrobbles.filter { it.hour in 22..23 || it.hour in 0..5 }
            }
            else -> {
                // Default Auto Time Context: +/- 1 hr
                scrobbles.filter { 
                    it.hour in (currentHour - 1)..(currentHour + 1) || 
                    (currentHour == 0 && it.hour == 23) ||
                    (currentHour == 23 && it.hour == 0)
                }
            }
        }

        
        // Count occurrences
        val trackCounts = mutableMapOf<Pair<String, String>, Int>()
        for (s in filteredScrobbles) {
            val key = Pair(s.artist, s.track)
            trackCounts[key] = (trackCounts[key] ?: 0) + 1
        }
        
        // 3. MIXED SAMPLING FOR VARIETY
        val allUniqueTracks = trackCounts.keys.toList()
        
        // Take top tracks (High frequency)
        val topTracks = trackCounts.toList()
            .sortedByDescending { it.second }
            .take(250)
            .map { it.first }
            
        // Take random tracks from the same context (Low frequency/New)
        val randomFromContext = allUniqueTracks.shuffled().take(250)
        
        // Combine and shuffle
        val combinedPool = (topTracks + randomFromContext).distinct().shuffled()
            
        val randomLimit = (100..300).random()
        return combinedPool.take(randomLimit)
    }


    private fun getCurrentlyEnjoyingSeeds(): List<Pair<String, String>> {
        try {
            val db = com.maxmpz.poweramp.companion.db.ScrobbleDatabase.getDatabase(context)
            val since = System.currentTimeMillis() - (14L * 24 * 60 * 60 * 1000)
            val scrobbles = db.scrobbleDao().getScrobblesSince(since)
            
            if (scrobbles.isEmpty()) return emptyList()
            
            // Group by artist/title and count
            val counts = scrobbles.groupingBy { "${it.artist}|${it.title}" }.eachCount()
            
            // Sort by count descending and take top 10
            return counts.entries
                .sortedByDescending { it.value }
                .take(10)
                .map { 
                    val parts = it.key.split("|")
                    Pair(parts[0], parts[1])
                }
        } catch (e: Exception) {
            android.util.Log.e("RecommendationEngine", "Error getting currently enjoying seeds", e)
            return emptyList()
        }
    }

    fun runMegamix(onLog: (String) -> Unit): List<PowerampTrack> {
        val allTracks = mutableSetOf<PowerampTrack>()
        val syncManager = com.maxmpz.poweramp.companion.db.ScrobbleSyncManager(context)

        // 1. Last.fm API Top Tracks (random 15-40 from top 100)
        onLog("🎵 Megamix: Lade deine Last.fm All-Time Hits...")
        try {
            val lfmTopPairs = kotlinx.coroutines.runBlocking {
                syncManager.fetchLastFmTopTracks(limit = 200)
            }
            val lfmLimit = (50..150).random()
            val lfmSlice = lfmTopPairs.shuffled().take(lfmLimit)
            if (lfmSlice.isNotEmpty()) {
                val lfmTracks = powerampController.findTracks(lfmSlice, onLog)
                allTracks.addAll(lfmTracks)
                onLog("✅ ${lfmTracks.size} Last.fm Top Tracks gefunden.")
            } else {
                onLog("⚠️ Keine Last.fm Daten – überspringe.")
            }
        } catch (e: Exception) {
            onLog("⚠️ Last.fm API nicht erreichbar: ${e.message}")
        }

        // 2. Forgotten Favorites (random 15-40)
        onLog("🎵 Megamix: Suche vergessene Favoriten...")
        val forgottenTracks = powerampController.findTracks(
            listOf(Pair("MAGIC_TOKEN_FORGOTTEN_FAVORITES", "MAGIC_TOKEN_FORGOTTEN_FAVORITES")), onLog
        )
        val forgottenLimit = (50..150).random()
        val forgottenSlice = forgottenTracks.filter { it !in allTracks }.shuffled().take(forgottenLimit)
        allTracks.addAll(forgottenSlice)
        onLog("✅ ${forgottenSlice.size} vergessene Favoriten hinzugefügt.")

        // 3. New Tracks (random 10-25)
        onLog("🎵 Megamix: Suche neue Tracks...")
        val newTracks = powerampController.findTracks(
            listOf(Pair("MAGIC_TOKEN_NEW_TRACKS", "MAGIC_TOKEN_NEW_TRACKS")), onLog
        )
        val newLimit = (30..100).random()
        val newSlice = newTracks.filter { it !in allTracks }.shuffled().take(newLimit)
        allTracks.addAll(newSlice)
        onLog("✅ ${newSlice.size} neue Tracks hinzugefügt.")

        // 4. Random Favorites (random 5-20)
        onLog("🎵 Megamix: Würfle zufällige Favoriten dazu...")
        val randomTracks = powerampController.findTracks(
            listOf(Pair("MAGIC_TOKEN_FALLBACK", "MAGIC_TOKEN_FALLBACK")), onLog
        )
        val randomLimit = (30..100).random()
        val randomSlice = randomTracks.filter { it !in allTracks }.shuffled().take(randomLimit)
        allTracks.addAll(randomSlice)
        onLog("✅ ${randomSlice.size} zufällige Favoriten hinzugefügt.")

        onLog("🔥 Megamix fertig: ${allTracks.size} Tracks aus 4 verschiedenen Quellen!")
        return allTracks.toList().shuffled()
    }
}
