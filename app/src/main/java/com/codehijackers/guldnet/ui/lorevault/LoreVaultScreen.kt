package com.codehijackers.guldnet.ui.screens.lorevault

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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
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
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.viewmodel.GuideViewModel

private val GuildnetSurface = Color(0xFF0F1727)
private val GuildnetBorder = Color(0xFF26344D)
private val GuildnetPurple = Color(0xFF9857FF)
private val GuildnetPurpleDark = Color(0xFF241545)
private val GuildnetText = Color(0xFFF1F3FA)
private val GuildnetMutedText = Color(0xFF8794AD)

@Composable
fun LoreVaultScreen(
    guildId: String,
    onGuideClicked: (String) -> Unit = {},
    onCreateGuideClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    guideViewModel: GuideViewModel = viewModel()
) {
    val strings = currentGuildnetStrings
    val guides by guideViewModel.guides.collectAsState()

    var searchQuery by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf(strings.all)
    }

    val guildGuides = guides.filter {
        it.guildId == guildId
    }

    val categories = listOf(strings.all) +
            guildGuides
                .map { it.category }
                .distinct()

    val filteredGuides = guildGuides
        .filter {
            selectedCategory == strings.all ||
                    it.category == selectedCategory
        }
        .filter {
            searchQuery.isBlank() ||
                    it.title.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||
                    it.description.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||
                    it.category.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||
                    it.authorName.contains(
                        searchQuery,
                        ignoreCase = true
                    )
        }

    val featuredGuides = filteredGuides
        .sortedByDescending {
            it.viewCount
        }
        .take(2)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(horizontal = 13.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    bottom = 14.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = strings.lore,
                    color = GuildnetText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = strings.vault,
                    color = GuildnetPurple,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .clickable {
                        onCreateGuideClicked()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = strings.createGuide,
                    tint = GuildnetPurple,
                    modifier = Modifier.size(21.dp)
                )
            }
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
                contentDescription = strings.search,
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
                            text = strings.searchGuidesLore,
                            color = GuildnetMutedText,
                            fontSize = 11.sp
                        )
                    }

                    innerTextField()
                }
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(
                    rememberScrollState()
                ),
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            categories.forEach { category ->
                LoreCategoryChip(
                    text = category,
                    selected = selectedCategory == category,
                    onClick = {
                        selectedCategory = category
                    }
                )
            }
        }

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        if (filteredGuides.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = strings.noGuidesFound,
                    color = GuildnetText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = strings.tryAnotherSearchCategory,
                    color = GuildnetMutedText,
                    fontSize = 11.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    bottom = 90.dp
                ),
                verticalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                item {
                    LoreSectionHeader(
                        title = strings.featuredGuides
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        featuredGuides.forEach { guide ->
                            FeaturedGuideCard(
                                guide = guide,
                                featuredText = strings.featured,
                                byText = strings.byAuthor,
                                minReadText = strings.minRead,
                                onClick = {
                                    onGuideClicked(guide.id)
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }

                        if (featuredGuides.size == 1) {
                            Spacer(
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                item {
                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )
                }

                item {
                    LoreSectionHeader(
                        title = strings.allGuides
                    )
                }

                items(
                    items = filteredGuides,
                    key = {
                        "guide_${it.id}"
                    }
                ) { guide ->
                    GuideListCard(
                        guide = guide,
                        byText = strings.byAuthor,
                        viewsText = strings.views,
                        minReadText = strings.minRead,
                        onClick = {
                            onGuideClicked(guide.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun LoreCategoryChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(33.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(
                if (selected) {
                    GuildnetPurple
                } else {
                    GuildnetSurface
                }
            )
            .border(
                width = if (selected) 0.dp else 1.dp,
                color = GuildnetBorder,
                shape = RoundedCornerShape(18.dp)
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 14.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected) {
                Color.White
            } else {
                GuildnetMutedText
            },
            fontSize = 10.sp,
            fontWeight = if (selected) {
                FontWeight.SemiBold
            } else {
                FontWeight.Normal
            }
        )
    }
}

@Composable
private fun LoreSectionHeader(
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(15.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(GuildnetPurple)
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        Text(
            text = title.uppercase(),
            color = GuildnetText,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.9.sp
        )
    }
}

@Composable
private fun FeaturedGuideCard(
    guide: com.codehijackers.guldnet.model.Guide,
    featuredText: String,
    byText: String,
    minReadText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(126.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(GuildnetPurpleDark.copy(alpha = 0.55f))
            .border(
                width = 1.dp,
                color = GuildnetPurple.copy(alpha = 0.5f),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {
                onClick()
            }
            .padding(13.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(
                    GuildnetPurple.copy(alpha = 0.13f)
                )
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                )
        ) {
            Text(
                text = featuredText,
                color = GuildnetPurple,
                fontSize = 8.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = guide.title,
            color = GuildnetText,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$byText ${guide.authorName}",
                color = GuildnetMutedText,
                fontSize = 8.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "${readTime(guide.content)} $minReadText",
                color = GuildnetMutedText,
                fontSize = 8.sp
            )
        }
    }
}

@Composable
private fun GuideListCard(
    guide: com.codehijackers.guldnet.model.Guide,
    byText: String,
    viewsText: String,
    minReadText: String,
    onClick: () -> Unit
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
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 13.dp,
                vertical = 13.dp
            ),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        GuildnetPurple.copy(alpha = 0.12f)
                    )
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    )
            ) {
                Text(
                    text = guide.category,
                    color = GuildnetPurple,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = guide.title,
                color = GuildnetText,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "$byText ${guide.authorName}  •  ${guide.viewCount} $viewsText  •  ${readTime(guide.content)} $minReadText",
                color = GuildnetMutedText,
                fontSize = 8.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Icon(
            imageVector = Icons.Default.Bookmark,
            contentDescription = null,
            tint = GuildnetPurple,
            modifier = Modifier
                .padding(
                    top = 2.dp,
                    start = 8.dp
                )
                .size(17.dp)
        )
    }
}

private fun readTime(
    content: String
): Int {
    val words = content
        .trim()
        .split(Regex("\\s+"))
        .count()

    return maxOf(
        1,
        words / 200
    )
}