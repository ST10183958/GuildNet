package com.codehijackers.guldnet.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import com.codehijackers.guldnet.repository.LanguageRepository

private val DarkPurpleColorScheme = darkColorScheme(
    primary = GuildnetPrimary,
    secondary = GuildnetSecondary,
    tertiary = GuildnetTertiary
)

private val RoyalPacificColorScheme = darkColorScheme(
    primary = RoyalPacificPrimary,
    secondary = RoyalPacificSecondary,
    tertiary = RoyalPacificTertiary
)

private val SabeinsColorScheme = darkColorScheme(
    primary = SabeinsPrimary,
    secondary = SabeinsSecondary,
    tertiary = SabeinsTertiary
)

private val VelvetBloomColorScheme = darkColorScheme(
    primary = VelvetBloomPrimary,
    secondary = VelvetBloomSecondary,
    tertiary = VelvetBloomTertiary
)

@Composable
fun GuildnetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val selectedTheme by LanguageRepository
        .theme
        .collectAsState()

    val colorScheme = when (selectedTheme) {

        GuildnetThemeOption.DARK_PURPLE ->
            DarkPurpleColorScheme

        GuildnetThemeOption.ROYAL_PACIFIC ->
            RoyalPacificColorScheme

        GuildnetThemeOption.SABEINS ->
            SabeinsColorScheme

        GuildnetThemeOption.VELVET_BLOOM ->
            VelvetBloomColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = GuildnetTypography,
        content = content
    )
}