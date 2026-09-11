package com.codehijackers.guldnet.ui.screens.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.screens.posts.components.PostCard
import com.codehijackers.guldnet.viewmodel.PostViewModel

@Composable
fun GuildPostsScreen(
    guildId: String,
    onPostClicked: (String) -> Unit = {},
    onCreatePostClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    postViewModel: PostViewModel = viewModel()
) {
    val allPosts by postViewModel.posts.collectAsState()

    val posts = allPosts.filter {
        it.guildId == guildId
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Guild Posts",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Community discussions",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp)
        )

        Button(
            onClick = onCreatePostClicked,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Create Post")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            items(
                items = posts,
                key = { it.id }
            ) { post ->

                PostCard(
                    post = post,
                    onPostClicked = {
                        onPostClicked(post.id)
                    }
                )
            }

            item {
                Button(
                    onClick = onBackClicked
                ) {
                    Text("Back")
                }
            }
        }
    }
}