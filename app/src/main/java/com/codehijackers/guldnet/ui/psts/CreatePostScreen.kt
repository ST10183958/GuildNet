package com.codehijackers.guldnet.ui.screens.posts

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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors

@Composable
fun CreatePostScreen(
    guildId: String,
    onPostCreated: (
        title: String,
        content: String
    ) -> Unit = { _, _ -> },
    onBackClicked: () -> Unit = {}
) {
    val colors = currentGuildnetThemeColors
    val strings = currentGuildnetStrings

    var title by remember {
        mutableStateOf("")
    }

    var content by remember {
        mutableStateOf("")
    }

    val isValid =
        title.isNotBlank() &&
                content.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = strings.createPost,
            color = colors.textPrimary,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = strings.startDiscussionWithGuild,
            color = colors.textSecondary,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
            },
            label = {
                Text(
                    text = strings.postTitle,
                    color = colors.textSecondary
                )
            },
            placeholder = {
                Text(
                    text = strings.postTitlePlaceholder,
                    color = colors.textSecondary
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colors.surface,
                unfocusedContainerColor = colors.surface,
                disabledContainerColor = colors.surfaceVariant,
                focusedBorderColor = colors.primary,
                unfocusedBorderColor = colors.border,
                focusedLabelColor = colors.primary,
                unfocusedLabelColor = colors.textSecondary,
                focusedTextColor = colors.textPrimary,
                unfocusedTextColor = colors.textPrimary,
                cursorColor = colors.primary
            )
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = content,
            onValueChange = {
                content = it
            },
            label = {
                Text(
                    text = strings.content,
                    color = colors.textSecondary
                )
            },
            placeholder = {
                Text(
                    text = strings.postContentPlaceholder,
                    color = colors.textSecondary
                )
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 6,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colors.surface,
                unfocusedContainerColor = colors.surface,
                disabledContainerColor = colors.surfaceVariant,
                focusedBorderColor = colors.primary,
                unfocusedBorderColor = colors.border,
                focusedLabelColor = colors.primary,
                unfocusedLabelColor = colors.textSecondary,
                focusedTextColor = colors.textPrimary,
                unfocusedTextColor = colors.textPrimary,
                cursorColor = colors.primary
            )
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {
                onPostCreated(
                    title.trim(),
                    content.trim()
                )
            },
            enabled = isValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = strings.createPost
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