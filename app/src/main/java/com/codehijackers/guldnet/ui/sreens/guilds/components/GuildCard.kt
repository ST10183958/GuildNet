package com.codehijackers.guldnet.ui.screens.guilds.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehijackers.guldnet.model.Guild

@Composable
fun GuildCard(
    guild: Guild,
    onGuildClicked: () -> Unit,
    onJoinClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        onClick = onGuildClicked
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = guild.name,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = guild.game,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = guild.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "${guild.memberCount} members",
                    style = MaterialTheme.typography.bodyMedium
                )

                Button(
                    onClick = onJoinClicked
                ) {
                    Text(
                        text = if (guild.isJoined) {
                            "Joined"
                        } else {
                            "Join"
                        }
                    )
                }
            }
        }
    }
}