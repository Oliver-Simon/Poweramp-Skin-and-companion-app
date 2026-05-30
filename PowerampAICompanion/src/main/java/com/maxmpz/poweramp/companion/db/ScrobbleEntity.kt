package com.maxmpz.poweramp.companion.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "scrobbles",
    indices = [androidx.room.Index(value = ["timestamp", "artist", "title"], unique = true)]
)
data class ScrobbleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val trackId: Long,
    val title: String,
    val artist: String,
    val album: String,
    val timestamp: Long,
    val durationMs: Long,
    
    // Sonic Analysis Fields (Nullable as they might not be enriched yet)
    val bpm: Float? = null,
    val energy: Float? = null,
    val danceability: Float? = null,
    val mood: String? = null,
    val musicbrainzId: String? = null
)
