package com.codehijackers.guldnet.model

data class CommunityPost(
    val id: String,
    val guildId: String,
    val title: String,
    val content: String,
    val authorName: String,
    val createdAt: String,
    val commentCount: Int = 0,
    val upvotes: Int = 0
)