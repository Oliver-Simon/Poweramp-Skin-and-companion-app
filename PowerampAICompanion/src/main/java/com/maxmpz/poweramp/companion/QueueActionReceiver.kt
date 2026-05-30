package com.maxmpz.poweramp.companion

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationManager
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Handles taps on the "Queue fast leer"-Notification action buttons:
 *   ACTION_AUTO_EXPAND  -> silently generates and enqueues more tracks
 *   ACTION_OPEN_APP     -> brings the companion app to the foreground
 */
class QueueActionReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_AUTO_EXPAND = "com.maxmpz.poweramp.companion.ACTION_AUTO_EXPAND"
        const val ACTION_OPEN_APP   = "com.maxmpz.poweramp.companion.ACTION_OPEN_APP"
        const val EXTRA_TRACK_TITLE  = "extra_track_title"
        const val EXTRA_TRACK_ARTIST = "extra_track_artist"
        const val NOTIFICATION_ID_QUEUE_PROMPT = 42
    }

    override fun onReceive(context: Context, intent: Intent) {
        // Dismiss the prompt notification immediately
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.cancel(NOTIFICATION_ID_QUEUE_PROMPT)
        Log.d("QueueActionReceiver", "onReceive: ${intent.action}")

        when (intent.action) {
            ACTION_AUTO_EXPAND -> {
                val title  = intent.getStringExtra(EXTRA_TRACK_TITLE) ?: "Unknown"
                val artist = intent.getStringExtra(EXTRA_TRACK_ARTIST) ?: "Unknown"
                Log.d("QueueActionReceiver", "Auto-expand triggered for: $title by $artist")

                // Run generation in a fresh coroutine (goAsync not used – fast hand-off to service)
                val expandIntent = Intent(context, AutoDjService::class.java).apply {
                    action = AutoDjService.ACTION_EXPAND_QUEUE
                    putExtra(EXTRA_TRACK_TITLE, title)
                    putExtra(EXTRA_TRACK_ARTIST, artist)
                }
                context.startService(expandIntent)
            }

            ACTION_OPEN_APP -> {
                val launchIntent = Intent(context, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    putExtra("nav_tab", "generate") // MainActivity reads this to switch tab
                }
                Log.d("QueueActionReceiver", "Opening App...")
                context.startActivity(launchIntent)
            }
        }
    }
}
