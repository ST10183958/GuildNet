package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HomeUiState(
    val searchQuery: String = "",
    val notificationsAvailable: Boolean = true
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }
}
