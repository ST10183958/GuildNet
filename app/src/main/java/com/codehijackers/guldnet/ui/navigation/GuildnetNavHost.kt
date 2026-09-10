package com.codehijackers.guldnet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.codehijackers.guldnet.ui.screens.auth.LoginScreen
import com.codehijackers.guldnet.ui.screens.home.HomeScreen
import com.codehijackers.guldnet.ui.screens.guilds.GuildsScreen
import com.codehijackers.guldnet.ui.screens.squads.SquadsScreen
import com.codehijackers.guldnet.ui.screens.clans.ClansScreen
import com.codehijackers.guldnet.ui.screens.lorevault.LoreVaultScreen
import com.codehijackers.guldnet.ui.screens.search.SearchScreen
import com.codehijackers.guldnet.ui.screens.profile.ProfileScreen
import com.codehijackers.guldnet.ui.screens.guilds.GuildDetailsScreen
import com.codehijackers.guldnet.ui.screens.guilds.CreateGuildScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.viewmodel.GuildViewModel


@Composable
fun GuildnetNavHost(
    navController: NavHostController,
    startDestination: String,
    onLoginSuccess: () -> Unit = {}
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
                    onLoginSuccess()
                }
            )
        }

        composable(
            route = GuildnetRoutes.Home.route
        ) {
            HomeScreen()
        }

        composable(
            route = GuildnetRoutes.Guilds.route
        ) {
            GuildsScreen(
                onGuildClicked = { guildId ->
                    navController.navigate(
                        GuildnetRoutes.GuildDetails.createRoute(guildId)
                    )
                },
                onCreateGuildClicked = {
                    navController.navigate(
                        GuildnetRoutes.CreateGuild.route
                    )
                }
            )
        }

        composable(
            route = GuildnetRoutes.GuildDetails.route
        ) { backStackEntry ->

            val guildId = backStackEntry
                .arguments
                ?.getString("guildId")
                ?: return@composable

            GuildDetailsScreen(
                guildId = guildId,
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = GuildnetRoutes.Squads.route
        ) {
            SquadsScreen()
        }

        composable(
            route = GuildnetRoutes.Clans.route
        ) {
            ClansScreen()
        }

        composable(
            route = GuildnetRoutes.LoreVault.route
        ) {
            LoreVaultScreen()
        }

        composable(
            route = GuildnetRoutes.Search.route
        ) {
            SearchScreen()
        }

        composable(
            route = GuildnetRoutes.Profile.route
        ) {
            ProfileScreen()
        }

        composable(
            route = GuildnetRoutes.CreateGuild.route
        ) {
            val guildViewModel: GuildViewModel = viewModel()

            CreateGuildScreen(
                onGuildCreated = { name, game, description ->

                    guildViewModel.createGuild(
                        name = name,
                        game = game,
                        description = description
                    )

                    navController.popBackStack()
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }


    }
}