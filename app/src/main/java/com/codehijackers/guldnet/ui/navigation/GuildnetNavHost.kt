package com.codehijackers.guldnet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codehijackers.guldnet.ui.screens.auth.LoginScreen
import com.codehijackers.guldnet.ui.screens.home.HomeScreen

@Composable
fun GuildnetNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(
            route = GuildnetRoutes.Login.route
        ) {
            LoginScreen(
                onLoginClicked = {
                    navController.navigate(
                        GuildnetRoutes.Home.route
                    ) {
                        popUpTo(GuildnetRoutes.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = GuildnetRoutes.Home.route
        ) {
            HomeScreen()
        }
    }
}