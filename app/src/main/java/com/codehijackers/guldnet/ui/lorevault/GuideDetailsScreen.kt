package com.codehijackers.guldnet.ui.screens.lorevault

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.viewmodel.GuideViewModel

private val GuildnetSurface = Color(0xFF0F1727)
private val GuildnetBorder = Color(0xFF26344D)
private val GuildnetPurple = Color(0xFF9857FF)
private val GuildnetPurpleDark = Color(0xFF241545)
private val GuildnetText = Color(0xFFF1F3FA)
private val GuildnetMutedText = Color(0xFF8794AD)

@Composable
fun GuideDetailsScreen(
    guideId: String,
    onBackClicked: () -> Unit = {},
    guideViewModel: GuideViewModel = viewModel()
) {
    val guides by guideViewModel.guides.collectAsState()

    val guide = guides.find {
        it.id == guideId
    }

    if (guide == null) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(13.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .clickable {
                        onBackClicked()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = GuildnetText,
                    modifier = Modifier.padding(
                        end = 10.dp
                    )
                )

                Text(
                    text = "Back",
                    color = GuildnetMutedText,
                    fontSize = 11.sp
                )
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            Text(
                text = "Guide not found",
                color = GuildnetText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onBackClicked()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = GuildnetText,
                    modifier = Modifier.padding(
                        end = 10.dp
                    )
                )

                Text(
                    text = "LoreVault",
                    color = GuildnetText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Icon(
                imageVector = Icons.Outlined.BookmarkBorder,
                contentDescription = "Bookmark",
                tint = GuildnetPurple,
                modifier = Modifier.padding(
                    horizontal = 7.dp
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    horizontal = 13.dp
                )
        ) {

            Text(
                text = guide.category,
                color = GuildnetPurple,
                fontSize = 9.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        GuildnetPurple.copy(alpha = 0.12f)
                    )
                    .border(
                        width = 1.dp,
                        color = GuildnetPurple.copy(alpha = 0.25f),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    )
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = guide.title,
                color = GuildnetText,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "by ${guide.authorName} • ${guide.createdAt} • ${guide.viewCount} views",
                color = GuildnetMutedText,
                fontSize = 9.sp
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

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
                    .padding(15.dp)
            ) {

                Text(
                    text = guide.description,
                    color = GuildnetText,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            Text(
                text = guide.content,
                color = GuildnetText,
                fontSize = 13.sp,
                lineHeight = 21.sp
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            Text(
                text = "${guide.viewCount} views",
                color = GuildnetMutedText,
                fontSize = 9.sp
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )
        }
    }
}