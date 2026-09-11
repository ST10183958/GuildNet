package com.codehijackers.guldnet.repository

import com.codehijackers.guldnet.model.CommunityPost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PostRepository {

    private val _posts = MutableStateFlow(
        listOf(
            CommunityPost(
                id = "1",
                guildId = "1",
                title = "What are you building?",
                content = "Show everyone what you are currently building in Minecraft!",
                authorName = "Alex",
                createdAt = "Today",
                commentCount = 12,
                upvotes = 42
            ),
            CommunityPost(
                id = "2",
                guildId = "1",
                title = "Best survival seeds?",
                content = "What are your favourite Minecraft survival seeds?",
                authorName = "Jordan",
                createdAt = "Yesterday",
                commentCount = 8,
                upvotes = 27
            ),
            CommunityPost(
                id = "3",
                guildId = "2",
                title = "Best early-game build?",
                content = "What build would you recommend for starting a new Elden Ring character?",
                authorName = "Sam",
                createdAt = "2 days ago",
                commentCount = 19,
                upvotes = 51
            )
        )
    )

    val posts: StateFlow<List<CommunityPost>> =
        _posts.asStateFlow()

    fun getPostsForGuild(guildId: String): List<CommunityPost> {
        return _posts.value.filter {
            it.guildId == guildId
        }
    }

    fun createPost(
        guildId: String,
        title: String,
        content: String,
        authorName: String
    ) {
        val post = CommunityPost(
            id = (_posts.value.size + 1).toString(),
            guildId = guildId,
            title = title,
            content = content,
            authorName = authorName,
            createdAt = "Just now"
        )

        _posts.value = _posts.value + post
    }
}