package com.example.dermtect.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.ui.components.BubblesBackground
import com.google.firebase.auth.FirebaseAuth

@Composable
fun VerifyEmailScreen(navController: NavController, email: String?) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var loading by remember { mutableStateOf(false) }

    BubblesBackground { Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Verify Your Email",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = buildAnnotatedString {
                append("A verification link has been sent to:\n")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(email ?: "your email")
                }
                append("\nPlease verify before continuing.")
            },
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal,
                lineHeight = 30.sp),
            textAlign = TextAlign.Center,
         )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                loading = true
                user?.reload()?.addOnCompleteListener { task ->
                    loading = false
                    if (task.isSuccessful && user.isEmailVerified) {
                        Toast.makeText(context, "Email verified!", Toast.LENGTH_SHORT).show()
                        navController.navigate("login") {
                            popUpTo("verify_email") { inclusive = true }
                        }
                    } else {
                        Toast.makeText(context, "Email not verified yet.", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0FB2B2)),
            enabled = !loading
        ) {
            Text(if (loading) "Checking..." else "I’ve Verified My Email", style = MaterialTheme.typography.bodyMedium)
        }


        TextButton(onClick = {
            user?.sendEmailVerification()
            Toast.makeText(context, "Verification email re-sent!", Toast.LENGTH_SHORT).show()
        }) {
            Text("Resend Email", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Didn’t get the email? \nTry resending after a minute or check your spam folder.",
            style = MaterialTheme.typography.bodySmall.copy(lineHeight =25.sp),
            textAlign = TextAlign.Center
        )
    }
    }
}


@Preview(showBackground = true)
@Composable
fun VerifyEmailScreenPreview() {
    VerifyEmailScreen(navController = rememberNavController(), email = "sample@email.com")
}