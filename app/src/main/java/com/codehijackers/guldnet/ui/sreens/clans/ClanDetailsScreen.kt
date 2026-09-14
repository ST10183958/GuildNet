package com.codehijackers.guldnet.ui.screens.clans

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.screens.clans.components.ClanResponseCard
import com.codehijackers.guldnet.viewmodel.ClanResponseViewModel
import com.codehijackers.guldnet.viewmodel.ClanViewModel

@Composable
fun ClanDetailsScreen(
    clanId: String,
    onBackClicked: () -> Unit = {},
    clanViewModel: ClanViewModel = viewModel(),
    responseViewModel: ClanResponseViewModel = viewModel()
) {
    val clans by clanViewModel.clans.collectAsState()
    val responses by responseViewModel.responses.collectAsState()

    val clan = clans.find {
        it.id == clanId
    }

    var responseText by remember {
        mutableStateOf("")
    }

    if (clan == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                text = "Clan not found",
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

    val clanResponses = responses.filter {
        it.clanId == clanId
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {

            Text(
                text = clan.title,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "by ${clan.authorName}",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 4.dp)
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
                        text = clan.content,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Row(
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(
                            text = "▲ ${clan.upvotes}"
                        )

                        Text(
                            text = "💬 ${clanResponses.size}",
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Responses",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }

        items(
            items = clanResponses,
            key = { it.id }
        ) { response ->

            ClanResponseCard(
                response = response
            )
        }

        item {

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Add a Response",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = responseText,
                onValueChange = {
                    responseText = it
                },
                label = {
                    Text("Response")
                },
                placeholder = {
                    Text("Join the discussion...")
                },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {
                    responseViewModel.addResponse(
                        clanId = clanId,
                        content = responseText.trim(),
                        authorName = "You"
                    )

                    responseText = ""
                },
                enabled = responseText.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Post Response")
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedButton(
                onClick = onBackClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }
        }
    }
}