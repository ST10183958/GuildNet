package com.codehijackers.guldnet.ui.screens.squads

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
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicTextField
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.model.Squad
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.viewmodel.SquadViewModel

private val GuildnetSurface = Color(0xFF0F1727)
private val GuildnetSurfaceLight = Color(0xFF121C2E)
private val GuildnetBorder = Color(0xFF26344D)

private val GuildnetPurple = Color(0xFF9857FF)
private val GuildnetPurpleDark = Color(0xFF241545)

private val GuildnetText = Color(0xFFF1F3FA)
private val GuildnetMutedText = Color(0xFF8794AD)
private val GuildnetGreen = Color(0xFF35D98A)

@Composable
fun SquadsScreen(
    onSquadClicked: (String) -> Unit = {},
    onCreateSquadClicked: () -> Unit = {},
    squadViewModel: SquadViewModel = viewModel()
) {
    val squads by squadViewModel.squads.collectAsState()
    val strings = currentGuildnetStrings

    var searchQuery by remember {
        mutableStateOf("")
    }

    var selectedTab by remember {
        mutableStateOf(strings.mySquads)
    }

    val filteredSquads = squads
        .filter { squad ->
            when (selectedTab) {
                strings.mySquads -> squad.isJoined
                else -> true
            }
        }
        .filter { squad ->
            searchQuery.isBlank() ||
                    squad.name.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||
                    squad.description.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||
                    squad.ownerName.contains(
                        searchQuery,
                        ignoreCase = true
                    )
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(horizontal = 8.dp)
    ) {

        Text(
            text = strings.squads,
            color = GuildnetText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                top = 14.dp,
                bottom = 16.dp
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(41.dp)
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
                contentDescription = strings.search,
                tint = Color(0xFF52617D),
                modifier = Modifier.size(17.dp)
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            BasicTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(
                    color = GuildnetText,
                    fontSize = 12.sp
                ),
                decorationBox = { innerTextField ->

                    if (searchQuery.isEmpty()) {
                        Text(
                            text = strings.searchSquads,
                            color = GuildnetMutedText,
                            fontSize = 12.sp
                        )
                    }

                    innerTextField()
                }
            )
        }

        Spacer(
            modifier = Modifier.height(17.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    GuildnetSurface.copy(alpha = 0.75f)
                )
                .padding(3.dp)
        ) {

            SquadTabButton(
                text = strings.mySquads,
                selected = selectedTab == strings.mySquads,
                onClick = {
                    selectedTab = strings.mySquads
                },
                modifier = Modifier.weight(1f)
            )

            SquadTabButton(
                text = strings.popular,
                selected = selectedTab == strings.popular,
                onClick = {
                    selectedTab = strings.popular
                },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(
            modifier = Modifier.height(17.dp)
        )

        if (filteredSquads.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = when {
                        searchQuery.isNotBlank() ->
                            strings.noSquadsFound

                        selectedTab == strings.mySquads ->
                            strings.noSquadsJoined

                        else ->
                            strings.noSquadsAvailable
                    },
                    color = GuildnetMutedText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )

                if (
                    selectedTab == strings.mySquads &&
                    searchQuery.isBlank()
                ) {

                    Spacer(
                        modifier = Modifier.height(7.dp)
                    )

                    Text(
                        text = strings.explorePopularSquads,
                        color = GuildnetMutedText,
                        fontSize = 11.sp
                    )
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    bottom = 90.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(
                    items = filteredSquads,
                    key = { it.id }
                ) { squad ->

                    GuildnetSquadCard(
                        squad = squad,

                        onSquadClicked = {
                            onSquadClicked(squad.id)
                        },

                        onChatClicked = {
                            onSquadClicked(squad.id)
                        },

                        onJoinClicked = {

                            if (squad.isJoined) {
                                squadViewModel.leaveSquad(
                                    squad.id
                                )
                            } else {
                                squadViewModel.joinSquad(
                                    squad.id
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SquadTabButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(9.dp))
            .background(
                if (selected) {
                    GuildnetPurple
                } else {
                    Color.Transparent
                }
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected) {
                Color.White
            } else {
                GuildnetMutedText
            },
            fontSize = 11.sp,
            fontWeight = if (selected) {
                FontWeight.SemiBold
            } else {
                FontWeight.Normal
            }
        )
    }
}

@Composable
private fun GuildnetSquadCard(
    squad: Squad,
    onSquadClicked: () -> Unit,
    onChatClicked: () -> Unit,
    onJoinClicked: () -> Unit
) {
    val strings = currentGuildnetStrings

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(GuildnetSurface)
            .border(
                width = 1.dp,
                color = GuildnetBorder,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                horizontal = 15.dp,
                vertical = 15.dp
            )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            SquadGameIcon(
                squad = squad
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = squad.name,
                    color = GuildnetText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = "${squad.memberCount} ${strings.members}",
                    color = GuildnetMutedText,
                    fontSize = 10.sp
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(GuildnetGreen)
                )

                Spacer(
                    modifier = Modifier.width(5.dp)
                )

                Text(
                    text = onlineCount(squad).toString(),
                    color = GuildnetGreen,
                    fontSize = 10.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(39.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(GuildnetPurpleDark)
                    .border(
                        width = 1.dp,
                        color = GuildnetPurple.copy(
                            alpha = 0.55f
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        onChatClicked()
                    },
                contentAlignment = Alignment.Center
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Outlined.ChatBubbleOutline,
                        contentDescription = strings.chat,
                        tint = GuildnetPurple,
                        modifier = Modifier.size(15.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(6.dp)
                    )

                    Text(
                        text = strings.chat,
                        color = GuildnetPurple,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(39.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        width = 1.dp,
                        color = GuildnetBorder,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        onSquadClicked()
                    },
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = strings.view,
                    color = GuildnetMutedText,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun SquadGameIcon(
    squad: Squad
) {
    val name = squad.name.lowercase()

    val backgroundColor: Color
    val borderColor: Color

    when {

        "apex" in name -> {
            backgroundColor = Color(0xFF28171E)
            borderColor = Color(0xFF71313F)
        }

        "valorant" in name -> {
            backgroundColor = Color(0xFF102D28)
            borderColor = Color(0xFF18795D)
        }

        "cs2" in name ||
                "counter" in name -> {
            backgroundColor = Color(0xFF132A48)
            borderColor = Color(0xFF225A94)
        }

        else -> {
            backgroundColor = Color(0xFF172238)
            borderColor = GuildnetBorder
        }
    }

    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(RoundedCornerShape(11.dp))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(11.dp)
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