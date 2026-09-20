package com.codehijackers.guldnet.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.model.Guild
import com.codehijackers.guldnet.model.Squad
import com.codehijackers.guldnet.viewmodel.GuildViewModel
import com.codehijackers.guldnet.viewmodel.HomeViewModel
import com.codehijackers.guldnet.viewmodel.SquadViewModel

private val GuildnetBackground = Color(0xFF080C18)
private val GuildnetSurface = Color(0xFF0F1727)
private val GuildnetSurfaceLight = Color(0xFF121C2E)
private val GuildnetBorder = Color(0xFF26344D)

private val GuildnetPurple = Color(0xFF9857FF)
private val GuildnetPurpleDark = Color(0xFF241545)

private val GuildnetText = Color(0xFFF1F3FA)
private val GuildnetMutedText = Color(0xFF8794AD)
private val GuildnetGreen = Color(0xFF35D98A)
private val GuildnetRed = Color(0xFFFF4F62)

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
        query.isBlank() ||
                squad.name.contains(query, ignoreCase = true)
    }

    val joinedGuilds = visibleGuilds.filter { it.isJoined }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(horizontal = 20.dp),

        contentPadding = PaddingValues(
            top = 14.dp,
            bottom = 90.dp
        ),

        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        item {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "Good evening,",
                        color = GuildnetMutedText,
                        fontSize = 13.sp
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text = "xDragonSlayer 🔥",
                        color = GuildnetText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box {

                    IconButton(
                        onClick = onNotificationsClicked,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(GuildnetPurple)
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // Notification dot
                    if (homeState.notificationsAvailable) {

                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(GuildnetRed)
                                .align(Alignment.TopEnd)
                        )
                    }
                }
            }
        }

        item {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .background(GuildnetSurface)
                    .border(
                        width = 1.dp,
                        color = GuildnetBorder,
                        shape = RoundedCornerShape(13.dp)
                    )
                    .padding(horizontal = 13.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = Color(0xFF52617D),
                    modifier = Modifier.size(18.dp)
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                androidx.compose.foundation.text.BasicTextField(
                    value = homeState.searchQuery,
                    onValueChange = homeViewModel::updateSearchQuery,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = GuildnetText,
                        fontSize = 12.sp
                    ),
                    decorationBox = { innerTextField ->

                        if (homeState.searchQuery.isEmpty()) {

                            Text(
                                text = "Search squads, guides, players...",
                                color = GuildnetMutedText,
                                fontSize = 12.sp
                            )
                        }

                        innerTextField()
                    }
                )
            }
        }

        item {
            StatsRow()
        }

        item {

            HomeSectionHeader(
                title = "Recommended Squads",
                onSeeAllClicked = {}
            )
        }

        if (visibleSquads.isEmpty()) {

            item {
                EmptyHomeMessage(
                    message = "No squads match your search."
                )
            }

        } else {

            items(
                items = visibleSquads.take(3),
                key = { "squad_${it.id}" }
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
                title = "Trending Discussions",
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
                title = "Your Communities",
                onSeeAllClicked = {}
            )
        }

        if (joinedGuilds.isEmpty()) {

            item {
                EmptyHomeMessage(
                    message = "You have not joined any communities yet."
                )
            }

        } else {

            items(
                items = joinedGuilds.take(3),
                key = { "guild_${it.id}" }
            ) { guild ->

                CommunityCard(guild)
            }
        }
    }
}

