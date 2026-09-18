package com.codehijackers.guldnet.model

data class UserProfile(
    val id: String,
    val displayName: String,
    val username: String,
    val bio: String,
    val email: String,
    val avatarUrl: String? = null
)