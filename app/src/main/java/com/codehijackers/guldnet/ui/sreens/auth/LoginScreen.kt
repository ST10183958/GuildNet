package com.codehijackers.guldnet.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Guildnet authentication screen.
 *
 * The SSO authentication system will be integrated here
 * during the authentication development phase.
 */
@Composable
fun LoginScreen(
    onLoginClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Guildnet",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Where Gamers Unite",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        Button(
            onClick = onLoginClicked,
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text(
                text = "Sign in with SSO"
            )
        }
    }
}