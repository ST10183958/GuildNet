package com.codehijackers.guldnet.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.screens.home.components.HomeDiscussionCard
import com.codehijackers.guldnet.ui.screens.home.components.HomeGuildCard
import com.codehijackers.guldnet.ui.screens.home.components.HomeSection
import com.codehijackers.guldnet.ui.screens.home.components.HomeSquadCard
import com.codehijackers.guldnet.viewmodel.GuildViewModel
import com.codehijackers.guldnet.viewmodel.ProfileViewModel
import com.codehijackers.guldnet.viewmodel.SquadViewModel

@Composable
fun HomeScreen(
    guildViewModel: GuildViewModel = viewModel(),
    squadViewModel: SquadViewModel = viewModel(),
    profileViewModel: ProfileViewModel = viewModel()
) {
    val guilds by guildViewModel.guilds.collectAsState()
    val squads by squadViewModel.squads.collectAsState()

    val joinedGuilds = guilds.filter { it.isJoined }
    val joinedSquads = squads.filter { it.isJoined }
    val profile by profileViewModel.profile.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Column {
                Text(
                    text = "Welcome back, ${profile.displayName}!",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "Your gaming communities await.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        item {
            HomeSection(
                title = "Your Guilds"
            ) {
                if (joinedGuilds.isEmpty()) {
                    Text(
                        text = "You haven't joined any Guilds yet.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    joinedGuilds.take(3).forEach { guild ->
                        HomeGuildCard(guild)
                    }
                }
            }
        }

        item {
            HomeSection(
                title = "Recent Discussions"
            ) {
                HomeDiscussionCard(
                    title = "Best Minecraft starter farms?",
                    author = "Alex",
                    responses = 14
                )

                HomeDiscussionCard(
                    title = "Favourite Elden Ring boss?",
                    author = "Sam",
                    responses = 21
                )

                HomeDiscussionCard(
                    title = "What are you building?",
                    author = "Jordan",
                    responses = 12
                )
            }
        }

        item {
            HomeSection(
                title = "Your Squads"
            ) {
                if (joinedSquads.isEmpty()) {
                    Text(
                        text = "You haven't joined any Squads yet.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    joinedSquads.take(3).forEach { squad ->
                        HomeSquadCard(squad)
                    }
                }
            }
        }
    }
}