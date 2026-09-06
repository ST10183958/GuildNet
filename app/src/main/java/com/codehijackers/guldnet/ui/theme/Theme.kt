package com.codehijackers.guldnet.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val GuildnetDarkColorScheme = darkColorScheme()
private val GuildnetLightColorScheme = lightColorScheme()

@Composable
fun GuildnetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> GuildnetDarkColorScheme
        else -> GuildnetLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = GuildnetTypography,
        content = content
    )
}