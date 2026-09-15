package com.codehijackers.guldnet.ui.screens.squads

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
import com.codehijackers.guldnet.ui.screens.squads.components.SquadCard
import com.codehijackers.guldnet.viewmodel.SquadViewModel

@Composable
fun SquadsScreen(
    onSquadClicked: (String) -> Unit = {},
    onCreateSquadClicked: () -> Unit = {},
    squadViewModel: SquadViewModel = viewModel()
) {
    val squads by squadViewModel.squads.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Squads",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Join groups and chat with other gamers.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp)
        )

        Button(
            onClick = onCreateSquadClicked,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Create Squad")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                items = squads,
                key = { it.id }
            ) { squad ->

                SquadCard(
                    squad = squad,

                    onSquadClicked = {
                        onSquadClicked(squad.id)
                    },

                    onJoinClicked = {
                        if (squad.isJoined) {
                            squadViewModel.leaveSquad(squad.id)
                        } else {
                            squadViewModel.joinSquad(squad.id)
                        }
                    }
                )
            }
        }
    }
}