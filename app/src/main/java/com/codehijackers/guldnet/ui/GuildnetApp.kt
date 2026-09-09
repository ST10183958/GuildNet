package com.codehijackers.guldnet.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.codehijackers.guldnet.ui.navigation.GuildnetNavHost
import com.codehijackers.guldnet.ui.navigation.GuildnetRoutes
import com.codehijackers.guldnet.viewmodel.AppViewModel

@Composable
fun GuildnetApp(
    viewModel: AppViewModel
) {
    val isAuthenticated by viewModel.isUserAuthenticated.collectAsState()

    if (isAuthenticated) {

        GuildnetAuthenticatedApp()

    } else {

        val navController = rememberNavController()

        GuildnetNavHost(
            navController = navController,
            startDestination = GuildnetRoutes.Login.route,
            onLoginSuccess = {
                viewModel.setAuthenticated(true)
            }
        )
    }
}