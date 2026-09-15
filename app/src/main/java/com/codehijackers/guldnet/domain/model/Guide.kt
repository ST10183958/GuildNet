package com.codehijackers.guldnet.model

data class Guide(
    val id: String,
    val guildId: String,
    val title: String,
    val description: String,
    val content: String,
    val category: String,
    val authorName: String,
    val createdAt: String,
    val viewCount: Int = 0
)