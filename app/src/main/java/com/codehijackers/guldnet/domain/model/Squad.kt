package com.codehijackers.guldnet.model

data class Squad(
    val id: String,
    val name: String,
    val description: String,
    val ownerName: String,
    val memberCount: Int,
    val isJoined: Boolean = false
)