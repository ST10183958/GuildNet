package com.codehijackers.guldnet.ui.screens.guilds

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GuildDetailsScreen(
    guildId: String,
    onBackClicked: () -> Unit = {},
    onClansClicked: () -> Unit = {},
    onLoreVaultClicked: () -> Unit = {},
    onPostsClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Guild Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Guild ID: $guildId",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Guild",
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = "Selected gaming community",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Guild Features",
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
            Text("Clans")
        }

        OutlinedButton(
            onClick = onLoreVaultClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("LoreVault")
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
                Text("Back")
            }
        }
    }
}