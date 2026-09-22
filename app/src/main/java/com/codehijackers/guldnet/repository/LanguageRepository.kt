package com.codehijackers.guldnet.repository

import com.codehijackers.guldnet.data.local.AppSettingsEntity
import com.codehijackers.guldnet.data.local.GuildnetDatabase
import com.codehijackers.guldnet.ui.theme.GuildnetThemeOption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
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

    private val scope =
        CoroutineScope(Dispatchers.IO)

    private lateinit var database: GuildnetDatabase

    private val _language =
        MutableStateFlow(AppLanguage.ENGLISH)

    val language: StateFlow<AppLanguage> =
        _language.asStateFlow()

    private val _theme =
        MutableStateFlow(GuildnetThemeOption.DARK_PURPLE)

    val theme: StateFlow<GuildnetThemeOption> =
        _theme.asStateFlow()

    fun initialize(database: GuildnetDatabase) {
        this.database = database

        scope.launch {
            database.appSettingsDao()
                .observeSettings()
                .collect { settings ->

                    if (settings != null) {

                        _language.value =
                            when (settings.language) {
                                "zu" ->
                                    AppLanguage.ZULU

                                else ->
                                    AppLanguage.ENGLISH
                            }

                        _theme.value =
                            GuildnetThemeOption
                                .fromKey(settings.theme)

                    } else {

                        database.appSettingsDao()
                            .saveSettings(
                                AppSettingsEntity(
                                    id = 1,
                                    language = "en",
                                    theme = "dark_purple"
                                )
                            )
                    }
                }
        }
    }

    fun setLanguage(
        language: AppLanguage
    ) {
        _language.value = language

        scope.launch {
            saveCurrentSettings()
        }
    }

    fun setTheme(
        theme: GuildnetThemeOption
    ) {
        _theme.value = theme

        scope.launch {
            saveCurrentSettings()
        }
    }

    private suspend fun saveCurrentSettings() {

        database.appSettingsDao()
            .saveSettings(
                AppSettingsEntity(
                    id = 1,
                    language = _language.value.code,
                    theme = _theme.value.key
                )
            )
    }
}