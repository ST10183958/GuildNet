package com.codehijackers.guldnet.ui.localization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import com.codehijackers.guldnet.repository.AppLanguage

val LocalGuildnetLanguage =
    compositionLocalOf {
        AppLanguage.ENGLISH
    }

val LocalGuildnetStrings =
    compositionLocalOf {
        guildnetStrings(
            AppLanguage.ENGLISH
        )
    }

val currentGuildnetStrings: GuildnetStrings
    @Composable
    @ReadOnlyComposable
    get() = LocalGuildnetStrings.current