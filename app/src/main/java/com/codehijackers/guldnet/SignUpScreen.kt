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

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit = {},
    onLoginLinkClick: () -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Dynamic Password Checks
    val hasMinLength = password.length >= 8
    val hasNumber = password.any { it.isDigit() }
    val hasSpecialChar = password.any { !it.isLetterOrDigit() }

    // Safe fallback colors
    val darkBg = Color(0xFF0B0E14)
    val cardBg = Color(0xFF131822)
    val accentPurple = Color(0xFF9C27B0)
    val textBodyColor = Color(0xFF94A3B8)
    val dividerColor = Color(0xFF1E293B)
    val successGreen = Color(0xFF4CAF50) // Green for fulfilled requirements

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
                .border(1.dp, dividerColor, RoundedCornerShape(24.dp)),
            colors = CardDefaults.cardColors(containerColor = cardBg),
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title with Purple "NET"
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append("GUILD")
                        }
                        withStyle(style = SpanStyle(color = accentPurple)) {
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

                // Full Name Field
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("FULL NAME", color = textBodyColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        placeholder = { Text("Enter your full name", color = textBodyColor.copy(alpha = 0.5f)) },
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
                            focusedBorderColor = accentPurple, unfocusedBorderColor = dividerColor,
                            focusedContainerColor = darkBg, unfocusedContainerColor = darkBg,
                            focusedTextColor = Color.White, unfocusedTextColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Email Field
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("EMAIL", color = textBodyColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("Enter your email", color = textBodyColor.copy(alpha = 0.5f)) },
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
                            focusedBorderColor = accentPurple, unfocusedBorderColor = dividerColor,
                            focusedContainerColor = darkBg, unfocusedContainerColor = darkBg,
                            focusedTextColor = Color.White, unfocusedTextColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Password Field
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("PASSWORD", color = textBodyColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Create a password", color = textBodyColor.copy(alpha = 0.5f)) },
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
                            focusedBorderColor = accentPurple, unfocusedBorderColor = dividerColor,
                            focusedContainerColor = darkBg, unfocusedContainerColor = darkBg,
                            focusedTextColor = Color.White, unfocusedTextColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Dynamic Password Requirements Checklist
                Column(modifier = Modifier.fillMaxWidth()) {
                    RequirementRow("At least 8 characters", isMet = hasMinLength, successColor = successGreen, defaultColor = textBodyColor)
                    RequirementRow("Include a number", isMet = hasNumber, successColor = successGreen, defaultColor = textBodyColor)
                    RequirementRow("Include a special character (e.g. ! @ #)", isMet = hasSpecialChar, successColor = successGreen, defaultColor = textBodyColor)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Confirm Password Field
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("CONFIRM PASSWORD", color = textBodyColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        placeholder = { Text("Confirm your password", color = textBodyColor.copy(alpha = 0.5f)) },
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
                            focusedBorderColor = accentPurple, unfocusedBorderColor = dividerColor,
                            focusedContainerColor = darkBg, unfocusedContainerColor = darkBg,
                            focusedTextColor = Color.White, unfocusedTextColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Sign Up Button
                Button(
                    onClick = onSignUpClick,
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = accentPurple)
                ) {
                    Text("SIGN UP", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Log In Link
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Already have an account? ", color = textBodyColor, fontSize = 12.sp)
                    Text(
                        text = "Log In",
                        color = Color(0xFF8C9EFF), // Lighter blue/purple for link
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { onLoginLinkClick() }
                    )
                }
            }
        }
    }
}

// Helper composable for the checkmark rows
@Composable
fun RequirementRow(text: String, isMet: Boolean, successColor: Color, defaultColor: Color) {
    val color = if (isMet) successColor else defaultColor
    val icon = if (isMet) "✓" else "○"

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Text(text = icon, color = color, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = text, color = color, fontSize = 11.sp)
    }
}