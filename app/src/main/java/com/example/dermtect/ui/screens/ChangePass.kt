package com.example.dermtect.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.*
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.background
import androidx.compose.material3.CircularProgressIndicator


@Composable
fun ChangePasswordScreen(navController: NavController) {
    val context = LocalContext.current
    var currentPassword by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val isPasswordValid = password.length >= 6
    val isMatch = password == confirmPass
    val canSubmit = currentPassword.isNotBlank() && password.isNotBlank() && confirmPass.isNotBlank() && isPasswordValid && isMatch && !isLoading

    val user = FirebaseAuth.getInstance().currentUser
    val email = user?.email

    DialogTemplate(
        show = isLoading,
        title = "Updating Password",
        description = "Please wait ...",
        imageContent = {
            GifImage(resId = R.drawable.loader, size = 150)
        },
        onDismiss = {},
        autoDismiss = false
    )

    BubblesBackground {
        BackButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.offset(x = 25.dp, y = 50.dp)
                .align(Alignment.TopStart)
        )

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x88000000)), // semi-transparent backdrop
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle("Change Password")
            Spacer(modifier = Modifier.height(8.dp))
            SubText("Set a new password for your account.")

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Current Password",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Color(0xFF1D1D1D),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, bottom = 8.dp)
            )
            InputField(
                value = currentPassword,
                onValueChange = { currentPassword = it },
                placeholder = "Current Password",
                iconRes = R.drawable.icon_pass,
                isPassword = true,
                errorMessage = null
            )

            Spacer(modifier = Modifier.height(20.dp))

            // New Password
            Text(
                text = "New Password",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Color(0xFF1D1D1D),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, bottom = 8.dp)
            )

            InputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                iconRes = R.drawable.icon_pass,
                isPassword = true,
                errorMessage = if (password.isNotBlank() && !isPasswordValid) "Password must be at least 6 characters" else null
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Confirm Password
            Text(
                text = "Confirm Password",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Color(0xFF1D1D1D),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, bottom = 8.dp)
            )

            InputField(
                value = confirmPass,
                onValueChange = { confirmPass = it },
                placeholder = "Confirm Password",
                iconRes = R.drawable.icon_pass,
                isPassword = true,
                errorMessage = if (confirmPass.isNotBlank() && !isMatch) "Passwords do not match" else null
            )

            Spacer(modifier = Modifier.height(32.dp))


            PrimaryButton(
                text = "Update Password",
                onClick = {
                    if (user != null && email != null) {
                        isLoading = true // show gif loader

                        val credential = EmailAuthProvider.getCredential(email, currentPassword)
                        user.reauthenticate(credential)
                            .addOnCompleteListener { authTask ->
                                if (authTask.isSuccessful) {
                                    user.updatePassword(password)
                                        .addOnCompleteListener { updateTask ->
                                            isLoading = false // hide gif loader
                                            if (updateTask.isSuccessful) {
                                                Toast.makeText(context, "Password updated successfully", Toast.LENGTH_SHORT).show()
                                                navController.popBackStack()
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    updateTask.exception?.message ?: "Failed to update password",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        }
                                } else {
                                    isLoading = false // hide gif loader on failed re-auth
                                    Toast.makeText(
                                        context,
                                        authTask.exception?.message ?: "Re-authentication failed",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    } else {
                        Toast.makeText(context, "User not logged in", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = canSubmit
            )

        }
    }
}
