package com.codehijackers.guldnet.ui.screens.lorevault

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors

@Composable
fun CreateGuideScreen(
    guildId: String,
    onGuideCreated: (
        title: String,
        description: String,
        content: String,
        category: String
    ) -> Unit = { _, _, _, _ -> },
    onBackClicked: () -> Unit = {}
) {
    val colors = currentGuildnetThemeColors
    val strings = currentGuildnetStrings

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var content by remember {
        mutableStateOf("")
    }

    var category by remember {
        mutableStateOf("")
    }

    val isValid =
        title.isNotBlank() &&
                description.isNotBlank() &&
                content.isNotBlank() &&
                category.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(
                horizontal = 13.dp,
                vertical = 14.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        onBackClicked()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = strings.back,
                    tint = colors.textPrimary,
                    modifier = Modifier.size(21.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(7.dp)
            )

            Column {
                Text(
                    text = strings.createGuide,
                    color = colors.textPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = strings.shareGamingKnowledge,
                    color = colors.textSecondary,
                    fontSize = 9.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(22.dp)
        )

        GuideEditorField(
            label = strings.guideTitle,
            placeholder = strings.guideTitlePlaceholder,
            value = title,
            onValueChange = {
                title = it
            },
            icon = Icons.Outlined.Article,
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(13.dp)
        )

        GuideEditorField(
            label = strings.category,
            placeholder = strings.guideCategoryPlaceholder,
            value = category,
            onValueChange = {
                category = it
            },
            icon = Icons.Outlined.Category,
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(13.dp)
        )

        GuideEditorField(
            label = strings.description,
            placeholder = strings.guideDescriptionPlaceholder,
            value = description,
            onValueChange = {
                description = it
            },
            icon = Icons.Outlined.Description,
            singleLine = false,
            minHeight = 80.dp
        )

        Spacer(
            modifier = Modifier.height(13.dp)
        )

        GuideEditorField(
            label = strings.guideContent,
            placeholder = strings.guideContentPlaceholder,
            value = content,
            onValueChange = {
                content = it
            },
            icon = Icons.Outlined.Article,
            singleLine = false,
            minHeight = 170.dp
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clip(RoundedCornerShape(11.dp))
                .background(
                    if (isValid) {
                        colors.primary
                    } else {
                        colors.surfaceVariant
                    }
                )
                .border(
                    width = 1.dp,
                    color = if (isValid) {
                        colors.primary
                    } else {
                        colors.border
                    },
                    shape = RoundedCornerShape(11.dp)
                )
                .clickable(
                    enabled = isValid
                ) {
                    onGuideCreated(
                        title.trim(),
                        description.trim(),
                        content.trim(),
                        category.trim()
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = strings.publishGuide,
                color = if (isValid) {
                    Color.White
                } else {
                    colors.textSecondary
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(
            modifier = Modifier.height(9.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(43.dp)
                .clip(RoundedCornerShape(11.dp))
                .border(
                    width = 1.dp,
                    color = colors.border,
                    shape = RoundedCornerShape(11.dp)
                )
                .clickable {
                    onBackClicked()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = strings.cancel,
                color = colors.textSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun GuideEditorField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    singleLine: Boolean,
    minHeight: Dp = 46.dp
) {
    val colors = currentGuildnetThemeColors

    Column {
        Text(
            text = label.uppercase(),
            color = colors.textSecondary,
            fontSize = 9.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.8.sp,
            modifier = Modifier.padding(
                start = 3.dp,
                bottom = 6.dp
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(minHeight)
                .clip(RoundedCornerShape(12.dp))
                .background(colors.surface)
                .border(
                    width = 1.dp,
                    color = colors.border,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 10.dp
                ),
            verticalAlignment = if (singleLine) {
                Alignment.CenterVertically
            } else {
                Alignment.Top
            }
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier
                    .size(17.dp)
                    .padding(
                        top = if (singleLine) {
                            0.dp
                        } else {
                            2.dp
                        }
                    )
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = singleLine,
                maxLines = if (singleLine) {
                    1
                } else {
                    8
                },
                textStyle = TextStyle(
                    color = colors.textPrimary,
                    fontSize = 12.sp
                ),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = colors.textSecondary,
                            fontSize = 11.sp
                        )
                    }

                    innerTextField()
                }
            )
        }
    }
}