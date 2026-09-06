package com.codehijackers.guldnet.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class AppViewModel : ViewModel() {

    companion object {
        private const val TAG = "Guildnet.AppViewModel"
    }

    private val _isUserAuthenticated = MutableStateFlow(false)

    val isUserAuthenticated: StateFlow<Boolean> =
        _isUserAuthenticated.asStateFlow()

    init {
        Log.d(TAG, "AppViewModel created")
        Log.d(TAG, "Initial authentication state: false")
    }

    fun setAuthenticated(authenticated: Boolean) {
        Log.d(
            TAG,
            "Authentication state changing: " +
                    "${_isUserAuthenticated.value} -> $authenticated"
        )

        _isUserAuthenticated.value = authenticated
    }

    override fun onCleared() {
        Log.d(TAG, "AppViewModel cleared")
        super.onCleared()
    }
}