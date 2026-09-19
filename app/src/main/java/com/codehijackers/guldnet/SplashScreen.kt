package com.codehijackers.guldnet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit = {}
) {

    val context = LocalContext.current

    // Splash screen colours
    val darkBackground = Color(0xFF0B0E14)
    val accentPurple = Color(0xFF9C27B0)
    val textBody = Color(0xFF94A3B8)
    val dividerColor = Color(0xFF1E293B)

    LaunchedEffect(Unit) {
        delay(3.seconds)
        onSplashFinished()
    }

    // Safely find splash background
    val bgResId = context.resources.getIdentifier(
        "background_splash",
        "drawable",
        context.packageName
    )

    // Safely find logo
    val logoResId = context.resources.getIdentifier(
        "logo_icon",
        "drawable",
        context.packageName
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBackground),
        contentAlignment = Alignment.Center
    ) {

        // Background image
        if (bgResId != 0) {
            Image(
                painter = painterResource(id = bgResId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // GuildNet logo
            if (logoResId != 0) {
                Image(
                    painter = painterResource(id = logoResId),
                    contentDescription = "GuildNet Logo",
                    modifier = Modifier.size(110.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // GUILDNET title
            Text(
                text = buildAnnotatedString {

                    withStyle(
                        style = SpanStyle(
                            color = Color.White
                        )
                    ) {
                        append("GUILD")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = accentPurple
                        )
                    ) {
                        append("NET")
                    }
                },
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "CONNECT ★ CONQUER ★ COLLABORATE",
                color = textBody,
                fontSize = 12.sp
            )

            Spacer(
                modifier = Modifier.height(60.dp)
            )

            LinearProgressIndicator(
                modifier = Modifier
                    .width(200.dp)
                    .height(4.dp),
                color = accentPurple,
                trackColor = dividerColor
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "v2.4.1",
                color = textBody,
                fontSize = 10.sp
            )
        }
    }
}