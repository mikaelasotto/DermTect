package com.example.dermtect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.collectAsState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.LaunchedEffect
import android.widget.Toast
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dermtect.ui.components.DialogTemplate
import com.example.dermtect.ui.components.GifImage

@Composable
fun Login(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isEmailValid = remember(email) { android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() }
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory())
    val authSuccess by viewModel.authSuccess.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("50445058822-fn9cea4e0bduos6t0g7ofb2g9ujri5s2.apps.googleusercontent.com")
        .requestEmail()
        .build()
    val googleSignInClient = GoogleSignIn.getClient(context, gso)
    val isLoading by viewModel.isLoading.collectAsState()

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account?.idToken
            if (idToken != null) {
                viewModel.signInWithGoogle(
                    idToken,
                    onSuccess = {
                        val uid = FirebaseAuth.getInstance().currentUser?.uid
                        if (uid != null) {
                            FirebaseFirestore.getInstance()
                                .collection("users")
                                .document(uid)
                                .get()
                                .addOnSuccessListener { document ->
                                    val role = document.getString("role")
                                    when (role) {
                                        "patient" -> navController.navigate("user_home")
                                        "derma" -> navController.navigate("derma_home")
                                        else -> navController.navigate("user_home")
                                    }
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Failed to fetch role", Toast.LENGTH_SHORT).show()
                                }
                        }
                    },
                    onError = {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        } catch (e: ApiException) {
            Toast.makeText(context, "Sign-in failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    BubblesBackground {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    focusManager.clearFocus()
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF1D1D1D)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Email input
                InputField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Email",
                    iconRes = R.drawable.icon_email,
                    textColor = Color(0xFF1D1D1D)
                )

                if (email.isNotEmpty() && !isEmailValid) {
                    Text(
                        text = "Invalid email address",
                        color = Color.Red,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(start = 48.dp, top = 4.dp)
                            .align(Alignment.Start)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Password input
                InputField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Password",
                    iconRes = R.drawable.icon_pass,
                    isPassword = true,
                    textColor = Color(0xFF1D1D1D)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Forgot Password
                Box(
                    modifier = Modifier
                        .width(299.dp)
                        .padding(top = 8.dp)
                        .clickable { navController.navigate("forgot_pass1") },
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = "Forgot Password?",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF1D1D1D),
                        textDecoration = TextDecoration.Underline
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Login Button
                Button(
                    onClick = {
                        viewModel.login(email, password)
                    },
                    modifier = Modifier
                        .width(299.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0FB2B2),
                        contentColor = Color.White
                    ),
                    enabled = isEmailValid && password.isNotBlank()
                ) {
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Other",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF828282)
                )

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
                    Text(
                        text = "Don't have an account? ",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF1D1D1D)
                    )
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = Color(0xFF2FD8D8),
                        modifier = Modifier.clickable {
                            navController.navigate("register")
                        }
                    )
                }
            }
            LaunchedEffect(authSuccess) {
                if (authSuccess) {
                    val uid = FirebaseAuth.getInstance().currentUser?.uid
                    if (uid != null) {
                        val db = FirebaseFirestore.getInstance()
                        db.collection("users").document(uid).get()
                            .addOnSuccessListener { document ->
                                val role = document.getString("role")
                                when (role) {
                                    "patient" -> navController.navigate("user_home")
                                    "derma" -> navController.navigate("derma_home")
                                    else -> navController.navigate("user_home")
                                }
                                viewModel.resetAuthSuccess()
                            }
                            .addOnFailureListener {
                                Toast.makeText(context, "Failed to fetch role", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    }
                }
            }
            LaunchedEffect(errorMessage) {
                errorMessage?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    viewModel.clearError()
                }
            }
            val navigateToHome by viewModel.navigateToHome.collectAsState()

            LaunchedEffect(navigateToHome) {
                if (navigateToHome) {
                    navController.navigate("user_home") {
                        popUpTo("login") { inclusive = true }
                    }
                    viewModel.markNavigationDone()
                }
            }

            DialogTemplate(
                show = isLoading,
                title = "Logging you in",
                description = "Please wait ...",
                imageContent = {
                    GifImage(resId = R.drawable.loader, size = 150)
                },
                onDismiss = {},
                autoDismiss = false
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
        Login(navController = rememberNavController())
}