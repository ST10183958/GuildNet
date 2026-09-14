package com.codehijackers.guldnet.model

data class PostComment(
    val id: String,
    val postId: String,
    val authorName: String,
    val content: String,
    val createdAt: String
)