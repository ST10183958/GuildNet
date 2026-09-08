package com.codehijackers.guldnet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codehijackers.guldnet.ui.screens.auth.LoginScreen
import com.codehijackers.guldnet.ui.screens.home.HomeScreen
import com.codehijackers.guldnet.ui.screens.communities.CommunitiesScreen
import com.codehijackers.guldnet.ui.screens.search.SearchScreen
import com.codehijackers.guldnet.ui.screens.profile.ProfileScreen

@Composable
fun GuildnetNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(GuildnetRoutes.Login.route) {
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

        composable(GuildnetRoutes.Home.route) {
            HomeScreen()
        }

        composable(GuildnetRoutes.Communities.route) {
            CommunitiesScreen()
        }

        composable(GuildnetRoutes.Search.route) {
            SearchScreen()
        }

        composable(GuildnetRoutes.Profile.route) {
            ProfileScreen()
        }
    }
}