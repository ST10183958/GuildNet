package com.codehijackers.guldnet.ui.screens.posts.components

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
import com.codehijackers.guldnet.model.CommunityPost

@Composable
fun PostCard(
    post: CommunityPost,
    onPostClicked: () -> Unit
) {
    Card(
        onClick = onPostClicked,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = post.title,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "by ${post.authorName}",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = post.content,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 10.dp)
            )

            Row(
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Text(
                    text = "▲ ${post.upvotes}"
                )

                Text(
                    text = "  💬 ${post.commentCount}",
                    modifier = Modifier.padding(start = 12.dp)
                )

                Text(
                    text = "  ${post.createdAt}",
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }
}