package com.codehijackers.guldnet.model

data class Guild(
    val id: String,
    val name: String,
    val game: String,
    val description: String,
    val memberCount: Int,
    val isJoined: Boolean = false
)