package com.codehijackers.guldnet.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.model.Guild
import com.codehijackers.guldnet.model.Squad
import com.codehijackers.guldnet.viewmodel.GuildViewModel
import com.codehijackers.guldnet.viewmodel.HomeViewModel
import com.codehijackers.guldnet.viewmodel.SquadViewModel

@Composable
fun HomeScreen(
    guildViewModel: GuildViewModel = viewModel(),
    squadViewModel: SquadViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel(),
    onNotificationsClicked: () -> Unit = {}
) {
    val guilds by guildViewModel.guilds.collectAsState()
    val squads by squadViewModel.squads.collectAsState()
    val homeState by homeViewModel.uiState.collectAsState()

    val query = homeState.searchQuery.trim()
    val visibleGuilds = guilds.filter { guild ->
        query.isBlank() ||
            guild.name.contains(query, ignoreCase = true) ||
            guild.game.contains(query, ignoreCase = true)
    }
    val visibleSquads = squads.filter { squad ->
        query.isBlank() || squad.name.contains(query, ignoreCase = true)
    }
    val joinedGuilds = visibleGuilds.filter { it.isJoined }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            top = 16.dp,
            bottom = 24.dp
        ),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Good evening,",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "xDragonSlayer 🔥",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                BadgedBox(
                    badge = {
                        if (homeState.notificationsAvailable) Badge()
                    }
                ) {
                    IconButton(onClick = onNotificationsClicked) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications"
                        )
                    }
                }
            }
        }

        item {
            OutlinedTextField(
                value = homeState.searchQuery,
                onValueChange = homeViewModel::updateSearchQuery,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                },
                placeholder = {
                    Text("Search squads, guides, players...")
                },
                shape = RoundedCornerShape(14.dp)
            )
        }

        item {
            StatsRow()
        }

        item {
            HomeSectionHeader(
                title = "Recommended squads",
                onSeeAllClicked = {}
            )
        }

        if (visibleSquads.isEmpty()) {
            item {
                EmptyHomeMessage("No squads match your search.")
            }
        } else {
            items(
                items = visibleSquads.take(3),
                key = { it.id }
            ) { squad ->
                RecommendedSquadCard(
                    squad = squad,
                    onJoinClicked = {
                        if (squad.isJoined) {
                            squadViewModel.leaveSquad(squad.id)
                        } else {
                            squadViewModel.joinSquad(squad.id)
                        }
                    }
                )
            }
        }

        item {
            HomeSectionHeader(
                title = "Trending discussions",
                onSeeAllClicked = {}
            )
        }

        item {
            DiscussionCard(
                title = "Best loadout for ranked in Season 20?",
                author = "xDragonSlayer",
                responses = 42,
                age = "2h"
            )
        }
        item {
            DiscussionCard(
                title = "Share your controller settings",
                author = "Nova",
                responses = 18,
                age = "5h"
            )
        }

        item {
            HomeSectionHeader(
                title = "Your communities",
                onSeeAllClicked = {}
            )
        }

        if (joinedGuilds.isEmpty()) {
            item {
                EmptyHomeMessage("You have not joined any communities yet.")
            }
        } else {
            items(
                items = joinedGuilds.take(3),
                key = { it.id }
            ) { guild ->
                CommunityCard(guild)
            }
        }
    }
}

@Composable
private fun StatsRow() {
    val stats = listOf(
        "128" to "Matches",
        "68%" to "Win Rate",
        "Gold II" to "Rank"
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stats.forEach { (value, label) ->
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp, horizontal = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = value,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeSectionHeader(
    title: String,
    onSeeAllClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
        TextButton(onClick = onSeeAllClicked) {
            Text("See all")
        }
    }
}

@Composable
private fun RecommendedSquadCard(
    squad: Squad,
    onJoinClicked: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = squad.name.take(3).uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = squad.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "${squad.memberCount} members • ${squad.ownerName}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Button(
                onClick = onJoinClicked,
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(if (squad.isJoined) "Joined" else "Join")
            }
        }
    }
}

@Composable
private fun DiscussionCard(
    title: String,
    author: String,
    responses: Int,
    age: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.SportsEsports,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$author • $responses replies • $age",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CommunityCard(guild: Guild) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = guild.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "${guild.game} • ${guild.memberCount} members",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun EmptyHomeMessage(message: String) {
    Text(
        text = message,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}