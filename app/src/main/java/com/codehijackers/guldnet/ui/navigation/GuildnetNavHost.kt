package com.codehijackers.guldnet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.codehijackers.guldnet.ui.screens.auth.LoginScreen
import com.codehijackers.guldnet.ui.screens.home.HomeScreen
import com.codehijackers.guldnet.ui.screens.guilds.GuildsScreen
import com.codehijackers.guldnet.ui.screens.guilds.GuildDetailsScreen
import com.codehijackers.guldnet.ui.screens.guilds.CreateGuildScreen
import com.codehijackers.guldnet.ui.screens.squads.SquadsScreen
import com.codehijackers.guldnet.ui.screens.squads.CreateSquadScreen
import com.codehijackers.guldnet.ui.screens.squads.SquadDetailsScreen
import com.codehijackers.guldnet.ui.screens.clans.ClansScreen
import com.codehijackers.guldnet.ui.screens.clans.CreateClanScreen
import com.codehijackers.guldnet.ui.screens.clans.ClanDetailsScreen
import com.codehijackers.guldnet.ui.screens.lorevault.LoreVaultScreen
import com.codehijackers.guldnet.ui.screens.lorevault.CreateGuideScreen
import com.codehijackers.guldnet.ui.screens.lorevault.GuideDetailsScreen
import com.codehijackers.guldnet.ui.screens.search.SearchScreen
import com.codehijackers.guldnet.ui.screens.profile.ProfileScreen
import com.codehijackers.guldnet.ui.screens.profile.EditProfileScreen
import com.codehijackers.guldnet.ui.screens.posts.GuildPostsScreen
import com.codehijackers.guldnet.ui.screens.posts.CreatePostScreen
import com.codehijackers.guldnet.ui.screens.posts.PostDetailsScreen
import com.codehijackers.guldnet.ui.screens.chat.ChatScreen

import com.codehijackers.guldnet.viewmodel.GuildViewModel
import com.codehijackers.guldnet.viewmodel.PostViewModel
import com.codehijackers.guldnet.viewmodel.ClanViewModel
import com.codehijackers.guldnet.viewmodel.GuideViewModel
import com.codehijackers.guldnet.viewmodel.SquadViewModel

