package com.codehijackers.guldnet.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    val state by profileViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = onSettingsClicked) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Profile settings"
                )
            }
        }

        ProfileHeader(state)
        ProfileStats(state)

        Text(
            text = "ACCOUNT",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column {
                ProfileActionRow(
                    icon = Icons.Default.Person,
                    title = "Edit Profile",
                    onClick = onEditProfileClicked
                )
                ProfileActionRow(
                    icon = Icons.Default.Notifications,
                    title = "Notifications",
                    value = if (state.notificationsEnabled) "On" else "Off",
                    trailing = {
                        Switch(
                            checked = state.notificationsEnabled,
                            onCheckedChange = {
                                profileViewModel.toggleNotifications()
                            }
                        )
                    },
                    onClick = profileViewModel::toggleNotifications
                )
                ProfileActionRow(
                    icon = Icons.Default.Lock,
                    title = "Privacy",
                    value = state.privacy,
                    onClick = {}
                )
            }
        }

        Text(
            text = "PREFERENCES",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column {
                ProfileActionRow(
                    icon = Icons.Default.Settings,
                    title = "Theme",
                    value = state.theme,
                    onClick = onSettingsClicked
                )
                ProfileActionRow(
                    icon = Icons.Default.Language,
                    title = "Language",
                    value = state.language,
                    onClick = onSettingsClicked
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun ProfileHeader(state: ProfileUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BoxAvatar(initial = state.initial)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = state.displayName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = state.email,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ProfilePill(state.rank)
                ProfilePill("🏆 ${state.badge}")
            }
        }
    }
}

@Composable
private fun BoxAvatar(initial: String) {
    Row(
        modifier = Modifier
            .size(72.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = initial,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ProfilePill(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 10.dp, vertical = 5.dp)
    )
}

@Composable
private fun ProfileStats(state: ProfileUiState) {
    val stats = listOf(
        state.matches to "Matches",
        state.winRate to "Win Rate",
        state.squadRank to "Squad Rank",
        state.posts to "Posts"
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stats.forEach { (value, label) ->
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = value,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            value?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        if (trailing != null) {
            trailing()
        } else {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Open $title"
                )
            }
        }
    }
}