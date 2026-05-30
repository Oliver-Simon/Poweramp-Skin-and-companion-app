package com.maxmpz.poweramp.companion

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class MainActivity : AppCompatActivity() {

    var currentPlayingInfo: String? = null
    private lateinit var powerampController: PowerampController
    private lateinit var recommendationEngine: RecommendationEngine

    private val trackReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == com.maxmpz.poweramp.player.PowerampAPI.ACTION_TRACK_CHANGED) {
                val trackBundle = intent.getBundleExtra(com.maxmpz.poweramp.player.PowerampAPI.TRACK)
                if (trackBundle != null) {
                    val title = trackBundle.getString(com.maxmpz.poweramp.player.PowerampAPI.Track.TITLE) ?: "Unknown"
                    val artist = trackBundle.getString(com.maxmpz.poweramp.player.PowerampAPI.Track.ARTIST) ?: "Unknown Artist"
                    val trackId = trackBundle.getLong(com.maxmpz.poweramp.player.PowerampAPI.Track.REAL_ID, -1L)
                    val queueId = trackBundle.getLong(com.maxmpz.poweramp.player.PowerampAPI.Track.ID, -1L)
                    val cat = trackBundle.getInt(com.maxmpz.poweramp.player.PowerampAPI.Track.CAT, -1)
                    
                    powerampController.currentQueueId = if (cat == com.maxmpz.poweramp.player.PowerampAPI.Cats.QUEUE) queueId else -1L
                    
                    currentPlayingInfo = "$title by $artist"
                    updateMiniPlayer(title, artist, trackId, isPlaying = true)
                }
                refreshQueueStatus()
            } else if (intent?.action == com.maxmpz.poweramp.player.PowerampAPI.ACTION_STATUS_CHANGED) {
                val state = intent.getIntExtra(com.maxmpz.poweramp.player.PowerampAPI.STATE, -1)
                val isPlaying = state == com.maxmpz.poweramp.player.PowerampAPI.STATE_PLAYING
                updateMiniPlayerPlaybackState(isPlaying)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Berechtigung erteilt.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Fehlende Berechtigung für Musik-Scan.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        powerampController = PowerampController(this)
        recommendationEngine = RecommendationEngine(this, powerampController)

        setupMiniPlayer()
        checkPermissions()

        findViewById<ImageButton>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        findViewById<ImageButton>(R.id.btnOpenPoweramp).setOnClickListener {
            val intent = packageManager.getLaunchIntentForPackage(com.maxmpz.poweramp.player.PowerampAPI.PACKAGE_NAME)
            if (intent != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "Poweramp not installed!", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<ImageButton>(R.id.btnInsights).setOnClickListener {
            showInsights()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_generate -> {
                    switchFragment(GenerateFragment())
                    true
                }
                R.id.nav_explore -> {
                    switchFragment(ExploreFragment())
                    true
                }
                R.id.nav_discovery -> {
                    switchFragment(DiscoveryFragment())
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.nav_generate
        }

        handleNavIntent(intent)
        checkPowerampInstalled()

        lifecycleScope.launch {
            try {
                com.maxmpz.poweramp.companion.db.ScrobbleSyncManager(this@MainActivity).syncFull()
            } catch (e: Exception) {
               android.util.Log.e("MainActivity", "Scrobble sync error", e)
            }
        }
    }

    private fun checkPowerampInstalled() {
        try {
            packageManager.getPackageInfo(com.maxmpz.poweramp.player.PowerampAPI.PACKAGE_NAME, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            com.google.android.material.snackbar.Snackbar.make(
                findViewById(android.R.id.content),
                "⚠️ Poweramp ist nicht installiert — einige Funktionen sind nicht verfügbar.",
                com.google.android.material.snackbar.Snackbar.LENGTH_INDEFINITE
            ).setAction("Installieren") {
                try {
                    startActivity(Intent(Intent.ACTION_VIEW,
                        android.net.Uri.parse("market://details?id=${com.maxmpz.poweramp.player.PowerampAPI.PACKAGE_NAME}")))
                } catch (ex: Exception) {
                    startActivity(Intent(Intent.ACTION_VIEW,
                        android.net.Uri.parse("https://play.google.com/store/apps/details?id=${com.maxmpz.poweramp.player.PowerampAPI.PACKAGE_NAME}")))
                }
            }.show()
        }
    }

    private fun setupMiniPlayer() {
        val layoutMiniPlayer = findViewById<android.widget.LinearLayout>(R.id.layoutMiniPlayer)
        val btnPlayPause = findViewById<ImageButton>(R.id.btnMiniPlayerPlayPause)
        val btnPrev = findViewById<ImageButton>(R.id.btnMiniPlayerPrev)
        val btnNext = findViewById<ImageButton>(R.id.btnMiniPlayerNext)
        
        btnPrev.setOnClickListener {
            startService(Intent(com.maxmpz.poweramp.player.PowerampAPI.ACTION_API_COMMAND).apply {
                setPackage(com.maxmpz.poweramp.player.PowerampAPI.PACKAGE_NAME)
                putExtra(com.maxmpz.poweramp.player.PowerampAPI.COMMAND, com.maxmpz.poweramp.player.PowerampAPI.Commands.PREVIOUS)
            })
        }
        
        btnPlayPause.setOnClickListener {
            startService(Intent(com.maxmpz.poweramp.player.PowerampAPI.ACTION_API_COMMAND).apply {
                setPackage(com.maxmpz.poweramp.player.PowerampAPI.PACKAGE_NAME)
                putExtra(com.maxmpz.poweramp.player.PowerampAPI.COMMAND, com.maxmpz.poweramp.player.PowerampAPI.Commands.TOGGLE_PLAY_PAUSE)
            })
        }

        btnNext.setOnClickListener {
            startService(Intent(com.maxmpz.poweramp.player.PowerampAPI.ACTION_API_COMMAND).apply {
                setPackage(com.maxmpz.poweramp.player.PowerampAPI.PACKAGE_NAME)
                putExtra(com.maxmpz.poweramp.player.PowerampAPI.COMMAND, com.maxmpz.poweramp.player.PowerampAPI.Commands.NEXT)
            })
        }
        
        layoutMiniPlayer.setOnClickListener {
            val intent = packageManager.getLaunchIntentForPackage(com.maxmpz.poweramp.player.PowerampAPI.PACKAGE_NAME)
            if (intent != null) startActivity(intent)
        }
    }

    private fun updateMiniPlayer(title: String, artist: String, trackId: Long, isPlaying: Boolean) {
        val prefs = getSharedPreferences("PowerampCompanionPrefs", MODE_PRIVATE)
        if (!prefs.getBoolean("show_mini_player", true)) {
            findViewById<android.widget.LinearLayout>(R.id.layoutMiniPlayer)?.visibility = android.view.View.GONE
            return
        }

        val layoutMiniPlayer = findViewById<android.widget.LinearLayout>(R.id.layoutMiniPlayer)
        val tvTitle = findViewById<android.widget.TextView>(R.id.tvMiniPlayerTitle)
        val tvArtist = findViewById<android.widget.TextView>(R.id.tvMiniPlayerArtist)
        val ivArt = findViewById<android.widget.ImageView>(R.id.ivMiniPlayerArt)
        
        layoutMiniPlayer.visibility = android.view.View.VISIBLE
        tvTitle.text = title
        tvArtist.text = artist
        
        val albumArtUri = com.maxmpz.poweramp.player.PowerampAPI.AA_ROOT_URI.buildUpon()
            .appendEncodedPath("files")
            .appendEncodedPath(trackId.toString())
            .build()

        Glide.with(this)
            .load(albumArtUri)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_gallery)
            .into(ivArt)

        updateMiniPlayerPlaybackState(isPlaying)
    }

    private fun updateMiniPlayerPlaybackState(isPlaying: Boolean) {
        val btnPlayPause = findViewById<ImageButton>(R.id.btnMiniPlayerPlayPause)
        if (isPlaying) {
            btnPlayPause.setImageResource(android.R.drawable.ic_media_pause)
        } else {
            btnPlayPause.setImageResource(android.R.drawable.ic_media_play)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleNavIntent(intent)
    }

    private fun handleNavIntent(intent: Intent?) {
        if (intent?.getStringExtra("nav_tab") == "generate") {
            val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNav.selectedItemId = R.id.nav_generate
        }
    }
    
    fun getPowerampController() = powerampController
    fun getRecommendationEngine() = recommendationEngine

    private fun showInsights() {
        val prefs = getSharedPreferences("PowerampCompanionPrefs", MODE_PRIVATE)
        val apiKey = prefs.getString("gemini_api_key", "")
        val blacklist = prefs.getString("blacklist_filter", "") ?: ""

        if (apiKey.isNullOrEmpty()) {
            com.google.android.material.snackbar.Snackbar.make(
                findViewById(android.R.id.content),
                "Bitte API Key in den Einstellungen hinterlegen!",
                com.google.android.material.snackbar.Snackbar.LENGTH_LONG
            ).setAction("Settings") {
                startActivity(Intent(this, SettingsActivity::class.java))
            }.show()
            return
        }

        val bottomSheet = InsightsBottomSheetFragment()
        bottomSheet.show(supportFragmentManager, InsightsBottomSheetFragment.TAG)

        lifecycleScope.launch {
            try {
                val topTracks = withContext(Dispatchers.IO) { powerampController.getTopPlayedTracks(50) }
                val recentTracks = withContext(Dispatchers.IO) { powerampController.getRecentlyAddedTracks(50) }

                val result = recommendationEngine.generateInsights(apiKey, topTracks, recentTracks, blacklist)

                withContext(Dispatchers.Main) {
                    bottomSheet.setInsightsText(result)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    bottomSheet.showError(e.message ?: "Unknown Error")
                }
            }
        }
    }

    private fun refreshQueueStatus() {
        lifecycleScope.launch(Dispatchers.IO) {
            val stats = powerampController.getQueueStats()
            withContext(Dispatchers.Main) {
                val layout = findViewById<android.view.View>(R.id.layoutQueueStatus)
                val textView = findViewById<android.widget.TextView>(R.id.tvQueueStatus)
                if (stats.totalCount > 0) {
                    layout.visibility = android.view.View.VISIBLE
                    val prefs = getSharedPreferences("PowerampCompanionPrefs", Context.MODE_PRIVATE)
                    val showRemaining = prefs.getBoolean("queue_remaining_duration", true)
                    
                    val durationMs = if (showRemaining) stats.remainingDurationMs else stats.totalDurationMs
                    val timeStr = formatDuration(durationMs)
                    
                    textView.text = "Warteschlange: ${stats.playedCount}/${stats.totalCount} • $timeStr"
                    
                    layout.setOnClickListener {
                        openPowerampQueue()
                    }
                } else {
                    layout.visibility = android.view.View.GONE
                }
            }
        }
    }

    private fun openPowerampQueue() {
        try {
            val intent = Intent(com.maxmpz.poweramp.player.PowerampAPI.ACTION_OPEN_LIBRARY).apply {
                component = android.content.ComponentName(
                    com.maxmpz.poweramp.player.PowerampAPI.PACKAGE_NAME,
                    com.maxmpz.poweramp.player.PowerampAPI.ACTIVITY_STARTUP
                )
                data = com.maxmpz.poweramp.player.PowerampAPI.ROOT_URI.buildUpon()
                    .appendEncodedPath("queue")
                    .build()
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Konnte Poweramp Queue nicht öffnen.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formatDuration(ms: Long): String {
        val totalSeconds = ms / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        
        return if (hours > 0) {
            String.format("%d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%d:%02d", minutes, seconds)
        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun getRequiredPermission(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_AUDIO
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
    }

    fun hasPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            getRequiredPermission()
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun checkPermissions() {
        if (!hasPermission()) {
            requestPermissionLauncher.launch(getRequiredPermission())
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter().apply {
            addAction(com.maxmpz.poweramp.player.PowerampAPI.ACTION_TRACK_CHANGED)
            addAction(com.maxmpz.poweramp.player.PowerampAPI.ACTION_STATUS_CHANGED)
        }
        ContextCompat.registerReceiver(this, trackReceiver, filter, ContextCompat.RECEIVER_EXPORTED)

        val prefs = getSharedPreferences("PowerampCompanionPrefs", MODE_PRIVATE)
        if (prefs.getBoolean("auto_dj_enabled", false)) {
            val serviceIntent = Intent(this, AutoDjService::class.java)
            ContextCompat.startForegroundService(this, serviceIntent)
        }
        refreshQueueStatus()
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(trackReceiver)
    }
}
