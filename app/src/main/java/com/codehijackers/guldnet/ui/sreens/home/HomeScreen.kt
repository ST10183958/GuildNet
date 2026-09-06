package com.codehijackers.guldnet.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun HomeScreen() {

    Column(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Welcome to Guildnet!",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Your gaming communities await.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}