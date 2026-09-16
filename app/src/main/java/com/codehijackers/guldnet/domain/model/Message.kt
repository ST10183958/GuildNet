package com.codehijackers.guldnet.model

data class Message(
    val id: String,
    val squadId: String,
    val senderName: String,
    val content: String,
    val createdAt: String
)