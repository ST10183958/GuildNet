package com.codehijackers.guldnet.model

data class Clan(
    val id: String,
    val guildId: String,
    val title: String,
    val content: String,
    val authorName: String,
    val createdAt: String,
    val responseCount: Int = 0,
    val upvotes: Int = 0
)