package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codehijackers.guldnet.data.local.GuildnetDatabaseProvider
import com.codehijackers.guldnet.data.local.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileUiState(
    val displayName: String = "xDragonSlayer",
    val email: String = "dragonslayer@guildnet.gg",
    val initial: String = "X",
    val rank: String = "Gold II",
    val badge: String = "Veteran",
    val matches: String = "128",
    val winRate: String = "68%",
    val squadRank: String = "#12",
    val posts: String = "47",
    val notificationsEnabled: Boolean = true,
    val privacy: String = "Friends",
    val theme: String = "System default",
    val language: String = "English"
)

class ProfileViewModel : ViewModel() {

    private val database = GuildnetDatabaseProvider.getDatabase()

    val profile: StateFlow<UserEntity?> =
        database.userDao()
            .observeLoggedInUser()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            database.userDao()
                .observeLoggedInUser()
                .collect { user ->

                    if (user != null) {
                        _uiState.update {
                            it.copy(
                                displayName = user.displayName,
                                email = user.email,
                                initial = user.displayName
                                    .trim()
                                    .firstOrNull()
                                    ?.uppercase()
                                    ?: "X"
                            )
                        }
                    }
                }
        }
    }

    fun updateProfile(
        displayName: String,
        username: String,
        bio: String
    ) {
        viewModelScope.launch {

            val currentUser =
                database.userDao()
                    .observeLoggedInUser()
                    .first()
                    ?: return@launch

            val updatedUser = currentUser.copy(
                displayName = displayName,
                username = username,
                bio = bio
            )

            database.userDao().updateUser(updatedUser)
        }
    }

    fun toggleNotifications() {
        _uiState.update {
            it.copy(
                notificationsEnabled = !it.notificationsEnabled
            )
        }
    }
}