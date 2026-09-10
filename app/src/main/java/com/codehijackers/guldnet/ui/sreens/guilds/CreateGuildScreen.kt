package com.codehijackers.guldnet.ui.screens.guilds

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateGuildScreen(
    onGuildCreated: (
        name: String,
        game: String,
        description: String
    ) -> Unit = { _, _, _ -> },
    onBackClicked: () -> Unit = {}
) {
    var name by remember {
        mutableStateOf("")
    }

    var game by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val isValid =
        name.isNotBlank() &&
                game.isNotBlank() &&
                description.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Create Guild",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Create a community for your game.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Guild Name")
            },
            placeholder = {
                Text("Example: Minecraft Builders")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = game,
            onValueChange = {
                game = it
            },
            label = {
                Text("Game")
            },
            placeholder = {
                Text("Example: Minecraft")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            label = {
                Text("Description")
            },
            placeholder = {
                Text("What is your Guild about?")
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 4
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {
                onGuildCreated(
                    name.trim(),
                    game.trim(),
                    description.trim()
                )
            },
            enabled = isValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Guild")
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedButton(
            onClick = onBackClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}