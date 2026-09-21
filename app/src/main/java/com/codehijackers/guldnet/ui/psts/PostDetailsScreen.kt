package com.codehijackers.guldnet.ui.screens.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.ui.screens.posts.components.CommentCard
import com.codehijackers.guldnet.viewmodel.CommentViewModel
import com.codehijackers.guldnet.viewmodel.PostViewModel

@Composable
fun PostDetailsScreen(
    postId: String,
    onBackClicked: () -> Unit = {},
    postViewModel: PostViewModel = viewModel(),
    commentViewModel: CommentViewModel = viewModel()
) {
    val strings = currentGuildnetStrings
    val posts by postViewModel.posts.collectAsState()
    val comments by commentViewModel.comments.collectAsState()

    val post = posts.find {
        it.id == postId
    }

    var commentText by remember {
        mutableStateOf("")
    }

    if (post == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = strings.postNotFound,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedButton(
                onClick = onBackClicked
            ) {
                Text(strings.back)
            }
        }

        return
    }

    val postComments = comments.filter {
        it.postId == postId
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = post.title,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "${strings.by} ${post.authorName}",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = post.content,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Row(
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(
                            text = "▲ ${post.upvotes}"
                        )

                        Text(
                            text = "💬 ${postComments.size}",
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = strings.comments,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }

        items(
            items = postComments,
            key = { it.id }
        ) { comment ->
            CommentCard(
                comment = comment
            )
        }

        item {
            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = strings.addComment,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = commentText,
                onValueChange = {
                    commentText = it
                },
                label = {
                    Text(strings.comment)
                },
                placeholder = {
                    Text(strings.joinDiscussion)
                },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {
                    commentViewModel.addComment(
                        postId = postId,
                        content = commentText.trim(),
                        authorName = strings.you
                    )

                    commentText = ""
                },
                enabled = commentText.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(strings.postComment)
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedButton(
                onClick = onBackClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(strings.back)
            }
        }
    }
}