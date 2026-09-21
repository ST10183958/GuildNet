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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Language
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

private val GuildnetSurface = Color(0xFF0F1727)
private val GuildnetSurfaceLight = Color(0xFF121C2E)
private val GuildnetBorder = Color(0xFF26344D)
private val GuildnetPurple = Color(0xFF9857FF)
private val GuildnetPurpleDark = Color(0xFF241545)
private val GuildnetText = Color(0xFFF1F3FA)
private val GuildnetMutedText = Color(0xFF8794AD)

@Composable
fun SettingsScreen(
    onBackClicked: () -> Unit = {}
) {
    val strings = currentGuildnetStrings
    val currentLanguage by LanguageRepository.language.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
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
                    tint = GuildnetText,
                    modifier = Modifier.size(21.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(7.dp)
            )

            Column {
                Text(
                    text = strings.settings,
                    color = GuildnetText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = strings.language,
                    color = GuildnetMutedText,
                    fontSize = 9.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = strings.language.uppercase(),
            color = Color(0xFF657394),
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(
                start = 5.dp
            )
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(GuildnetSurface)
                .border(
                    width = 1.dp,
                    color = GuildnetBorder,
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
                    .background(GuildnetPurpleDark),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Language,
                    contentDescription = null,
                    tint = GuildnetPurple,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(13.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = strings.language,
                    color = GuildnetText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = strings.languageSettingsDescription,
                    color = GuildnetMutedText,
                    fontSize = 9.sp,
                    lineHeight = 14.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        LanguageOptionCard(
            label = strings.English,
            selected = currentLanguage == AppLanguage.ENGLISH,
            onClick = {
                LanguageRepository.setLanguage(AppLanguage.ENGLISH)
            }
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        LanguageOptionCard(
            label = strings.isiZulu,
            selected = currentLanguage == AppLanguage.ZULU,
            onClick = {
                LanguageRepository.setLanguage(AppLanguage.ZULU)
            }
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )
    }
}

@Composable
private fun LanguageOptionCard(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(
                if (selected) {
                    GuildnetPurpleDark
                } else {
                    GuildnetSurface
                }
            )
            .border(
                width = 1.dp,
                color = if (selected) {
                    GuildnetPurple.copy(alpha = 0.65f)
                } else {
                    GuildnetBorder
                },
                shape = RoundedCornerShape(13.dp)
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 15.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = if (selected) {
                        GuildnetPurple
                    } else {
                        GuildnetMutedText
                    },
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(GuildnetPurple)
                )
            }
        }

        Spacer(
            modifier = Modifier.width(13.dp)
        )

        Text(
            text = label,
            color = if (selected) {
                GuildnetText
            } else {
                GuildnetMutedText
            },
            fontSize = 12.sp,
            fontWeight = if (selected) {
                FontWeight.SemiBold
            } else {
                FontWeight.Normal
            }
        )
    }
}