package com.codehijackers.guldnet.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class GuildnetThemeColors(
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val border: Color,
    val primary: Color,
    val secondary: Color,
    val accent: Color,
    val selectedBackground: Color,
    val textPrimary: Color,
    val textSecondary: Color
)

val DarkPurpleThemeColors = GuildnetThemeColors(
    background = Color(0xFF0B0E14),
    surface = Color(0xFF131822),
    surfaceVariant = Color(0xFF0F1727),
    border = Color(0xFF26344D),
    primary = Color(0xFF6750A4),
    secondary = Color(0xFF625B71),
    accent = Color(0xFF9C27B0),
    selectedBackground = Color(0xFF241545),
    textPrimary = Color(0xFFF1F3FA),
    textSecondary = Color(0xFF8794AD)
)

val RoyalPacificThemeColors = GuildnetThemeColors(
    background = Color(0xFF07111F),
    surface = Color(0xFF0D1B2E),
    surfaceVariant = Color(0xFF10243A),
    border = Color(0xFF23415F),
    primary = Color(0xFF2457D6),
    secondary = Color(0xFF00B8D9),
    accent = Color(0xFF22D3EE),
    selectedBackground = Color(0xFF102D5F),
    textPrimary = Color(0xFFF4FBFF),
    textSecondary = Color(0xFF9BC6DD)
)

val SabeinsThemeColors = GuildnetThemeColors(
    background = Color(0xFF080808),
    surface = Color(0xFF15100D),
    surfaceVariant = Color(0xFF1E1510),
    border = Color(0xFF3A2A16),
    primary = Color(0xFFB5121B),
    secondary = Color(0xFF7A0C12),
    accent = Color(0xFFD4AF37),
    selectedBackground = Color(0xFF3B1515),
    textPrimary = Color(0xFFFFF8E7),
    textSecondary = Color(0xFFB8AFA0)
)

val VelvetBloomThemeColors = GuildnetThemeColors(
    background = Color(0xFF120811),
    surface = Color(0xFF21101F),
    surfaceVariant = Color(0xFF2A1226),
    border = Color(0xFF4B2444),
    primary = Color(0xFFC21875),
    secondary = Color(0xFF6A1B5A),
    accent = Color(0xFFF06292),
    selectedBackground = Color(0xFF35102D),
    textPrimary = Color(0xFFFFF5FA),
    textSecondary = Color(0xFFC9A8BB)
)

val LocalGuildnetThemeColors = staticCompositionLocalOf {
    DarkPurpleThemeColors
}

val currentGuildnetThemeColors: GuildnetThemeColors
    @Composable
    get() = LocalGuildnetThemeColors.current