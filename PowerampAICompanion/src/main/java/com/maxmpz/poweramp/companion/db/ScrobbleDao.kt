package com.maxmpz.poweramp.companion.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScrobbleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(scrobble: ScrobbleEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(scrobbles: List<ScrobbleEntity>)

    @Query("SELECT * FROM scrobbles ORDER BY timestamp DESC")
    fun getAllScrobbles(): List<ScrobbleEntity>

    @Query("SELECT * FROM scrobbles WHERE timestamp >= :sinceTimestamp ORDER BY timestamp DESC")
    fun getScrobblesSince(sinceTimestamp: Long): List<ScrobbleEntity>

    @Query("SELECT * FROM scrobbles WHERE timestamp >= :start AND timestamp <= :end ORDER BY timestamp DESC")
    fun getScrobblesInRange(start: Long, end: Long): List<ScrobbleEntity>

    @Query("SELECT * FROM scrobbles WHERE musicbrainzId IS NULL LIMIT :limit")
    fun getUnenrichedTracks(limit: Int): List<ScrobbleEntity>

    @Query("UPDATE scrobbles SET bpm = :bpm, energy = :energy, danceability = :danceability, mood = :mood, musicbrainzId = :mbid WHERE artist = :artist AND title = :title")
    fun updateSonicData(artist: String, title: String, mbid: String, bpm: Float?, energy: Float?, danceability: Float?, mood: String?)

    @Query("SELECT * FROM scrobbles WHERE artist = :artist AND title = :title LIMIT 1")
    fun getScrobble(artist: String, title: String): ScrobbleEntity?

    @Query("DELETE FROM scrobbles")
    fun clearAll()
    @Query("SELECT * FROM scrobbles WHERE musicbrainzId IS NOT NULL AND musicbrainzId != 'not_found' AND energy IS NOT NULL AND energy >= 0.65")
    fun getHighEnergyScrobbles(): List<ScrobbleEntity>

    @Query("SELECT * FROM scrobbles WHERE musicbrainzId IS NOT NULL AND musicbrainzId != 'not_found' AND energy IS NOT NULL AND energy > 0 AND energy <= 0.55")
    fun getLowEnergyScrobbles(): List<ScrobbleEntity>

    @Query("SELECT * FROM scrobbles WHERE musicbrainzId IS NOT NULL AND musicbrainzId != 'not_found' AND mood = 'happy'")
    fun getHappyScrobbles(): List<ScrobbleEntity>

    @Query("SELECT * FROM scrobbles WHERE musicbrainzId IS NOT NULL AND musicbrainzId != 'not_found' AND mood = 'sad'")
    fun getSadScrobbles(): List<ScrobbleEntity>

    @Query("SELECT * FROM scrobbles WHERE musicbrainzId IS NOT NULL AND musicbrainzId != 'not_found' AND energy IS NOT NULL AND energy >= 0.6 AND danceability IS NOT NULL AND danceability >= 0.6")
    fun getPartyScrobbles(): List<ScrobbleEntity>

    @Query("SELECT * FROM scrobbles WHERE musicbrainzId IS NOT NULL AND musicbrainzId != 'not_found' AND energy IS NOT NULL AND energy >= 0.1 AND energy <= 0.6")
    fun getEveningScrobbles(): List<ScrobbleEntity>

    @Query("SELECT * FROM scrobbles WHERE musicbrainzId IS NOT NULL AND musicbrainzId != 'not_found' AND bpm IS NOT NULL AND ABS(bpm - :targetBpm) < 10")
    fun getScrobblesByBpm(targetBpm: Float): List<ScrobbleEntity>

    @Query("SELECT * FROM scrobbles WHERE musicbrainzId IS NOT NULL AND musicbrainzId != 'not_found' AND bpm IS NOT NULL AND bpm >= :minBpm AND bpm <= :maxBpm")
    fun getScrobblesByBpmRange(minBpm: Float, maxBpm: Float): List<ScrobbleEntity>
}
