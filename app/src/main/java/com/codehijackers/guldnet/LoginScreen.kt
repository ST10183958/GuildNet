package com.codehijackers.guldnet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehijackers.guldnet.data.local.GuildnetDatabaseProvider
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginClick: (Long) -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    onDiscordClick: () -> Unit = {},
    onGuestClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val darkBg = Color(0xFF0B0E14)
    val cardBg = Color(0xFF131822)
    val accentPurple = Color(0xFF9C27B0)
    val textBodyColor = Color(0xFF94A3B8)
    val dividerColor = Color(0xFF1E293B)

    val googleResId =
        context.resources.getIdentifier(
            "ic_google",
            "drawable",
            context.packageName
        )

    val discordResId =
        context.resources.getIdentifier(
            "ic_discord",
            "drawable",
            context.packageName
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBg),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight()
                .border(
                    1.dp,
                    dividerColor,
                    RoundedCornerShape(24.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = cardBg
            ),
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Start Your Journey Today,",
                    color = textBodyColor,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(color = Color.White)
                        ) {
                            append("GUILD")
                        }

                        withStyle(
                            style = SpanStyle(color = accentPurple)
                        ) {
                            append("NET ★")
                        }
                    },
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "— CREATE YOUR ACCOUNT AND START YOUR ADVENTURE —",
                    color = textBodyColor,
                    fontSize = 9.sp,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "EMAIL",
                        color = textBodyColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            errorMessage = ""
                        },
                        placeholder = {
                            Text(
                                "Enter your email",
                                color = textBodyColor.copy(alpha = 0.5f)
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = accentPurple,
                            unfocusedBorderColor = dividerColor,
                            focusedContainerColor = darkBg,
                            unfocusedContainerColor = darkBg,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "PASSWORD",
                        color = textBodyColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            errorMessage = ""
                        },
                        placeholder = {
                            Text(
                                "Enter your password",
                                color = textBodyColor.copy(alpha = 0.5f)
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = accentPurple,
                            unfocusedBorderColor = dividerColor,
                            focusedContainerColor = darkBg,
                            unfocusedContainerColor = darkBg,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = "Forgot Password?",
                        color = accentPurple,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable {
                                onForgotPasswordClick()
                            }
                    )
                }

                if (errorMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = errorMessage,
                        color = Color(0xFFFF6B6B),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (email.isBlank()) {
                            errorMessage = "Please enter your email."
                            return@Button
                        }

                        if (!email.contains("@")) {
                            errorMessage = "Please enter a valid email address."
                            return@Button
                        }

                        if (password.isBlank()) {
                            errorMessage = "Please enter your password."
                            return@Button
                        }

                        scope.launch {
                            val database =
                                GuildnetDatabaseProvider.getDatabase()

                            val user =
                                database.userDao()
                                    .getUserByEmail(email.trim())

                            if (user == null) {
                                errorMessage =
                                    "No account was found with this email."
                                return@launch
                            }

                            database.userDao().logoutAllUsers()
                            database.userDao().loginUser(user.id)

                            onLoginClick(user.id)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = accentPurple
                    )
                ) {
                    Text(
                        "LOG IN",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = dividerColor
                    )

                    Text(
                        text = " OR ",
                        color = textBodyColor,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = dividerColor
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedButton(
                    onClick = onSignUpClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = SolidColor(accentPurple)
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(
                        "SIGN UP",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "OR CONTINUE WITH",
                    color = textBodyColor,
                    fontSize = 10.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onGoogleClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(42.dp),
                        shape = RoundedCornerShape(10.dp),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = SolidColor(dividerColor)
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = darkBg
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (googleResId != 0) {
                                Image(
                                    painter = painterResource(
                                        id = googleResId
                                    ),
                                    contentDescription = "Google",
                                    modifier = Modifier.size(18.dp)
                                )

                                Spacer(
                                    modifier = Modifier.width(8.dp)
                                )
                            }

                            Text(
                                "Google",
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                    }

                    OutlinedButton(
                        onClick = onDiscordClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(42.dp),
                        shape = RoundedCornerShape(10.dp),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = SolidColor(dividerColor)
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = darkBg
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (discordResId != 0) {
                                Image(
                                    painter = painterResource(
                                        id = discordResId
                                    ),
                                    contentDescription = "Discord",
                                    modifier = Modifier.size(18.dp)
                                )

                                Spacer(
                                    modifier = Modifier.width(8.dp)
                                )
                            }

                            Text(
                                "Discord",
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Continue as Guest",
                    color = accentPurple,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable {
                        onGuestClick()
                    }
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(color = textBodyColor)
                        ) {
                            append("By continuing, you agree to our ")
                        }

                        withStyle(
                            style = SpanStyle(color = accentPurple)
                        ) {
                            append("Terms of Service")
                        }

                        withStyle(
                            style = SpanStyle(color = textBodyColor)
                        ) {
                            append(" and ")
                        }

                        withStyle(
                            style = SpanStyle(color = accentPurple)
                        ) {
                            append("Privacy Policy")
                        }
                    },
                    fontSize = 10.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
}