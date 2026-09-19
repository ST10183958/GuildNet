package com.codehijackers.guldnet.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class AppLanguage(
    val code: String,
    val displayName: String
) {
    ENGLISH(code = "en", displayName = "English"),
    ZULU(code = "zu", displayName = "isiZulu")
}

/**
 * Holds the app's currently selected language so any screen can read
 * or change it. Mirrors the ProfileRepository pattern already used
 * in this codebase.
 */
object LanguageRepository {

    private val _language = MutableStateFlow(AppLanguage.ENGLISH)

    val language: StateFlow<AppLanguage> =
        _language.asStateFlow()

    fun setLanguage(language: AppLanguage) {
        _language.value = language
    }
}
