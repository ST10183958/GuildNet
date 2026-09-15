package com.codehijackers.guldnet.ui.screens.squads.components

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
import com.codehijackers.guldnet.model.Squad

@Composable
fun SquadCard(
    squad: Squad,
    onSquadClicked: () -> Unit,
    onJoinClicked: () -> Unit
) {
    Card(
        onClick = onSquadClicked,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = squad.name,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Owner: ${squad.ownerName}",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = squad.description,
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
                    text = "${squad.memberCount} members"
                )

                Button(
                    onClick = onJoinClicked
                ) {
                    Text(
                        if (squad.isJoined) {
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