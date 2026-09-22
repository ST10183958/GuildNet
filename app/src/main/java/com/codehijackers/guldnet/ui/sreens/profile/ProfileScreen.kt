package com.codehijackers.guldnet.ui.screens.profile

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
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors
import com.codehijackers.guldnet.viewmodel.ProfileUiState
import com.codehijackers.guldnet.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel(),
    onEditProfileClicked: () -> Unit = {},
    onSettingsClicked: () -> Unit = {},
    onNotificationsClicked: () -> Unit = {},
    onAppearanceClicked: () -> Unit = {},
    onLogoutClicked: () -> Unit = {}
) {
    val colors = currentGuildnetThemeColors
    val strings = currentGuildnetStrings
    val state by profileViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 10.dp,
                vertical = 14.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = strings.profile,
                color = colors.textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .clickable {
                        onSettingsClicked()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = strings.settings,
                    tint = colors.textSecondary,
                    modifier = Modifier.size(21.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier.height(17.dp)
        )

        ProfileHeader(
            state = state
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        ProfileStats(
            state = state,
            strings = strings
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        ProfileSectionLabel(
            text = strings.account
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        ProfileActionGroup {
            ProfileActionRow(
                icon = Icons.Outlined.PersonOutline,
                title = strings.editProfile,
                onClick = onEditProfileClicked
            )

            ProfileActionRow(
                icon = Icons.Outlined.NotificationsNone,
                title = strings.notifications,
                value = if (state.notificationsEnabled) {
                    strings.on
                } else {
                    strings.off
                },
                trailing = {
                    Switch(
                        checked = state.notificationsEnabled,
                        onCheckedChange = {
                            profileViewModel.toggleNotifications()
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = colors.primary,
                            uncheckedThumbColor = colors.textSecondary,
                            uncheckedTrackColor = colors.surfaceVariant,
                            uncheckedBorderColor = colors.border
                        )
                    )
                },
                onClick = {
                    profileViewModel.toggleNotifications()
                }
            )

            ProfileActionRow(
                icon = Icons.Outlined.Lock,
                title = strings.privacy,
                value = state.privacy,
                onClick = {}
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        ProfileSectionLabel(
            text = strings.preferences
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        ProfileActionGroup {


            ProfileActionRow(
                icon = Icons.Outlined.Language,
                title = strings.language,
                onClick = onSettingsClicked
            )
        }

        Spacer(
            modifier = Modifier.height(30.dp)
        )
    }
}

@Composable
private fun ProfileHeader(
    state: ProfileUiState
) {
    val colors = currentGuildnetThemeColors

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(17.dp))
            .background(colors.surface)
            .border(
                width = 1.dp,
                color = colors.border,
                shape = RoundedCornerShape(17.dp)
            )
            .padding(
                vertical = 21.dp,
                horizontal = 16.dp
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(78.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                        .background(colors.primary)
                        .border(
                            width = 2.dp,
                            color = colors.accent,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.initial,
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(colors.selectedBackground)
                        .border(
                            width = 1.dp,
                            color = colors.primary,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+",
                        color = colors.primary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = state.displayName,
                color = colors.textPrimary,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = state.email,
                color = colors.textSecondary,
                fontSize = 10.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                ProfilePill(
                    text = state.rank
                )

                ProfilePill(
                    text = "🏆 ${state.badge}"
                )
            }
        }
    }
}

@Composable
private fun ProfilePill(
    text: String
) {
    val colors = currentGuildnetThemeColors

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(colors.selectedBackground)
            .border(
                width = 1.dp,
                color = colors.primary.copy(alpha = 0.55f),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(
                horizontal = 10.dp,
                vertical = 4.dp
            )
    ) {
        Text(
            text = text,
            color = colors.primary,
            fontSize = 9.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ProfileStats(
    state: ProfileUiState,
    strings: com.codehijackers.guldnet.ui.localization.GuildnetStrings
) {
    val colors = currentGuildnetThemeColors

    val stats = listOf(
        state.matches to strings.matches,
        state.winRate to strings.winRate,
        state.squadRank to strings.squadRank,
        state.posts to strings.posts
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stats.forEach { (value, label) ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(69.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colors.surface)
                    .border(
                        width = 1.dp,
                        color = colors.border,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(
                        horizontal = 3.dp,
                        vertical = 8.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = value,
                    color = colors.textPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = label,
                    color = colors.textSecondary,
                    fontSize = 8.sp
                )
            }
        }
    }
}

@Composable
private fun ProfileSectionLabel(
    text: String
) {
    val colors = currentGuildnetThemeColors

    Text(
        text = text,
        color = colors.textSecondary,
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 1.sp,
        modifier = Modifier.padding(
            start = 5.dp
        )
    )
}

@Composable
private fun ProfileActionGroup(
    content: @Composable () -> Unit
) {
    val colors = currentGuildnetThemeColors

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(colors.surface)
            .border(
                width = 1.dp,
                color = colors.border,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        content()
    }
}

@Composable
private fun ProfileActionRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String? = null,
    trailing: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    val colors = currentGuildnetThemeColors

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                onClick()
            }
            .padding(horizontal = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colors.primary,
            modifier = Modifier.size(18.dp)
        )

        Spacer(
            modifier = Modifier.width(13.dp)
        )

        Text(
            text = title,
            color = colors.textPrimary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )

        if (value != null) {
            Text(
                text = value,
                color = colors.textSecondary,
                fontSize = 10.sp
            )

            Spacer(
                modifier = Modifier.width(6.dp)
            )
        }

        if (trailing != null) {
            Spacer(
                modifier = Modifier.width(5.dp)
            )

            trailing()
        } else {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = colors.border,
                modifier = Modifier.size(17.dp)
            )
        }
    }
}