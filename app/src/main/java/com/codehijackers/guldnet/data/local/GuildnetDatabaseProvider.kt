package com.codehijackers.guldnet.data.local

import android.content.Context

object GuildnetDatabaseProvider {

    private lateinit var database: GuildnetDatabase

    fun initialize(context: Context) {
        database = GuildnetDatabase.getDatabase(context)
    }

    fun getDatabase(): GuildnetDatabase {
        return database
    }
}