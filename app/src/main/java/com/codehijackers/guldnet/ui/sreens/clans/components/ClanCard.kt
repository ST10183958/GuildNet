package com.codehijackers.guldnet.ui.screens.clans.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehijackers.guldnet.model.Clan

@Composable
fun ClanCard(
    clan: Clan,
    onClanClicked: () -> Unit
) {
    Card(
        onClick = onClanClicked,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = clan.title,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "by ${clan.authorName}",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = clan.content,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 10.dp)
            )

            Row(
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Text("▲ ${clan.upvotes}")

                Text(
                    text = "💬 ${clan.responseCount}",
                    modifier = Modifier.padding(start = 16.dp)
                )

                Text(
                    text = clan.createdAt,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}