@Composable
fun GuildnetNavHost(
    navController: NavHostController,
    startDestination: String,
    onLoginSuccess: () -> Unit = {},
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // ---------------------------------------------------------
        // LOGIN
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.Login.route
        ) {
            LoginScreen(
                onLoginClicked = {
                    onLoginSuccess()
                }
            )
        }

        // ---------------------------------------------------------
        // HOME
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.Home.route
        ) {
            HomeScreen()
        }

        // ---------------------------------------------------------
        // GUILDS
        // ---------------------------------------------------------

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

        // ---------------------------------------------------------
        // GUILD DETAILS
        // ---------------------------------------------------------

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
                },

                onPostsClicked = {
                    navController.navigate(
                        GuildnetRoutes.GuildPosts.createRoute(guildId)
                    )
                },

                // FIX: Clans button now navigates correctly.
                onClansClicked = {
                    navController.navigate(
                        GuildnetRoutes.Clans.createRoute(guildId)
                    )
                },

                // FIX: LoreVault button now navigates correctly.
                onLoreVaultClicked = {
                    navController.navigate(
                        GuildnetRoutes.LoreVault.createRoute(guildId)
                    )
                }
            )
        }

        // ---------------------------------------------------------
        // CREATE GUILD
        // ---------------------------------------------------------

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

        // ---------------------------------------------------------
        // GUILD POSTS
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.GuildPosts.route
        ) { backStackEntry ->

            val guildId =
                backStackEntry.arguments?.getString("guildId")
                    ?: return@composable

            GuildPostsScreen(
                guildId = guildId,

                onPostClicked = { postId ->
                    navController.navigate(
                        GuildnetRoutes.PostDetails.createRoute(postId)
                    )
                },

                onCreatePostClicked = {
                    navController.navigate(
                        GuildnetRoutes.CreatePost.createRoute(guildId)
                    )
                },

                onLoreVaultClicked = {
                    navController.navigate(
                        GuildnetRoutes.LoreVault.createRoute(guildId)
                    )
                },

                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // ---------------------------------------------------------
        // CREATE POST
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.CreatePost.route
        ) { backStackEntry ->

            val guildId =
                backStackEntry.arguments?.getString("guildId")
                    ?: return@composable

            val postViewModel: PostViewModel = viewModel()

            CreatePostScreen(
                guildId = guildId,

                onPostCreated = { title, content ->

                    postViewModel.createPost(
                        guildId = guildId,
                        title = title,
                        content = content,
                        authorName = "You"
                    )

                    navController.popBackStack()
                },

                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // ---------------------------------------------------------
        // POST DETAILS
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.PostDetails.route
        ) { backStackEntry ->

            val postId =
                backStackEntry.arguments?.getString("postId")
                    ?: return@composable

            PostDetailsScreen(
                postId = postId,

                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // ---------------------------------------------------------
        // CLANS
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.Clans.route
        ) { backStackEntry ->

            val guildId =
                backStackEntry.arguments?.getString("guildId")
                    ?: return@composable

            ClansScreen(
                guildId = guildId,

                onClanClicked = { clanId ->
                    navController.navigate(
                        GuildnetRoutes.ClanDetails.createRoute(clanId)
                    )
                },

                onCreateClanClicked = {
                    navController.navigate(
                        GuildnetRoutes.CreateClan.createRoute(guildId)
                    )
                },

                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // ---------------------------------------------------------
        // CREATE CLAN
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.CreateClan.route
        ) { backStackEntry ->

            val guildId =
                backStackEntry.arguments?.getString("guildId")
                    ?: return@composable

            val clanViewModel: ClanViewModel = viewModel()

            CreateClanScreen(
                guildId = guildId,

                onClanCreated = { title, content ->

                    clanViewModel.createClan(
                        guildId = guildId,
                        title = title,
                        content = content,
                        authorName = "You"
                    )

                    navController.popBackStack()
                },

                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // ---------------------------------------------------------
        // CLAN DETAILS
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.ClanDetails.route
        ) { backStackEntry ->

            val clanId =
                backStackEntry.arguments?.getString("clanId")
                    ?: return@composable

            ClanDetailsScreen(
                clanId = clanId,

                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // ---------------------------------------------------------
        // LOREVAULT
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.LoreVault.route
        ) { backStackEntry ->

            val guildId =
                backStackEntry.arguments?.getString("guildId")
                    ?: return@composable

            LoreVaultScreen(
                guildId = guildId,

                onGuideClicked = { guideId ->
                    navController.navigate(
                        GuildnetRoutes.GuideDetails.createRoute(guideId)
                    )
                },

                onCreateGuideClicked = {
                    navController.navigate(
                        GuildnetRoutes.CreateGuide.createRoute(guildId)
                    )
                },

                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // ---------------------------------------------------------
        // CREATE GUIDE
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.CreateGuide.route
        ) { backStackEntry ->

            val guildId =
                backStackEntry.arguments?.getString("guildId")
                    ?: return@composable

            val guideViewModel: GuideViewModel = viewModel()

            CreateGuideScreen(
                guildId = guildId,

                onGuideCreated = {
                        title,
                        description,
                        content,
                        category ->

                    guideViewModel.createGuide(
                        guildId = guildId,
                        title = title,
                        description = description,
                        content = content,
                        category = category,
                        authorName = "You"
                    )

                    navController.popBackStack()
                },

                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // ---------------------------------------------------------
        // GUIDE DETAILS
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.GuideDetails.route
        ) { backStackEntry ->

            val guideId =
                backStackEntry.arguments?.getString("guideId")
                    ?: return@composable

            GuideDetailsScreen(
                guideId = guideId,

                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // ---------------------------------------------------------
        // SQUADS
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.Squads.route
        ) {
            SquadsScreen(
                onSquadClicked = { squadId ->
                    navController.navigate(
                        GuildnetRoutes.SquadDetails.createRoute(squadId)
                    )
                },

                onCreateSquadClicked = {
                    navController.navigate(
                        GuildnetRoutes.CreateSquad.route
                    )
                }
            )
        }

        // ---------------------------------------------------------
        // CREATE SQUAD
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.CreateSquad.route
        ) {
            val squadViewModel: SquadViewModel = viewModel()

            CreateSquadScreen(
                onSquadCreated = { name, description ->

                    squadViewModel.createSquad(
                        name = name,
                        description = description,
                        ownerName = "You"
                    )

                    navController.popBackStack()
                },

                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // ---------------------------------------------------------
        // SQUAD DETAILS
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.SquadDetails.route
        ) { backStackEntry ->

            val squadId =
                backStackEntry.arguments?.getString("squadId")
                    ?: return@composable

            SquadDetailsScreen(
                squadId = squadId,

                onChatClicked = {
                    navController.navigate(
                        GuildnetRoutes.Chat.createRoute(squadId)
                    )
                },

                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // ---------------------------------------------------------
        // CHAT
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.Chat.route
        ) { backStackEntry ->

            val squadId =
                backStackEntry.arguments?.getString("squadId")
                    ?: return@composable

            ChatScreen(
                squadId = squadId,

                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // ---------------------------------------------------------
        // SEARCH
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.Search.route
        ) {
            SearchScreen()
        }

        // ---------------------------------------------------------
        // PROFILE
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.Profile.route
        ) {
            ProfileScreen(
                onEditProfileClicked = {
                    navController.navigate(
                        GuildnetRoutes.EditProfile.route
                    )
                },

                onSettingsClicked = {
                    // Settings will be added next.
                },

                onNotificationsClicked = {
                    // Notifications will be added next.
                },

                onAppearanceClicked = {
                    // Appearance will be added next.
                },

                onLogoutClicked = {
                    // Authentication/logout will be connected later.
                }
            )
        }

        // ---------------------------------------------------------
        // EDIT PROFILE
        // ---------------------------------------------------------

        composable(
            route = GuildnetRoutes.EditProfile.route
        ) {
            EditProfileScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}