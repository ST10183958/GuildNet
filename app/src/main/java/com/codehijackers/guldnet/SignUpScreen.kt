package com.codehijackers.guldnet

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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import com.codehijackers.guldnet.data.local.UserEntity
import com.codehijackers.guldnet.data.local.GuildnetDatabaseProvider
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit = {},
    onLoginLinkClick: () -> Unit = {},
    onBackClick: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    val hasMinLength = password.length >= 8
    val hasNumber = password.any { it.isDigit() }
    val hasSpecialChar = password.any { !it.isLetterOrDigit() }
    val passwordsMatch = password.isNotEmpty() && password == confirmPassword

    val darkBg = Color(0xFF0B0E14)
    val cardBg = Color(0xFF131822)
    val accentPurple = Color(0xFF9C27B0)
    val textBodyColor = Color(0xFF94A3B8)
    val dividerColor = Color(0xFF1E293B)
    val successGreen = Color(0xFF4CAF50)

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
                        "FULL NAME",
                        color = textBodyColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = fullName,
                        onValueChange = {
                            fullName = it
                            errorMessage = ""
                        },
                        placeholder = {
                            Text(
                                "Enter your full name",
                                color = textBodyColor.copy(alpha = 0.5f)
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Full name",
                                tint = accentPurple
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

                Spacer(modifier = Modifier.height(12.dp))

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
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                tint = accentPurple
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

                Spacer(modifier = Modifier.height(12.dp))

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
                                "Create a password",
                                color = textBodyColor.copy(alpha = 0.5f)
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Password",
                                tint = accentPurple
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

                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RequirementRow(
                        "At least 8 characters",
                        hasMinLength,
                        successGreen,
                        textBodyColor
                    )

                    RequirementRow(
                        "Include a number",
                        hasNumber,
                        successGreen,
                        textBodyColor
                    )

                    RequirementRow(
                        "Include a special character (e.g. ! @ #)",
                        hasSpecialChar,
                        successGreen,
                        textBodyColor
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "CONFIRM PASSWORD",
                        color = textBodyColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            errorMessage = ""
                        },
                        placeholder = {
                            Text(
                                "Confirm your password",
                                color = textBodyColor.copy(alpha = 0.5f)
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Password",
                                tint = accentPurple
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

                if (errorMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = errorMessage,
                        color = Color(0xFFFF6B6B),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        when {
                            fullName.isBlank() ->
                                errorMessage = "Please enter your full name."

                            email.isBlank() ->
                                errorMessage = "Please enter your email."

                            !email.contains("@") ->
                                errorMessage = "Please enter a valid email address."

                            !hasMinLength ->
                                errorMessage = "Password must contain at least 8 characters."

                            !hasNumber ->
                                errorMessage = "Password must contain a number."

                            !hasSpecialChar ->
                                errorMessage = "Password must contain a special character."

                            !passwordsMatch ->
                                errorMessage = "Passwords do not match."

                            else -> {
                                scope.launch {
                                    val database =
                                        GuildnetDatabaseProvider.getDatabase()

                                    val existingUser =
                                        database.userDao()
                                            .getUserByEmail(email.trim())

                                    if (existingUser != null) {
                                        errorMessage =
                                            "An account with this email already exists."
                                        return@launch
                                    }

                                    val username =
                                        fullName
                                            .trim()
                                            .lowercase()
                                            .replace(" ", "")

                                    val user = UserEntity(
                                        email = email.trim(),
                                        displayName = fullName.trim(),
                                        username = username,
                                        bio = "",
                                        isLoggedIn = false
                                    )

                                    database.userDao().insertUser(user)

                                    onSignUpClick()
                                }
                            }
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
                        "SIGN UP",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Already have an account? ",
                        color = textBodyColor,
                        fontSize = 12.sp
                    )

                    Text(
                        text = "Log In",
                        color = Color(0xFF8C9EFF),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            onLoginLinkClick()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RequirementRow(
    text: String,
    isMet: Boolean,
    successColor: Color,
    defaultColor: Color
) {
    val color =
        if (isMet) successColor
        else defaultColor

    val icon =
        if (isMet) "✓"
        else "○"

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Text(
            text = icon,
            color = color,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = text,
            color = color,
            fontSize = 11.sp
        )
    }
}