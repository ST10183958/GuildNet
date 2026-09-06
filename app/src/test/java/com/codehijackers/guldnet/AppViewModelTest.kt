package com.codehijackers.guldnet

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {

    private val _isUserAuthenticated = MutableStateFlow(false)


    val isUserAuthenticated: StateFlow<Boolean> =
        _isUserAuthenticated.asStateFlow()


    fun setAuthenticated(authenticated: Boolean) {
        _isUserAuthenticated.value = authenticated
    }
}