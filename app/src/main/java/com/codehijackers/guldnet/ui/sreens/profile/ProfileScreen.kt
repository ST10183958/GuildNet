package com.codehijackers.guldnet.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.screens.profile.components.ProfileStat
import com.codehijackers.guldnet.viewmodel.GuildViewModel
import com.codehijackers.guldnet.viewmodel.ProfileViewModel
import com.codehijackers.guldnet.viewmodel.SquadViewModel

@Composable
fun ProfileScreen(
    onEditProfileClicked: () -> Unit = {},
    onSettingsClicked: () -> Unit = {},
    onNotificationsClicked: () -> Unit = {},
    onAppearanceClicked: () -> Unit = {},
    onLogoutClicked: () -> Unit = {},
    profileViewModel: ProfileViewModel = viewModel(),
    guildViewModel: GuildViewModel = viewModel(),
    squadViewModel: SquadViewModel = viewModel()
) {
    val profile by profileViewModel.profile.collectAsState()
    val guilds by guildViewModel.guilds.collectAsState()
    val squads by squadViewModel.squads.collectAsState()

    val joinedGuilds = guilds.count { it.isJoined }
    val joinedSquads = squads.count { it.isJoined }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = profile.displayName,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "@${profile.username}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 4.dp)
        )

        Text(
            text = profile.bio,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = onEditProfileClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Edit Profile")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStat(
                    value = joinedGuilds.toString(),
                    label = "Guilds"
                )

                ProfileStat(
                    value = joinedSquads.toString(),
                    label = "Squads"
                )

                ProfileStat(
                    value = "0",
                    label = "Posts"
                )

                ProfileStat(
                    value = "0",
                    label = "Guides"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Account",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                OutlinedButton(
                    onClick = onSettingsClicked,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Settings")
                }

                OutlinedButton(
                    onClick = onNotificationsClicked,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Notifications")
                }

                OutlinedButton(
                    onClick = onAppearanceClicked,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Appearance")
                }
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedButton(
            onClick = onLogoutClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log Out")
        }
    }
}