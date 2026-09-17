package com.codehijackers.guildnet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BiometricScreen(
    onBackClick: () -> Unit = {},
    onUsePasswordClick: () -> Unit = {}, // Kept in signature so MainActivity doesn't crash!
    onCancelClick: () -> Unit = {}
) {
    val context = LocalContext.current

    val darkBg = Color(0xFF0B0E14)
    val textBodyColor = Color(0xFF94A3B8)

    // We fetch your specific fingerprint icon dynamically so it won't crash if the file is missing yet
    val fingerprintResId = context.resources.getIdentifier("ic_fingerprint", "drawable", context.packageName)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBg)
    ) {
        // Back Button
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_revert), // Fallback back arrow
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier
                .padding(24.dp)
                .size(28.dp)
                .clickable { onBackClick() }
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Biometric Auth",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Authenticate to continue",
                color = textBodyColor,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Concentric Circles with Fingerprint Icon
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(200.dp)
            ) {
                // Outer circle
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .background(Color(0xFF1E1C2E), shape = CircleShape)
                )
                // Middle circle
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .background(Color(0xFF332047), shape = CircleShape)
                )
                // Inner circle
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFF6A1B9A), shape = CircleShape)
                )

                // Fingerprint Icon (will only show if you've added ic_fingerprint to your drawables)
                if (fingerprintResId != 0) {
                    Image(
                        painter = painterResource(id = fingerprintResId),
                        contentDescription = "Fingerprint Sensor",
                        modifier = Modifier.size(45.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Touch the sensor to authenticate",
                color = Color.White,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Cancel Button only
            Text(
                text = "Cancel",
                color = textBodyColor,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onCancelClick() }
            )
        }
    }
}