package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import com.codehijackers.guldnet.model.PostComment
import com.codehijackers.guldnet.repository.CommentRepository
import kotlinx.coroutines.flow.StateFlow

class CommentViewModel : ViewModel() {

    private val repository = CommentRepository

    val comments: StateFlow<List<PostComment>> =
        repository.comments

    fun getCommentsForPost(
        postId: String
    ): List<PostComment> {
        return repository.getCommentsForPost(postId)
    }

    fun addComment(
        postId: String,
        content: String,
        authorName: String
    ) {
        repository.addComment(
            postId = postId,
            authorName = authorName,
            content = content
        )
    }
}