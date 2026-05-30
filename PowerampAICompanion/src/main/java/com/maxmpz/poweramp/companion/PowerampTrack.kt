package com.maxmpz.poweramp.companion

import android.net.Uri

data class PowerampTrack(
    val id: Long,
    val title: String,
    val artist: String,
    val durationMs: Long = 0L,
    val playedTimes: Int = 0,
    val albumArtUri: Uri? = null // Poweramp AA content URI, e.g. content://com.maxmpz.audioplayer.aa/files/{id}
)
