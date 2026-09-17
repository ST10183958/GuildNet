package com.codehijackers.guildnet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds
import com.codehijackers.guildnet.theme.*

@Composable
fun SplashScreen(onSplashFinished: () -> Unit = {}) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        delay(3.seconds)
        onSplashFinished()
    }

    // Safely resolve drawables by name to bypass R class compilation glitches
    val bgResId = context.resources.getIdentifier("background_splash", "drawable", context.packageName)
    val logoResId = context.resources.getIdentifier("logo_icon", "drawable", context.packageName)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentAlignment = Alignment.Center
    ) {
        // Starry Background Image
        if (bgResId != 0) {
            Image(
                painter = painterResource(id = bgResId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // GuildNet Logo Image
            if (logoResId != 0) {
                Image(
                    painter = painterResource(id = logoResId),
                    contentDescription = "GuildNet Logo",
                    modifier = Modifier.size(110.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // GUILD in white, NET in AccentPurple
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("GUILD")
                    }
                    withStyle(style = SpanStyle(color = AccentPurple)) {
                        append("NET")
                    }
                },
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "CONNECT ★ CONQUER ★ COLLABORATE",
                color = TextBody,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Loading Bar
            LinearProgressIndicator(
                modifier = Modifier.width(200.dp).height(4.dp),
                color = AccentPurple,
                trackColor = DividerColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "v2.4.1",
                color = TextBody,
                fontSize = 10.sp
            )
        }
    }
}