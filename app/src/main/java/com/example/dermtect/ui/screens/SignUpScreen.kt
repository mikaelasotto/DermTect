package com.example.dermtect.ui.screens

import android.util.Patterns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.BubblesBackground
import com.example.dermtect.ui.components.InputField
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dermtect.ui.viewmodel.AuthViewModel
import com.example.dermtect.ui.viewmodel.AuthViewModelFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Register(navController: NavController) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory())

    val loading by viewModel.loading.collectAsState()
    val authSuccess by viewModel.authSuccess.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.isNotBlank() && password == confirmPassword
    val isFormValid = isEmailValid && isPasswordValid

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("50445058822-ij64vec3lhdjasahe5qvmjfelm2ejlmj.apps.googleusercontent.com")
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener { authTask ->
                    if (authTask.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        val uid = user?.uid
                        if (uid != null) {
                            FirebaseFirestore.getInstance()
                                .collection("users")
                                .document(uid)
                                .set(mapOf("email" to user.email, "role" to "patient"))
                                .addOnSuccessListener {
                                    Toast.makeText(context, "Google sign-in successful!", Toast.LENGTH_SHORT).show()
                                    navController.navigate("user_home")
                                }
                        }
                    } else {
                        Toast.makeText(context, "Google sign-in failed", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: ApiException) {
            Toast.makeText(context, "Sign-in error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    BubblesBackground {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Sign Up", style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold), color = Color(0xFF1D1D1D))
            Spacer(modifier = Modifier.height(32.dp))

            InputField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email",
                iconRes = R.drawable.icon_email,
                textColor = Color.Black,
                isPassword = false,
                errorMessage = if (email.isNotBlank() && !isEmailValid) "Enter a valid email" else null
            )

            Spacer(modifier = Modifier.height(20.dp))

            InputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                iconRes = R.drawable.icon_pass,
                textColor = Color.Black,
                isPassword = true,
                errorMessage = if (password.isBlank()) "Password is required" else null
            )

            Spacer(modifier = Modifier.height(20.dp))

            InputField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Confirm Password",
                iconRes = R.drawable.icon_pass,
                textColor = Color.Black,
                isPassword = true,
                errorMessage = if (confirmPassword.isNotBlank() && password != confirmPassword) "Passwords do not match" else null
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.register(email, password) },
                enabled = isFormValid && !loading,
                modifier = Modifier
                    .width(299.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) Color(0xFF0FB2B2) else Color(0xFFB0B0B0),
                    contentColor = Color.White
                )
            ) {
                Text(if (loading) "Registering..." else "Register", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold))
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Other", style = MaterialTheme.typography.labelMedium, color = Color(0xFF828282))
            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = "Google Login",
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        val signInIntent = googleSignInClient.signInIntent
                        launcher.launch(signInIntent)
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text("Already have an account? ", style = MaterialTheme.typography.labelMedium, color = Color(0xFF1D1D1D))
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = Color(0xFF2FD8D8),
                    modifier = Modifier.clickable { navController.navigate("login") }
                )
            }

        }

        LaunchedEffect(authSuccess) {
            if (authSuccess) {
                navController.navigate("verify_email/${email}")
                viewModel.resetAuthSuccess()
            }
        }


        LaunchedEffect(errorMessage) {
            errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                viewModel.clearError()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    Register(navController = rememberNavController())
}
