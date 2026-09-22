package com.codehijackers.guldnet.ui.screens.squads

import androidx.compose.foundation.background
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors

@Composable
fun CreateSquadScreen(
    onSquadCreated: (
        name: String,
        description: String
    ) -> Unit = { _, _ -> },
    onBackClicked: () -> Unit = {}
) {
    val colors = currentGuildnetThemeColors
    val strings = currentGuildnetStrings

    var name by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val isValid =
        name.isNotBlank() &&
                description.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = strings.createSquad,
            color = colors.textPrimary,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = strings.createSquadDescription,
            color = colors.textSecondary,
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
                Text(
                    text = strings.squadName,
                    color = colors.textSecondary
                )
            },
            placeholder = {
                Text(
                    text = strings.squadNamePlaceholder,
                    color = colors.textSecondary
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = colors.textPrimary,
                unfocusedTextColor = colors.textPrimary,
                focusedContainerColor = colors.surface,
                unfocusedContainerColor = colors.surface,
                focusedIndicatorColor = colors.primary,
                unfocusedIndicatorColor = colors.border,
                cursorColor = colors.primary,
                focusedLabelColor = colors.primary,
                unfocusedLabelColor = colors.textSecondary
            )
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
                Text(
                    text = strings.description,
                    color = colors.textSecondary
                )
            },
            placeholder = {
                Text(
                    text = strings.squadDescriptionPlaceholder,
                    color = colors.textSecondary
                )
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 4,
            colors = TextFieldDefaults.colors(
                focusedTextColor = colors.textPrimary,
                unfocusedTextColor = colors.textPrimary,
                focusedContainerColor = colors.surface,
                unfocusedContainerColor = colors.surface,
                focusedIndicatorColor = colors.primary,
                unfocusedIndicatorColor = colors.border,
                cursorColor = colors.primary,
                focusedLabelColor = colors.primary,
                unfocusedLabelColor = colors.textSecondary
            )
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {
                onSquadCreated(
                    name.trim(),
                    description.trim()
                )
            },
            enabled = isValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = strings.createSquad,
                color = Color.White
            )
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedButton(
            onClick = onBackClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = strings.cancel,
                color = colors.textPrimary
            )
        }
    }
}