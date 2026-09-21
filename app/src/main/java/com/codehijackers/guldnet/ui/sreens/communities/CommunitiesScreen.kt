package com.codehijackers.guldnet.ui.screens.communities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings

@Composable
fun CommunitiesScreen() {
    val strings = currentGuildnetStrings

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = strings.communities,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = strings.findGamingCommunities,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}