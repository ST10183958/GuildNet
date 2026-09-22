package com.codehijackers.guldnet.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehijackers.guldnet.repository.AppLanguage
import com.codehijackers.guldnet.repository.LanguageRepository
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.ui.theme.GuildnetThemeOption

@Composable
fun SettingsScreen(
    onBackClicked: () -> Unit = {}
) {
    val strings = currentGuildnetStrings

    val currentLanguage by LanguageRepository
        .language
        .collectAsState()

    val currentTheme by LanguageRepository
        .theme
        .collectAsState()

    val background = Color(0xFF0B0E14)
    val surface = Color(0xFF0F1727)
    val border = Color(0xFF26344D)
    val text = Color(0xFFF1F3FA)
    val mutedText = Color(0xFF8794AD)
    val purple = Color(0xFF9857FF)
    val purpleDark = Color(0xFF241545)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 10.dp,
                vertical = 14.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .clickable {
                        onBackClicked()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = strings.back,
                    tint = text,
                    modifier = Modifier.size(21.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(7.dp)
            )

            Column {
                Text(
                    text = strings.settings,
                    color = text,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = strings.language,
                    color = mutedText,
                    fontSize = 9.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        SettingsSectionTitle(
            text = strings.language,
            color = mutedText
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        SettingsHeaderCard(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Language,
                    contentDescription = null,
                    tint = purple,
                    modifier = Modifier.size(22.dp)
                )
            },
            title = strings.language,
            description = strings.languageSettingsDescription,
            surface = surface,
            border = border,
            iconBackground = purpleDark,
            text = text,
            mutedText = mutedText
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        LanguageOptionCard(
            label = strings.English,
            selected = currentLanguage == AppLanguage.ENGLISH,
            onClick = {
                LanguageRepository.setLanguage(
                    AppLanguage.ENGLISH
                )
            },
            surface = surface,
            selectedSurface = purpleDark,
            border = border,
            selectedBorder = purple,
            text = text,
            mutedText = mutedText,
            accent = purple
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        LanguageOptionCard(
            label = strings.isiZulu,
            selected = currentLanguage == AppLanguage.ZULU,
            onClick = {
                LanguageRepository.setLanguage(
                    AppLanguage.ZULU
                )
            },
            surface = surface,
            selectedSurface = purpleDark,
            border = border,
            selectedBorder = purple,
            text = text,
            mutedText = mutedText,
            accent = purple
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        SettingsSectionTitle(
            text = strings.theme,
            color = mutedText
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        SettingsHeaderCard(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Palette,
                    contentDescription = null,
                    tint = purple,
                    modifier = Modifier.size(22.dp)
                )
            },
            title = strings.theme,
            description = strings.themeSettingsDescription,
            surface = surface,
            border = border,
            iconBackground = purpleDark,
            text = text,
            mutedText = mutedText
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        ThemeOptionCard(
            name = strings.darkPurple,
            description = strings.darkPurpleDescription,
            theme = GuildnetThemeOption.DARK_PURPLE,
            selected = currentTheme == GuildnetThemeOption.DARK_PURPLE,
            onClick = {
                LanguageRepository.setTheme(
                    GuildnetThemeOption.DARK_PURPLE
                )
            },
            surface = surface,
            border = border,
            text = text,
            mutedText = mutedText,
            accent = Color(0xFF9C27B0),
            accentDark = Color(0xFF241545)
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        ThemeOptionCard(
            name = strings.royalPacific,
            description = strings.royalPacificDescription,
            theme = GuildnetThemeOption.ROYAL_PACIFIC,
            selected = currentTheme == GuildnetThemeOption.ROYAL_PACIFIC,
            onClick = {
                LanguageRepository.setTheme(
                    GuildnetThemeOption.ROYAL_PACIFIC
                )
            },
            surface = surface,
            border = border,
            text = text,
            mutedText = mutedText,
            accent = Color(0xFF22D3EE),
            accentDark = Color(0xFF102D5F)
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        ThemeOptionCard(
            name = strings.sabeins,
            description = strings.sabeinsDescription,
            theme = GuildnetThemeOption.SABEINS,
            selected = currentTheme == GuildnetThemeOption.SABEINS,
            onClick = {
                LanguageRepository.setTheme(
                    GuildnetThemeOption.SABEINS
                )
            },
            surface = surface,
            border = border,
            text = text,
            mutedText = mutedText,
            accent = Color(0xFFD4AF37),
            accentDark = Color(0xFF3B1515)
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        ThemeOptionCard(
            name = strings.velvetBloom,
            description = strings.velvetBloomDescription,
            theme = GuildnetThemeOption.VELVET_BLOOM,
            selected = currentTheme == GuildnetThemeOption.VELVET_BLOOM,
            onClick = {
                LanguageRepository.setTheme(
                    GuildnetThemeOption.VELVET_BLOOM
                )
            },
            surface = surface,
            border = border,
            text = text,
            mutedText = mutedText,
            accent = Color(0xFFF06292),
            accentDark = Color(0xFF35102D)
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )
    }
}

@Composable
private fun SettingsSectionTitle(
    text: String,
    color: Color
) {
    Text(
        text = text.uppercase(),
        color = color,
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 1.sp,
        modifier = Modifier.padding(start = 5.dp)
    )
}

@Composable
private fun SettingsHeaderCard(
    icon: @Composable () -> Unit,
    title: String,
    description: String,
    surface: Color,
    border: Color,
    iconBackground: Color,
    text: Color,
    mutedText: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(surface)
            .border(
                width = 1.dp,
                color = border,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(
                horizontal = 15.dp,
                vertical = 15.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(iconBackground),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }

        Spacer(
            modifier = Modifier.width(13.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = text,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            Text(
                text = description,
                color = mutedText,
                fontSize = 9.sp,
                lineHeight = 14.sp
            )
        }
    }
}

@Composable
private fun LanguageOptionCard(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    surface: Color,
    selectedSurface: Color,
    border: Color,
    selectedBorder: Color,
    text: Color,
    mutedText: Color,
    accent: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(
                if (selected) selectedSurface
                else surface
            )
            .border(
                width = 1.dp,
                color = if (selected) selectedBorder
                else border,
                shape = RoundedCornerShape(13.dp)
            )
            .clickable {
                onClick()
            }
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SelectionIndicator(
            selected = selected,
            accent = accent,
            mutedText = mutedText
        )

        Spacer(
            modifier = Modifier.width(13.dp)
        )

        Text(
            text = label,
            color = if (selected) text else mutedText,
            fontSize = 12.sp,
            fontWeight = if (selected) {
                FontWeight.SemiBold
            } else {
                FontWeight.Normal
            }
        )
    }
}

@Composable
private fun ThemeOptionCard(
    name: String,
    description: String,
    theme: GuildnetThemeOption,
    selected: Boolean,
    onClick: () -> Unit,
    surface: Color,
    border: Color,
    text: Color,
    mutedText: Color,
    accent: Color,
    accentDark: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(
                if (selected) accentDark
                else surface
            )
            .border(
                width = 1.dp,
                color = if (selected) {
                    accent.copy(alpha = 0.8f)
                } else {
                    border
                },
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 15.dp,
                vertical = 13.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SelectionIndicator(
            selected = selected,
            accent = accent,
            mutedText = mutedText
        )

        Spacer(
            modifier = Modifier.width(13.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = name,
                color = if (selected) text else mutedText,
                fontSize = 13.sp,
                fontWeight = if (selected) {
                    FontWeight.SemiBold
                } else {
                    FontWeight.Normal
                }
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            Text(
                text = description,
                color = mutedText,
                fontSize = 9.sp,
                lineHeight = 13.sp
            )
        }

        ThemePreview(
            theme = theme
        )
    }
}

@Composable
private fun SelectionIndicator(
    selected: Boolean,
    accent: Color,
    mutedText: Color
) {
    Box(
        modifier = Modifier
            .size(22.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = if (selected) accent
                else mutedText,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(accent)
            )
        }
    }
}

@Composable
private fun ThemePreview(
    theme: GuildnetThemeOption
) {
    val colors = when (theme) {
        GuildnetThemeOption.DARK_PURPLE ->
            listOf(
                Color(0xFF0B0E14),
                Color(0xFF6750A4),
                Color(0xFF9C27B0)
            )

        GuildnetThemeOption.ROYAL_PACIFIC ->
            listOf(
                Color(0xFF07111F),
                Color(0xFF2457D6),
                Color(0xFF22D3EE)
            )

        GuildnetThemeOption.SABEINS ->
            listOf(
                Color(0xFF080808),
                Color(0xFFB5121B),
                Color(0xFFD4AF37)
            )

        GuildnetThemeOption.VELVET_BLOOM ->
            listOf(
                Color(0xFF120811),
                Color(0xFFC21875),
                Color(0xFFF06292)
            )
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}