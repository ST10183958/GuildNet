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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors
import com.codehijackers.guldnet.viewmodel.GuideViewModel

@Composable
fun GuideDetailsScreen(
    guideId: String,
    onBackClicked: () -> Unit = {},
    guideViewModel: GuideViewModel = viewModel()
) {
    val colors = currentGuildnetThemeColors
    val strings = currentGuildnetStrings
    val guides by guideViewModel.guides.collectAsState()

    val guide = guides.find {
        it.id == guideId
    }

    if (guide == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
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
                    contentDescription = strings.back,
                    tint = colors.textPrimary,
                    modifier = Modifier.padding(
                        end = 10.dp
                    )
                )

                Text(
                    text = strings.back,
                    color = colors.textSecondary,
                    fontSize = 11.sp
                )
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            Text(
                text = strings.guideNotFound,
                color = colors.textPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
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
                    contentDescription = strings.back,
                    tint = colors.textPrimary,
                    modifier = Modifier.padding(
                        end = 10.dp
                    )
                )

                Text(
                    text = strings.loreVaultLabel,
                    color = colors.textPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Icon(
                imageVector = Icons.Outlined.BookmarkBorder,
                contentDescription = strings.bookmark,
                tint = colors.primary,
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
                color = colors.primary,
                fontSize = 9.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        colors.primary.copy(alpha = 0.12f)
                    )
                    .border(
                        width = 1.dp,
                        color = colors.primary.copy(alpha = 0.25f),
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
                color = colors.textPrimary,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "${strings.by} ${guide.authorName} • ${guide.createdAt} • ${guide.viewCount} ${strings.views}",
                color = colors.textSecondary,
                fontSize = 9.sp
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp))
                    .background(colors.surface)
                    .border(
                        width = 1.dp,
                        color = colors.border,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(15.dp)
            ) {
                Text(
                    text = guide.description,
                    color = colors.textPrimary,
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
                color = colors.textPrimary,
                fontSize = 13.sp,
                lineHeight = 21.sp
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            Text(
                text = "${guide.viewCount} ${strings.views}",
                color = colors.textSecondary,
                fontSize = 9.sp
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )
        }
    }
}