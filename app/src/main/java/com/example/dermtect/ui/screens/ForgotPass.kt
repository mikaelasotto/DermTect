package com.example.dermtect.ui.screens

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.InputField
import com.example.dermtect.ui.components.PrimaryButton
import com.example.dermtect.ui.components.BubblesBackground
import com.example.dermtect.ui.components.ScreenTitle
import com.example.dermtect.ui.components.SubText
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.dermtect.ui.components.ResendEmailText
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import com.example.dermtect.ui.components.BackButton
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import android.net.Uri




@Composable
fun ForgotPass1(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val isEmailValid = remember(email) {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    BubblesBackground {
        BackButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.offset(x = 25.dp, y = 50.dp)
                .align(Alignment.TopStart)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle("Forgot password")
            Spacer(modifier = Modifier.height(8.dp))
            SubText("Please enter your email to reset the password")
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Your Email",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Color(0xFF1D1D1D),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, bottom = 8.dp)
            )

            InputField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = if (it.isBlank()) {
                        "Email is required"
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(it).matches()) {
                        "Invalid email address"
                    } else {
                        ""
                    }
                },
                placeholder = "name@example.com",
                iconRes = R.drawable.icon_email,
                textColor = Color.Black,
                errorMessage = emailError.takeIf { it.isNotEmpty() }
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                text = if (isLoading) "Sending..." else "Reset Password",
                onClick = {
                    isLoading = true
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            isLoading = false
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Reset link sent to $email",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate("forgot_pass2?email=${Uri.encode(email)}")
                            } else {
                                Toast.makeText(
                                    context,
                                    task.exception?.message ?: "Something went wrong",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                },
                enabled = isEmailValid && email.isNotBlank() && !isLoading
            )
        }
    }
}


@Composable
fun ForgotPass2(navController: NavController, email: String) {
    var resendStatus by remember { mutableStateOf<String?>(null) }
    var isResendClickable by remember { mutableStateOf(true) }
    var triggerResend by remember { mutableStateOf(false) }

    BubblesBackground {
        BackButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .offset(x = 25.dp, y = 50.dp)
                .align(Alignment.TopStart)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle("Check your email")
            Spacer(modifier = Modifier.height(8.dp))

            SubText("We sent a password reset link to $email.\nPlease check your inbox.")

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                text = "Back to Login",
                onClick = {
                    navController.navigate("login") {
                        popUpTo("forgot_pass1") { inclusive = true }
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Resend option
            ResendEmailText(
                onResendClick = {
                    if (isResendClickable) {
                        resendStatus = "Reset email resent!"
                        isResendClickable = false
                        triggerResend = true

                        // TODO: Trigger Firebase resend email logic here
                    }
                }
            )

            if (triggerResend) {
                LaunchedEffect(Unit) {
                    delay(3000)
                    resendStatus = null
                    isResendClickable = true
                    triggerResend = false
                }
            }

            resendStatus?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it,
                    color = Color(0xFF0FB2B2),
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}


@Composable
fun ForgotPass3(navController: NavController) {
    BubblesBackground {
        BackButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .offset(x = 25.dp, y = 50.dp)
                .align(Alignment.TopStart)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle("Check your email")
            Spacer(modifier = Modifier.height(8.dp))

            SubText(
                text = "We’ve sent a password reset link to your email.\nPlease follow the instructions to reset your password."
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                text = "Back to Login",
                onClick = {
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
    }
}


@Composable
fun ForgotPass4(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }

    val isPasswordValid = password.length >= 6
    val isMatch = password == confirmPass
    val canSubmit = password.isNotBlank() && confirmPass.isNotBlank() && isPasswordValid && isMatch

    BubblesBackground {
        BackButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.offset(x = 25.dp, y = 50.dp)
                .align(Alignment.TopStart)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle("Set a new password")
            Spacer(modifier = Modifier.height(8.dp))
            SubText("Create a new password. Ensure it differs from previous ones for security.")
            Spacer(modifier = Modifier.height(24.dp))

            // Label
            Text(
                text = "Password",
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

            // Label
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
                    navController.navigate("login")
                },
                enabled = canSubmit
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ForgotPass1Preview() {
    ForgotPass1(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun ForgotPass2Preview() {
    ForgotPass2(navController = rememberNavController(), email = "example@email.com")
}

@Preview(showBackground = true)
@Composable
fun ForgotPass3Preview() {
        ForgotPass3(navController = rememberNavController())
}


@Preview(showBackground = true)
@Composable
fun ForgotPass4Preview() {
    // Safe preview with empty lambda
    ForgotPass4(navController = rememberNavController())
}