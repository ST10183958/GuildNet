package com.codehijackers.guldnet.repository

import com.codehijackers.guldnet.model.PostComment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object CommentRepository {

    private val _comments = MutableStateFlow(
        listOf(
            PostComment(
                id = "1",
                postId = "1",
                authorName = "Jordan",
                content = "I've been working on a huge medieval castle!",
                createdAt = "Today"
            ),
            PostComment(
                id = "2",
                postId = "1",
                authorName = "Sam",
                content = "That sounds awesome. Are you building it in survival?",
                createdAt = "Today"
            ),
            PostComment(
                id = "3",
                postId = "2",
                authorName = "Alex",
                content = "I usually look for villages near a large cave system.",
                createdAt = "Yesterday"
            )
        )
    )

    val comments: StateFlow<List<PostComment>> =
        _comments.asStateFlow()

    fun getCommentsForPost(postId: String): List<PostComment> {
        return _comments.value.filter {
            it.postId == postId
        }
    }

    fun addComment(
        postId: String,
        authorName: String,
        content: String
    ) {
        val comment = PostComment(
            id = (_comments.value.size + 1).toString(),
            postId = postId,
            authorName = authorName,
            content = content,
            createdAt = "Just now"
        )

        _comments.value = _comments.value + comment
    }
}