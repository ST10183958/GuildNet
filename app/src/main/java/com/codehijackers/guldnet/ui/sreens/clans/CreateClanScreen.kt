package com.codehijackers.guldnet.ui.screens.clans

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
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
fun CreateClanScreen(
    guildId: String,
    onClanCreated: (
        title: String,
        content: String
    ) -> Unit = { _, _ -> },
    onBackClicked: () -> Unit = {}
) {
    val colors = currentGuildnetThemeColors
    val strings = currentGuildnetStrings

    var title by remember {
        mutableStateOf("")
    }

    var content by remember {
        mutableStateOf("")
    }

    val isValid =
        title.isNotBlank() &&
                content.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
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
                    .width(38.dp)
                    .height(38.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        onBackClicked()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = strings.back,
                    tint = colors.textPrimary
                )
            }

            Spacer(
                modifier = Modifier.width(7.dp)
            )

            Text(
                text = strings.createDiscussion,
                color = colors.textPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        ClanEditorField(
            label = strings.discussionTitle,
            placeholder = strings.discussionTitlePlaceholder,
            value = title,
            onValueChange = {
                title = it
            },
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(17.dp)
        )

        Text(
            text = strings.category,
            color = colors.textSecondary,
            fontSize = 9.sp,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(
                start = 2.dp,
                bottom = 7.dp
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(colors.surface)
                .border(
                    width = 1.dp,
                    color = colors.border,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 13.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = strings.strategy,
                color = colors.textPrimary,
                fontSize = 11.sp
            )
        }

        Spacer(
            modifier = Modifier.height(17.dp)
        )

        ClanEditorField(
            label = strings.discussionContent,
            placeholder = strings.discussionContentPlaceholder,
            value = content,
            onValueChange = {
                content = it
            },
            singleLine = false,
            minHeight = 150.dp
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Row(
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
                    onClanCreated(
                        title.trim(),
                        content.trim()
                    )
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = strings.createDiscussion,
                color = if (isValid) {
                    Color.White
                } else {
                    colors.textSecondary
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(9.dp)
        )

        Row(
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = strings.cancel,
                color = colors.textSecondary,
                fontSize = 11.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )
    }
}

@Composable
private fun ClanEditorField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean,
    minHeight: Dp = 46.dp
) {
    val colors = currentGuildnetThemeColors

    Column {
        Text(
            text = label,
            color = colors.textSecondary,
            fontSize = 9.sp,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(
                start = 2.dp,
                bottom = 7.dp
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
                    horizontal = 13.dp,
                    vertical = 11.dp
                ),
            verticalAlignment = if (singleLine) {
                Alignment.CenterVertically
            } else {
                Alignment.Top
            }
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = singleLine,
                maxLines = if (singleLine) 1 else 8,
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