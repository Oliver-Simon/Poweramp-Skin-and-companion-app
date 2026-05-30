package com.maxmpz.poweramp.companion

import android.content.Context
import android.net.Uri
import android.util.Log
import com.maxmpz.poweramp.player.PowerampAPI

object DebugHelper {
    fun logCurrentTrackInfo(context: Context) {
        val trackUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("track").build()
        try {
            context.contentResolver.query(trackUri, null, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val columnNames = cursor.columnNames
                    val sb = StringBuilder("Current Track Info:\n")
                    for (name in columnNames) {
                        val idx = cursor.getColumnIndex(name)
                        val value = try { cursor.getString(idx) } catch (e: Exception) { "binary/error" }
                        sb.append("$name: $value\n")
                    }
                    Log.d("PowerampDebug", sb.toString())
                } else {
                    Log.d("PowerampDebug", "No current track info found (cursor empty)")
                }
            }
        } catch (e: Exception) {
            Log.e("PowerampDebug", "Error querying current track info", e)
        }
    }
}
