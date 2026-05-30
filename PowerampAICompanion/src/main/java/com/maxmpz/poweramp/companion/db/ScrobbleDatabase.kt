package com.maxmpz.poweramp.companion.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ScrobbleEntity::class], version = 3, exportSchema = false)
abstract class ScrobbleDatabase : RoomDatabase() {

    abstract fun scrobbleDao(): ScrobbleDao

    companion object {
        @Volatile
        private var INSTANCE: ScrobbleDatabase? = null

        fun getDatabase(context: Context): ScrobbleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScrobbleDatabase::class.java,
                    "scrobble_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
