package com.codehijackers.guldnet.ui.screens.lorevault

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
import com.codehijackers.guldnet.ui.screens.lorevault.components.GuideCard
import com.codehijackers.guldnet.viewmodel.GuideViewModel

@Composable
fun LoreVaultScreen(
    guildId: String,
    onGuideClicked: (String) -> Unit = {},
    onCreateGuideClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    guideViewModel: GuideViewModel = viewModel()
) {
    val guides by guideViewModel.guides.collectAsState()

    // Only show guides belonging to the current Guild.
    val guildGuides = guides.filter {
        it.guildId == guildId
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "LoreVault",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Guides, tutorials and gaming knowledge.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp)
        )

        Button(
            onClick = onCreateGuideClicked,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Create Guide")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            items(
                items = guildGuides,
                key = { guide -> guide.id }
            ) { guide ->

                GuideCard(
                    guide = guide,
                    onGuideClicked = {
                        onGuideClicked(guide.id)
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