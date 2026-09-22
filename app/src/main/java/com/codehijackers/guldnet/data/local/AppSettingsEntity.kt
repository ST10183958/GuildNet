package com.codehijackers.guldnet.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_settings")
data class AppSettingsEntity(
    @PrimaryKey
    val id: Int = 1,
    val language: String = "en",
    val theme: String = "dark_purple"
)