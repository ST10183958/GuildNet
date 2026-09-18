package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun toggleNotifications() {
        _uiState.update {
            it.copy(notificationsEnabled = !it.notificationsEnabled)
        }
    }
}