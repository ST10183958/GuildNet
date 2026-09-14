package com.codehijackers.guldnet.ui.screens.clans

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.codehijackers.guldnet.ui.screens.clans.components.ClanCard
import com.codehijackers.guldnet.viewmodel.ClanViewModel

@Composable
fun ClansScreen(
    guildId: String,
    onClanClicked: (String) -> Unit = {},
    onCreateClanClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    clanViewModel: ClanViewModel = viewModel()
) {
    val clans by clanViewModel.clans.collectAsState()

    val guildClans = clans.filter {
        it.guildId == guildId
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Clans",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Guild discussions and community topics.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp)
        )

        Button(
            onClick = onCreateClanClicked,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Create Clan")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                items = guildClans,
                key = { it.id }
            ) { clan ->

                ClanCard(
                    clan = clan,
                    onClanClicked = {
                        onClanClicked(clan.id)
                    }
                )
            }

            item {
                Button(
                    onClick = onBackClicked
                ) {
                    Text("Back")
                }
            }
        }
    }
}