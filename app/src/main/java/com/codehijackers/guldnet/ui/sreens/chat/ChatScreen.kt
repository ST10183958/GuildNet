package com.codehijackers.guldnet.ui.screens.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.viewmodel.MessageViewModel

@Composable
fun ChatScreen(
    squadId: String,
    onBackClicked: () -> Unit = {},
    messageViewModel: MessageViewModel = viewModel()
) {
    val messages by messageViewModel.messages.collectAsState()

    val squadMessages = messages.filter {
        it.squadId == squadId
    }

    var messageText by remember {
        mutableStateOf("")
    }

    val listState = rememberLazyListState()

    /*
     * Automatically scroll to the newest message.
     */
    LaunchedEffect(squadMessages.size) {
        if (squadMessages.isNotEmpty()) {
            listState.animateScrollToItem(
                squadMessages.lastIndex
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Squad Chat",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Squad ID: $squadId",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(
            modifier = Modifier.padding(top = 12.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(
                items = squadMessages,
                key = { it.id }
            ) { message ->

                ChatMessageBubble(
                    senderName = message.senderName,
                    content = message.content,
                    createdAt = message.createdAt
                )
            }
        }

        Spacer(
            modifier = Modifier.padding(top = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {

            OutlinedTextField(
                value = messageText,
                onValueChange = {
                    messageText = it
                },
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text("Type a message...")
                },
                maxLines = 4
            )

            Spacer(
                modifier = Modifier.padding(start = 8.dp)
            )

            Button(
                onClick = {
                    messageViewModel.sendMessage(
                        squadId = squadId,
                        content = messageText.trim(),
                        senderName = "You"
                    )

                    messageText = ""
                },
                enabled = messageText.isNotBlank()
            ) {
                Text("Send")
            }
        }

        Spacer(
            modifier = Modifier.padding(top = 8.dp)
        )

        OutlinedButton(
            onClick = onBackClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}