package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import com.codehijackers.guldnet.model.CommunityPost
import com.codehijackers.guldnet.repository.PostRepository
import kotlinx.coroutines.flow.StateFlow

class PostViewModel : ViewModel() {

    private val repository = PostRepository

    val posts: StateFlow<List<CommunityPost>> =
        repository.posts

    fun getPostsForGuild(
        guildId: String
    ): List<CommunityPost> {
        return repository.getPostsForGuild(guildId)
    }

    fun createPost(
        guildId: String,
        title: String,
        content: String,
        authorName: String
    ) {
        repository.createPost(
            guildId = guildId,
            title = title,
            content = content,
            authorName = authorName
        )
    }
}