package com.codehijackers.guldnet.ui.screens.squads

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.viewmodel.SquadViewModel

@Composable
fun SquadDetailsScreen(
    squadId: String,
    onChatClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    squadViewModel: SquadViewModel = viewModel()
) {
    val squads by squadViewModel.squads.collectAsState()

    val squad = squads.find {
        it.id == squadId
    }

    if (squad == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Squad not found",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedButton(
                onClick = onBackClicked
            ) {
                Text("Back")
            }
        }

        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = squad.name,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = squad.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "${squad.memberCount} members",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(top = 12.dp)
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = onChatClicked,
            enabled = squad.isJoined,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Open Chat")
        }

        if (!squad.isJoined) {
            Text(
                text = "Join this Squad to enter the chat.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedButton(
            onClick = onBackClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}