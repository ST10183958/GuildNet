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

    val navController = rememberNavController()

    val startDestination =
        if (isAuthenticated) {
            GuildnetRoutes.Home.route
        } else {
            GuildnetRoutes.Login.route
        }

    GuildnetNavHost(
        navController = navController,
        startDestination = startDestination
    )
}