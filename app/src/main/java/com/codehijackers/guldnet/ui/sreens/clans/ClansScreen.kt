package com.codehijackers.guldnet.ui.screens.clans

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.model.Clan
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.viewmodel.ClanViewModel

private val GuildnetSurface = Color(0xFF0F1727)
private val GuildnetBorder = Color(0xFF26344D)
private val GuildnetPurple = Color(0xFF9857FF)
private val GuildnetPurpleDark = Color(0xFF241545)
private val GuildnetText = Color(0xFFF1F3FA)
private val GuildnetMutedText = Color(0xFF8794AD)
private val GuildnetRed = Color(0xFFFF5065)

@Composable
fun ClansScreen(
    guildId: String,
    onClanClicked: (String) -> Unit = {},
    onCreateClanClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    clanViewModel: ClanViewModel = viewModel()
) {
    val strings = currentGuildnetStrings
    val clans by clanViewModel.clans.collectAsState()

    var searchQuery by remember {
        mutableStateOf("")
    }

    val guildClans = clans
        .filter {
            it.guildId == guildId
        }
        .filter {
            searchQuery.isBlank() ||
                    it.title.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||
                    it.content.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||
                    it.authorName.contains(
                        searchQuery,
                        ignoreCase = true
                    )
        }

    val categories = listOf(
        "⚔️" to strings.categoryStrategy,
        "🔧" to strings.categoryBuilds,
        "📋" to strings.categoryPatchNotes,
        "🎮" to strings.categoryHighlights
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 13.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 13.dp,
                        bottom = 13.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = strings.clansForum,
                    color = GuildnetText,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = strings.search,
                    tint = GuildnetMutedText,
                    modifier = Modifier.size(21.dp)
                )
            }

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
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = Color(0xFF52617D),
                    modifier = Modifier.size(17.dp)
                )

                Spacer(
                    modifier = Modifier.width(9.dp)
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
                        fontSize = 11.sp
                    ),
                    decorationBox = { innerTextField ->
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = strings.searchDiscussions,
                                color = GuildnetMutedText,
                                fontSize = 11.sp
                            )
                        }

                        innerTextField()
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        rememberScrollState()
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { (icon, title) ->
                    ClanCategoryCard(
                        icon = icon,
                        title = title,
                        count = when (title) {
                            strings.categoryStrategy -> "124"
                            strings.categoryBuilds -> "87"
                            strings.categoryPatchNotes -> "43"
                            else -> "62"
                        }
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(15.dp)
                        .clip(
                            RoundedCornerShape(2.dp)
                        )
                        .background(GuildnetPurple)
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = strings.popularDiscussions.uppercase(),
                    color = GuildnetText,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.9.sp
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            if (guildClans.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 45.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (searchQuery.isNotBlank()) {
                            strings.noDiscussionsFound
                        } else {
                            strings.noDiscussions
                        },
                        color = GuildnetText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = if (searchQuery.isNotBlank()) {
                            strings.tryDifferentSearch
                        } else {
                            strings.startDiscussionWithGuild
                        },
                        color = GuildnetMutedText,
                        fontSize = 11.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        bottom = 95.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(
                        items = guildClans,
                        key = {
                            "clan_${it.id}"
                        }
                    ) { clan ->
                        ClanForumCard(
                            clan = clan,
                            hotText = strings.hot,
                            categoryText = strings.categoryStrategy,
                            onClick = {
                                onClanClicked(clan.id)
                            }
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 17.dp,
                    bottom = 82.dp
                )
                .size(49.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(GuildnetPurple)
                .clickable {
                    onCreateClanClicked()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = strings.createDiscussion,
                tint = Color.White,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
private fun ClanCategoryCard(
    icon: String,
    title: String,
    count: String
) {
    Column(
        modifier = Modifier
            .width(74.dp)
            .height(86.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(GuildnetSurface)
            .border(
                width = 1.dp,
                color = GuildnetBorder,
                shape = RoundedCornerShape(13.dp)
            )
            .padding(
                horizontal = 6.dp,
                vertical = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = icon,
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = title,
            color = GuildnetText,
            fontSize = 9.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(
            modifier = Modifier.height(3.dp)
        )

        Text(
            text = count,
            color = GuildnetPurple,
            fontSize = 8.sp
        )
    }
}

@Composable
private fun ClanForumCard(
    clan: Clan,
    categoryText: String,
    hotText: String,
    onClick: () -> Unit
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
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 14.dp,
                vertical = 13.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            ClanTag(
                text = categoryText
            )

            if (clan.upvotes >= 20) {
                ClanTag(
                    text = "🔥 $hotText",
                    hot = true
                )
            }
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = clan.title,
            color = GuildnetText,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 17.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(19.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(GuildnetPurpleDark),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = clan.authorName
                        .take(1)
                        .uppercase(),
                    color = GuildnetPurple,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.width(7.dp)
            )

            Text(
                text = clan.authorName,
                color = GuildnetMutedText,
                fontSize = 9.sp,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "💬 ${clan.upvotes}",
                color = GuildnetMutedText,
                fontSize = 8.sp
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "♡ ${clan.upvotes}",
                color = GuildnetMutedText,
                fontSize = 8.sp
            )
        }
    }
}

@Composable
private fun ClanTag(
    text: String,
    hot: Boolean = false
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(
                if (hot) {
                    GuildnetRed.copy(alpha = 0.12f)
                } else {
                    GuildnetPurple.copy(alpha = 0.12f)
                }
            )
            .border(
                width = 1.dp,
                color = if (hot) {
                    GuildnetRed.copy(alpha = 0.3f)
                } else {
                    GuildnetPurple.copy(alpha = 0.3f)
                },
                shape = RoundedCornerShape(5.dp)
            )
            .padding(
                horizontal = 7.dp,
                vertical = 4.dp
            )
    ) {
        Text(
            text = text,
            color = if (hot) {
                GuildnetRed
            } else {
                GuildnetPurple
            },
            fontSize = 8.sp,
            fontWeight = FontWeight.Medium
        )
    }
}