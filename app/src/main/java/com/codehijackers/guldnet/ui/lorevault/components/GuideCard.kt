package com.codehijackers.guldnet.ui.screens.lorevault.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehijackers.guldnet.model.Guide

@Composable
fun GuideCard(
    guide: Guide,
    onGuideClicked: () -> Unit
) {
    Card(
        onClick = onGuideClicked,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = guide.title,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = guide.category,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = guide.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )

            Row(
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Text(
                    text = "by ${guide.authorName}"
                )

                Text(
                    text = " • ${guide.viewCount} views",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}