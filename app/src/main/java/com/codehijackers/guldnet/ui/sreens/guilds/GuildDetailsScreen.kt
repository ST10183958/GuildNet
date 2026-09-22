package com.codehijackers.guldnet.ui.screens.guilds

import androidx.compose.foundation.background
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors
import com.codehijackers.guldnet.viewmodel.GuildViewModel

@Composable
fun GuildDetailsScreen(
    guildId: String,
    onBackClicked: () -> Unit = {},
    onClansClicked: () -> Unit = {},
    onLoreVaultClicked: () -> Unit = {},
    onPostsClicked: () -> Unit = {},
    guildViewModel: GuildViewModel = viewModel()
) {
    val colors = currentGuildnetThemeColors
    val guilds by guildViewModel.guilds.collectAsState()

    val guild = guilds.find { it.id == guildId }

    if (guild == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Guild not found",
                color = colors.textPrimary,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedButton(
                onClick = onBackClicked
            ) {
                Text(
                    text = "Back",
                    color = colors.textPrimary
                )
            }
        }

        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(16.dp)
    ) {
        Text(
            text = guild.name,
            color = colors.textPrimary,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = guild.game,
            color = colors.textSecondary,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = colors.surface
            ),
            border = androidx.compose.foundation.BorderStroke(
                width = 1.dp,
                color = colors.border
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = guild.description,
                    color = colors.textPrimary,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "${guild.memberCount} members",
                    color = colors.textSecondary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 12.dp)
                )

                Text(
                    text = if (guild.isJoined) {
                        "You are a member of this Guild"
                    } else {
                        "You are not a member of this Guild"
                    },
                    color = colors.textSecondary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(
                    onClick = {
                        if (guild.isJoined) {
                            guildViewModel.leaveGuild(guild.id)
                        } else {
                            guildViewModel.joinGuild(guild.id)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (guild.isJoined) {
                            "Leave Guild"
                        } else {
                            "Join Guild"
                        }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Guild Features",
            color = colors.textPrimary,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = onPostsClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Posts")
        }

        OutlinedButton(
            onClick = onClansClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Clans",
                color = colors.textPrimary
            )
        }

        OutlinedButton(
            onClick = onLoreVaultClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "LoreVault",
                color = colors.textPrimary
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = onBackClicked
            ) {
                Text(
                    text = "Back",
                    color = colors.textPrimary
                )
            }
        }
    }
}