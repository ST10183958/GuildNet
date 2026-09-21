package com.codehijackers.guldnet.repository

import com.codehijackers.guldnet.data.local.AppSettingsEntity
import com.codehijackers.guldnet.data.local.GuildnetDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class AppLanguage(
    val code: String,
    val displayName: String
) {
    ENGLISH(
        code = "en",
        displayName = "English"
    ),
    ZULU(
        code = "zu",
        displayName = "isiZulu"
    )
}

object LanguageRepository {

    private val scope = CoroutineScope(
        Dispatchers.IO
    )

    private lateinit var database: GuildnetDatabase

    private val _language = MutableStateFlow(
        AppLanguage.ENGLISH
    )

    val language: StateFlow<AppLanguage> =
        _language.asStateFlow()

    fun initialize(database: GuildnetDatabase) {
        this.database = database

        scope.launch {
            database.appSettingsDao()
                .observeSettings()
                .collect { settings ->

                    if (settings != null) {
                        _language.value =
                            when (settings.language) {
                                "zu" -> AppLanguage.ZULU
                                else -> AppLanguage.ENGLISH
                            }
                    }
                }
        }
    }

    fun setLanguage(language: AppLanguage) {
        _language.value = language

        scope.launch {
            database.appSettingsDao().saveSettings(
                AppSettingsEntity(
                    id = 1,
                    language = language.code
                )
            )
        }
    }
}