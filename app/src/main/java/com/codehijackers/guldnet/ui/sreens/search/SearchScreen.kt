package com.codehijackers.guldnet.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors

@Composable
fun SearchScreen() {
    val colors = currentGuildnetThemeColors
    val strings = currentGuildnetStrings

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = strings.search,
            color = colors.textPrimary,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = strings.searchGuildnet,
            color = colors.textSecondary,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}