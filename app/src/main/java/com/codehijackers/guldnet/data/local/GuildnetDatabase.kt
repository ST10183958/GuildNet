package com.codehijackers.guldnet.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserEntity::class,
        AppSettingsEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class GuildnetDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun appSettingsDao(): AppSettingsDao

    companion object {
        @Volatile
        private var INSTANCE: GuildnetDatabase? = null

        fun getDatabase(context: Context): GuildnetDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GuildnetDatabase::class.java,
                    "guildnet_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}