@Composable
private fun StatsRow() {

    val stats = listOf(
        Triple(
            "128",
            "Matches",
            Icons.Outlined.SportsEsports
        ),
        Triple(
            "68%",
            "Win Rate",
            Icons.Outlined.EmojiEvents
        ),
        Triple(
            "Gold II",
            "Rank",
            Icons.Outlined.Star
        )
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        stats.forEach { (value, label, icon) ->

            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(116.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(GuildnetSurface)
                    .border(
                        width = 1.dp,
                        color = GuildnetBorder,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(
                        horizontal = 7.dp,
                        vertical = 11.dp
                    ),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = GuildnetText,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(
                    modifier = Modifier.height(7.dp)
                )

                Text(
                    text = value,
                    color = GuildnetText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = label,
                    color = GuildnetMutedText,
                    fontSize = 9.sp
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = when (label) {
                        "Matches" -> "+3 today"
                        "Win Rate" -> "Top 15%"
                        else -> "↑ from Silver"
                    },
                    color = GuildnetGreen,
                    fontSize = 8.sp
                )
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
            color = GuildnetText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.8.sp
        )

        Text(
            text = "See All",
            color = GuildnetPurple,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable {
                onSeeAllClicked()
            }
        )
    }
}


@Composable
private fun RecommendedSquadCard(
    squad: Squad,
    onJoinClicked: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(GuildnetSurface)
            .border(
                width = 1.dp,
                color = GuildnetBorder,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(horizontal = 14.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        // Game icon
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    when {
                        squad.name.contains(
                            "Apex",
                            ignoreCase = true
                        ) -> Color(0xFF2B1820)

                        squad.name.contains(
                            "Valorant",
                            ignoreCase = true
                        ) -> Color(0xFF102D28)

                        else -> Color(0xFF142844)
                    }
                )
                .border(
                    width = 1.dp,
                    color = when {
                        squad.name.contains(
                            "Apex",
                            ignoreCase = true
                        ) -> Color(0xFF71313F)

                        squad.name.contains(
                            "Valorant",
                            ignoreCase = true
                        ) -> Color(0xFF18795D)

                        else -> Color(0xFF225A94)
                    },
                    shape = RoundedCornerShape(10.dp)
                ),

            contentAlignment = Alignment.Center
        ) {

            Text(
                text = squad.name
                    .take(3)
                    .uppercase(),
                color = GuildnetText,
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        // Squad information
        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = squad.name,
                color = GuildnetText,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            Row {

                Text(
                    text = "${squad.memberCount} members",
                    color = GuildnetMutedText,
                    fontSize = 10.sp
                )

                Text(
                    text = " • ",
                    color = GuildnetMutedText,
                    fontSize = 10.sp
                )

                Text(
                    text = "${onlineCount(squad)} online",
                    color = GuildnetGreen,
                    fontSize = 10.sp
                )
            }
        }

        // Join button
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(9.dp))
                .background(GuildnetPurpleDark)
                .border(
                    width = 1.dp,
                    color = GuildnetPurple.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(9.dp)
                )
                .clickable {
                    onJoinClicked()
                }
                .padding(
                    horizontal = 14.dp,
                    vertical = 7.dp
                ),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = if (squad.isJoined) {
                    "Joined"
                } else {
                    "Join"
                },
                color = GuildnetPurple,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
            )
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(GuildnetSurface)
            .border(
                width = 1.dp,
                color = GuildnetBorder,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(13.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(GuildnetPurpleDark),

            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = Icons.Outlined.SportsEsports,
                contentDescription = null,
                tint = GuildnetPurple,
                modifier = Modifier.size(17.dp)
            )
        }

        Spacer(
            modifier = Modifier.width(11.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                color = GuildnetText,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            Text(
                text = "$author • $responses replies • $age",
                color = GuildnetMutedText,
                fontSize = 9.sp
            )
        }
    }
}


@Composable
private fun CommunityCard(
    guild: Guild
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(GuildnetSurface)
            .border(
                width = 1.dp,
                color = GuildnetBorder,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(14.dp)
    ) {

        Text(
            text = guild.name,
            color = GuildnetText,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.height(3.dp)
        )

        Text(
            text = "${guild.game} • ${guild.memberCount} members",
            color = GuildnetMutedText,
            fontSize = 10.sp
        )
    }
}



@Composable
private fun EmptyHomeMessage(
    message: String
) {

    Text(
        text = message,
        color = GuildnetMutedText,
        fontSize = 11.sp,
        modifier = Modifier.padding(
            vertical = 6.dp
        )
    )
}

private fun onlineCount(
    squad: Squad
): Int {

    return when {
        squad.memberCount >= 300 -> 54
        squad.memberCount >= 200 -> 32
        else -> 18
    }
}