package com.maxmpz.poweramp.companion

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.maxmpz.poweramp.player.PowerampAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AutoDjService : Service() {

    companion object {
        private const val CHANNEL_PERSISTENT = "AutoDjServiceChannel"
        private const val CHANNEL_PROMPT     = "AutoDjPromptChannel"
        const val  ACTION_STOP               = "com.maxmpz.poweramp.companion.STOP_AUTO_DJ"
        const val  ACTION_EXPAND_QUEUE       = "com.maxmpz.poweramp.companion.EXPAND_QUEUE"
        private const val NOTIF_PERSISTENT   = 1
    }

    private var isServiceRunning = false
    private lateinit var powerampController: PowerampController
    private lateinit var recommendationEngine: RecommendationEngine
    private var scrobbleJob: Job? = null
    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())
    private var isGenerating = false
    private var promptShownForTrackId = -1L // Guard: only show one prompt per track

    private var currentScrobbleTrackId = -1L
    private var currentScrobbleTitle = ""
    private var currentScrobbleArtist = ""
    private var currentScrobbleAlbum = ""
    private var currentScrobbleDuration = 0L
    private var accumulatedPlayTimeMs = 0L
    private var lastPlayResumeTime = 0L
    private var isPlaying = false
    private var isScrobbled = false

    private val handler = Handler(Looper.getMainLooper())
    private val scrobbleCheckRunnable = Runnable { checkAndScrobble() }

    // ──────────────────────────────────────────────────────
    // BroadcastReceiver: listens to Poweramp track changes and status
    // ──────────────────────────────────────────────────────
    private val trackReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                PowerampAPI.ACTION_TRACK_CHANGED,
                PowerampAPI.ACTION_TRACK_CHANGED_EXPLICIT -> {
                    val trackBundle = intent.getBundleExtra(PowerampAPI.TRACK)
                    val idLong      = intent.getLongExtra("id", -1L)
                    val trackTitle  = intent.getStringExtra("title")  ?: trackBundle?.getString(PowerampAPI.Track.TITLE)  ?: "Unknown"
                    val trackArtist = intent.getStringExtra("artist") ?: trackBundle?.getString(PowerampAPI.Track.ARTIST) ?: "Unknown Artist"
                    val trackAlbum  = intent.getStringExtra("album")  ?: trackBundle?.getString(PowerampAPI.Track.ALBUM)  ?: "Unknown Album"
                    val duration    = intent.getIntExtra("duration", 0).toLong()

                    if (idLong == -1L) return

                    // Check if previous track needs to be scrobbled (e.g. skipped after hearing threshold)
                    checkAndScrobble()

                    currentScrobbleTrackId = idLong
                    currentScrobbleTitle = trackTitle
                    currentScrobbleArtist = trackArtist
                    currentScrobbleAlbum = trackAlbum
                    currentScrobbleDuration = duration * 1000L
                    accumulatedPlayTimeMs = 0L
                    isScrobbled = false
                    handler.removeCallbacks(scrobbleCheckRunnable)

                    val paused = intent.getBooleanExtra("paused", false)
                    val state = intent.getIntExtra("state", -1)
                    isPlaying = !paused && (state == 1 || state == -1)
                    
                    if (isPlaying) {
                        lastPlayResumeTime = System.currentTimeMillis()
                        scheduleScrobbleCheck()
                    }

                    val posInList   = trackBundle?.getInt(PowerampAPI.Track.POS_IN_LIST, -1) ?: -1
                    val listSize    = trackBundle?.getInt(PowerampAPI.Track.LIST_SIZE, -1) ?: -1

                    checkQueueStatus(idLong, trackTitle, trackArtist, posInList, listSize)
                }
                PowerampAPI.ACTION_STATUS_CHANGED -> {
                    val paused = intent.getBooleanExtra("paused", false)
                    val newIsPlaying = !paused

                    if (newIsPlaying && !isPlaying) {
                        // Resumed
                        isPlaying = true
                        lastPlayResumeTime = System.currentTimeMillis()
                        scheduleScrobbleCheck()
                    } else if (!newIsPlaying && isPlaying) {
                        // Paused
                        isPlaying = false
                        accumulatedPlayTimeMs += (System.currentTimeMillis() - lastPlayResumeTime)
                        handler.removeCallbacks(scrobbleCheckRunnable)
                        checkAndScrobble()
                    }
                }
            }
        }
    }

    private fun scheduleScrobbleCheck() {
        if (isScrobbled || currentScrobbleTrackId == -1L || currentScrobbleDuration == 0L) return
        val threshold = minOf(30_000L, currentScrobbleDuration / 2)
        val remaining = threshold - accumulatedPlayTimeMs
        if (remaining > 0) {
            handler.removeCallbacks(scrobbleCheckRunnable)
            handler.postDelayed(scrobbleCheckRunnable, remaining)
        } else {
            checkAndScrobble()
        }
    }

    private fun checkAndScrobble() {
        if (isScrobbled || currentScrobbleTrackId == -1L || currentScrobbleDuration == 0L) return
        
        var totalPlayedMs = accumulatedPlayTimeMs
        if (isPlaying) {
            totalPlayedMs += (System.currentTimeMillis() - lastPlayResumeTime)
        }
        
        val threshold = minOf(30_000L, currentScrobbleDuration / 2)
        if (totalPlayedMs >= threshold) {
            isScrobbled = true
            serviceScope.launch {
                try {
                    val db = com.maxmpz.poweramp.companion.db.ScrobbleDatabase.getDatabase(this@AutoDjService)
                    db.scrobbleDao().insert(
                        com.maxmpz.poweramp.companion.db.ScrobbleEntity(
                            trackId = currentScrobbleTrackId, title = currentScrobbleTitle, artist = currentScrobbleArtist,
                            album = currentScrobbleAlbum, timestamp = System.currentTimeMillis(),
                            durationMs = currentScrobbleDuration
                        )
                    )
                    Log.d("AutoDjService", "Scrobble saved: $currentScrobbleTitle (Played ${totalPlayedMs / 1000}s, Threshold: ${threshold / 1000}s)")
                } catch (e: Exception) {
                    Log.e("AutoDjService", "Scrobble failed: ${e.message}")
                }
            }
        }
    }

    // ──────────────────────────────────────────────────────
    // Lifecycle
    // ──────────────────────────────────────────────────────
    override fun onCreate() {
        super.onCreate()
        powerampController    = PowerampController(this)
        recommendationEngine  = RecommendationEngine(this, powerampController)
        createNotificationChannels()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP -> {
                stopSelf()
                return START_NOT_STICKY
            }
            ACTION_EXPAND_QUEUE -> {
                // Called by QueueActionReceiver when user taps "Automatisch erweitern"
                val title  = intent.getStringExtra(QueueActionReceiver.EXTRA_TRACK_TITLE)  ?: "Unknown"
                val artist = intent.getStringExtra(QueueActionReceiver.EXTRA_TRACK_ARTIST) ?: "Unknown"
                serviceScope.launch { generateAndEnqueue(title, artist) }
                return START_NOT_STICKY
            }
        }

        if (!isServiceRunning) {
            startForeground(NOTIF_PERSISTENT, buildPersistentNotification())
            val filter = IntentFilter().apply {
                addAction(PowerampAPI.ACTION_TRACK_CHANGED)
                addAction(PowerampAPI.ACTION_TRACK_CHANGED_EXPLICIT)
                addAction(PowerampAPI.ACTION_STATUS_CHANGED)
            }
            ContextCompat.registerReceiver(this, trackReceiver, filter, ContextCompat.RECEIVER_EXPORTED)
            isServiceRunning = true

            Handler(Looper.getMainLooper()).post {
                Toast.makeText(this, "Auto-DJ aktiv 🎵", Toast.LENGTH_SHORT).show()
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isServiceRunning) {
            unregisterReceiver(trackReceiver)
            isServiceRunning = false
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    // ──────────────────────────────────────────────────────
    // Queue detection → prompt notification
    // ──────────────────────────────────────────────────────
    private fun checkQueueStatus(trackId: Long, currentTitle: String, currentArtist: String, posInList: Int = -1, listSize: Int = -1) {
        if (isGenerating || promptShownForTrackId == trackId) return

        serviceScope.launch {
            var position = posInList
            var queueSize = listSize

            // If intent bundle didn't have positions (sometimes it doesn't on older PA versions or certain cats), 
            // fallback to DB query
            if (position == -1 || queueSize == -1) {
                queueSize = powerampController.getQueueSize()
                position  = powerampController.getQueuePosition(trackId)
            }

            Log.d("AutoDjService", "Queue Status: pos=$position, size=$queueSize")
            
            // Trigger when there are 2 or fewer tracks remaining (position is 0-indexed)
            // Example: pos=8, size=10 -> 2 left (the one playing + 1 more) -> Trigger
            // Example: pos=9, size=10 -> 1 left (the one playing) -> Trigger
            if (queueSize > 0 && position != -1 && (queueSize - position <= 2)) {
                promptShownForTrackId = trackId
                Log.d("AutoDjService", "Threshold reached ($position/$queueSize)! Showing prompt.")
                withContext(Dispatchers.Main) {
                    showQueueEndPrompt(currentTitle, currentArtist)
                }
            } else if (position == -1) {
                Log.w("AutoDjService", "Track $trackId not found in queue/list!")
            }
        }
    }

    /**
     * Shows a loud heads-up notification asking the user what to do.
     * Two actions: automatic expand, or open the app.
     */
    private fun showQueueEndPrompt(trackTitle: String, trackArtist: String) {
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // "Automatisch erweitern" action
        val autoIntent = Intent(this, QueueActionReceiver::class.java).apply {
            action = QueueActionReceiver.ACTION_AUTO_EXPAND
            putExtra(QueueActionReceiver.EXTRA_TRACK_TITLE, trackTitle)
            putExtra(QueueActionReceiver.EXTRA_TRACK_ARTIST, trackArtist)
        }
        val autoPi = PendingIntent.getBroadcast(
            this, 10, autoIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // "App öffnen" action (direct activity start for reliability)
        val openAppIntent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra("nav_tab", "generate")
        }
        val openPi = PendingIntent.getActivity(
            this, 11, openAppIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Tap notification body → open app
        val tapPi  = PendingIntent.getActivity(
            this, 12,
            Intent(this, MainActivity::class.java).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) },
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_PROMPT)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentTitle("🎵 Warteschlange fast leer!")
            .setContentText("Letzte(r) Track: $trackTitle · Soll Auto-DJ die Queue erweitern?")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Der letzte Track läuft: \"$trackTitle\" von $trackArtist.\n\nWähle eine Option, um die Queue zu erweitern."))
            .setContentIntent(tapPi)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_SOUND or NotificationCompat.DEFAULT_VIBRATE)
            .addAction(android.R.drawable.ic_media_play, "🤖 Automatisch erweitern", autoPi)
            .addAction(android.R.drawable.ic_menu_view,  "📱 App öffnen",            openPi)
            .build()

        nm.notify(QueueActionReceiver.NOTIFICATION_ID_QUEUE_PROMPT, notification)
        Log.d("AutoDjService", "Queue-end prompt notification shown.")
    }

    // ──────────────────────────────────────────────────────
    // Silent auto-expand (called when user taps the auto button)
    // ──────────────────────────────────────────────────────
    private suspend fun generateAndEnqueue(currentTitle: String, currentArtist: String) {
        if (isGenerating) return
        isGenerating = true

        try {
            Log.d("AutoDjService", "Starting local context expansion with entire queue...")
            
            // Fetch the entire queue for context
            val queueTracks = withContext(Dispatchers.IO) { powerampController.getQueueTracks() }
            val queueContext = if (queueTracks.isNotEmpty()) {
                val contextStr = queueTracks.distinctBy { it.second + it.first }
                    .take(20) // Limit to top 20 for prompt sanity
                    .joinToString(", ") { "${it.second} von ${it.first}" }
                " (basiert auf der aktuellen Queue: $contextStr)"
            } else ""
            
            val prompt = "Passend zu: $currentTitle von $currentArtist$queueContext."
            
            // Bypass Gemini and go straight to local recommendations for Auto DJ
            val newTracks = recommendationEngine.parseAndRecommend(prompt, onLog = { Log.d("AutoDjService", "Engine: $it") })

            if (newTracks.isNotEmpty()) {
                val toAdd = newTracks.shuffled().take(5)
                // Use sendToPowerampQueueNext to ensure immediate insertion after current track
                powerampController.sendToPowerampQueueNext(toAdd.map { it.id })
                Log.d("AutoDjService", "Auto-enqueued ${toAdd.size} tracks using Play Next logic.")
                val notification = NotificationCompat.Builder(this, CHANNEL_PROMPT)
                    .setSmallIcon(android.R.drawable.ic_media_play)
                    .setContentTitle("✅ Queue erweitert!")
                    .setContentText("${toAdd.size} neue Tracks hinzugefügt (z.B. ${toAdd.firstOrNull()?.title})")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .build()
                val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                nm.notify(QueueActionReceiver.NOTIFICATION_ID_QUEUE_PROMPT + 1, notification)

                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "🤖 ${toAdd.size} Tracks zur Queue hinzugefügt!", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: Exception) {
            Log.e("AutoDjService", "Auto-expand failed: ${e.message}")
        } finally {
            isGenerating = false
        }
    }

    // ──────────────────────────────────────────────────────
    // Notification helpers
    // ──────────────────────────────────────────────────────
    private fun buildPersistentNotification(): Notification {
        val tapPi = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE
        )
        val stopPi = PendingIntent.getService(
            this, 1,
            Intent(this, AutoDjService::class.java).apply { action = ACTION_STOP },
            PendingIntent.FLAG_IMMUTABLE
        )
        return NotificationCompat.Builder(this, CHANNEL_PERSISTENT)
            .setContentTitle("Poweramp AI Auto-DJ")
            .setContentText("Überwacht deine Queue und benachrichtigt dich am Ende.")
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentIntent(tapPi)
            .setOngoing(true)
            .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Stoppen", stopPi)
            .build()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val nm = getSystemService(NotificationManager::class.java) ?: return

        // Persistent (silent) channel for the foreground service notification
        nm.createNotificationChannel(
            NotificationChannel(CHANNEL_PERSISTENT, "Auto-DJ Service", NotificationManager.IMPORTANCE_LOW)
        )

        // High-importance channel for the queue-end prompt (plays sound + appears as heads-up)
        val promptChannel = NotificationChannel(
            CHANNEL_PROMPT, "Auto-DJ Queue-Ende Benachrichtigung", NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Laut – erscheint wenn die Warteschlange fast leer ist"
            val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            setSound(sound, AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build())
            enableVibration(true)
            vibrationPattern = longArrayOf(0, 250, 100, 250)
        }
        nm.createNotificationChannel(promptChannel)
    }
}
