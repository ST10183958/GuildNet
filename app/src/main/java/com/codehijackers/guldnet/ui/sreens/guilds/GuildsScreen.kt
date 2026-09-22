package com.codehijackers.guldnet.ui.screens.guilds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.ui.screens.guilds.components.GuildCard
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors
import com.codehijackers.guldnet.viewmodel.GuildViewModel

@Composable
fun GuildsScreen(
    onGuildClicked: (String) -> Unit = {},
    onCreateGuildClicked: () -> Unit = {},
    guildViewModel: GuildViewModel = viewModel()
) {
    val colors = currentGuildnetThemeColors
    val guilds by guildViewModel.guilds.collectAsState()
    val strings = currentGuildnetStrings

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(16.dp)
    ) {
        Text(
            text = strings.guilds,
            color = colors.textPrimary,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = strings.findAndJoinGamingCommunities,
            color = colors.textSecondary,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = onCreateGuildClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = strings.createGuild,
                color = colors.textPrimary
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                items = guilds,
                key = { it.id }
            ) { guild ->

                GuildCard(
                    guild = guild,
                    onGuildClicked = {
                        onGuildClicked(guild.id)
                    },
                    onJoinClicked = {
                        if (guild.isJoined) {
                            guildViewModel.leaveGuild(guild.id)
                        } else {
                            guildViewModel.joinGuild(guild.id)
                        }
                    }
                )
            }
        }
    }
}