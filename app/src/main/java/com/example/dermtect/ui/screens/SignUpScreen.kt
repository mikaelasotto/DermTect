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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.BubblesBackground
import com.example.dermtect.ui.components.InputField
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dermtect.ui.components.DialogTemplate
import com.example.dermtect.ui.viewmodel.AuthViewModel
import com.example.dermtect.ui.viewmodel.AuthViewModelFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.foundation.interaction.MutableInteractionSource

@Composable
fun Register(navController: NavController) {
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory())
    var fnameTouched by remember { mutableStateOf(false) }
    var lnameTouched by remember { mutableStateOf(false) }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    val loading by viewModel.loading.collectAsState()
    val authSuccess by viewModel.authSuccess.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }

    val isEmailValid = email.isNotBlank() &&
            Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            !email.contains(" ")

    val isPasswordValid = password.isNotBlank() &&
            password == confirmPassword &&
            !password.contains(" ")

    val isFormValid = firstName.isNotBlank() && lastName.isNotBlank() && isEmailValid && isPasswordValid

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("50445058822-fn9cea4e0bduos6t0g7ofb2g9ujri5s2.apps.googleusercontent.com")
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

                        viewModel.signInWithGoogle(
                            idToken = account.idToken ?: "",
                            onSuccess = {
                                navController.navigate("user_home")
                            },
                            onError = {
                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            }
                        )


                        if (uid != null) {
                            FirebaseFirestore.getInstance()
                                .collection("users")
                                .document(uid)
                                .set(
                                    mapOf(
                                        "createdAt" to com.google.firebase.firestore.FieldValue.serverTimestamp(),
                                        "email" to user.email,
                                        "firstName" to (account?.givenName ?: ""),
                                        "lastName" to (account?.familyName ?: ""),
                                        "role" to "patient"
                                    )
                                )
                                .addOnSuccessListener {
                                    Toast.makeText(context, "Google sign-in successful!", Toast.LENGTH_SHORT).show()
                                    navController.navigate("user_home")
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Failed to save user info", Toast.LENGTH_SHORT).show()
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
                Text(
                    "Sign Up",
                    style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF1D1D1D)
                )
                Spacer(modifier = Modifier.height(30.dp))

                InputField(
                    value = firstName,
                    onValueChange = {
                        firstName = it.replaceFirstChar { char ->
                            char.uppercaseChar().toString()
                        }

                        if (!fnameTouched) fnameTouched = true
                    },
                    placeholder = "First Name",
                    iconRes = R.drawable.user_vector,
                    textColor = Color.Black,
                    isPassword = false,
                    errorMessage = if (fnameTouched && firstName.isBlank()) "First name is required" else null
                )

                Spacer(modifier = Modifier.height(5.dp))

                InputField(
                    value = lastName,
                    onValueChange = {
                        lastName = it.replaceFirstChar { char ->
                            char.uppercaseChar().toString()
                        }
                        if (!lnameTouched) lnameTouched = true
                    },
                    placeholder = "Last Name",
                    iconRes = R.drawable.user_vector,
                    textColor = Color.Black,
                    isPassword = false,
                    errorMessage = if (lnameTouched && lastName.isBlank()) "Last name is required" else null
                )
                Spacer(modifier = Modifier.height(5.dp))

                InputField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Email",
                    iconRes = R.drawable.icon_email,
                    textColor = Color.Black,
                    isPassword = false,
                    errorMessage = when {
                        email.contains(" ") -> "Email must not contain spaces"
                        email.isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(email)
                            .matches() -> "Enter a valid email"

                        else -> null
                    }
                )


                Spacer(modifier = Modifier.height(5.dp))

                InputField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Password",
                    iconRes = R.drawable.icon_pass,
                    textColor = Color.Black,
                    isPassword = true,
                    errorMessage = if (password.contains(" ")) "Password must not contain spaces" else null
                )


                Spacer(modifier = Modifier.height(5.dp))

                InputField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    placeholder = "Confirm Password",
                    iconRes = R.drawable.icon_pass,
                    textColor = Color.Black,
                    isPassword = true,
                    errorMessage = if (confirmPassword.isNotBlank() && password != confirmPassword) "Passwords do not match" else null
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
                        viewModel.register(email, password, firstName, lastName)
                    },
                    enabled = isFormValid && !loading,
                    modifier = Modifier
                        .width(299.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isFormValid) Color(0xFF0FB2B2) else Color(0xFFB0B0B0),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        if (loading) "Registering..." else "Register",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                    )
                }


                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    "Other",
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
                        "Already have an account? ",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF1D1D1D)
                    )
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = Color(0xFF2FD8D8),
                        modifier = Modifier.clickable { navController.navigate("login") }
                    )
                }
            }

            // Show dialog when registration is successful
            if (showSuccessDialog) {
                DialogTemplate(
                    show = showSuccessDialog,
                    title = "Check Your Email!",
                    description = buildAnnotatedString {
                        append("A verification email has been sent to\n")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(email)
                        }
                        append("\nPlease verify before logging in.")
                    }.toString(),

                    imageResId = R.drawable.success, // Replace with your image
                    primaryText = "Go to Login",
                    onPrimary = {
                        showSuccessDialog = false
                        navController.navigate("login") {
                            popUpTo("register") { inclusive = true }
                        }
                    },
                    onDismiss = { showSuccessDialog = false }
                )
            }

            // Listen for registration success
            LaunchedEffect(authSuccess) {
                if (authSuccess) {
                    showSuccessDialog = true
                    viewModel.resetAuthSuccess()
                }
            }

            // Listen for error messages
            LaunchedEffect(errorMessage) {
                errorMessage?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    viewModel.clearError()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    Register(navController = rememberNavController())
}
