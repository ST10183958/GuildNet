package com.codehijackers.guldnet.ui.screens.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileStat(
    value: String,
    label: String
) {
    Column {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium
        )
    }
}