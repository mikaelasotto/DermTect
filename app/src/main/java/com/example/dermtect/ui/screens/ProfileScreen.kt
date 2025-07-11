package com.example.dermtect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.BubblesBackground
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.dermtect.ui.components.DialogTemplate
import com.example.dermtect.ui.components.GifImage
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth


@Composable
fun ProfileScreen(navController: NavController) {
    var showPhoto by remember { mutableStateOf(false) }
    var showDeleteAccountDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser
    val email = user?.email
    var isLoading by remember { mutableStateOf(false) }

    DialogTemplate(
        show = isLoading,
        title = "Deleting Account",
        description = "Please wait ...",
        imageContent = {
            GifImage(resId = R.drawable.loader, size = 150)
        },
        onDismiss = {},
        autoDismiss = false
    )

    BubblesBackground {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
        ) {
            // Header Section
            Box(
                modifier = Modifier
                    .offset(y = 59.dp)
                    .fillMaxWidth()
                    .height(302.dp)
                    .background(Color(0xFFCDFFFF), shape = RoundedCornerShape(20.dp))
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                        .size(width = 37.dp, height = 35.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back"
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Settings",
                        fontSize = 24.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(width = 110.67.dp, height = 112.98.dp)
                            .clip(CircleShape)
                            .clickable { showPhoto = true },
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Mikaela Manalang",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // Card Section
            Card(
                modifier = Modifier
                    .offset(x = 25.dp, y = 344.dp)
                    .size(width = 363.dp, height = 388.dp)
                    .shadow(8.dp, RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(modifier = Modifier.padding(0.dp)) {
                    // Gmail Info
                    Box(
                        modifier = Modifier
                            .offset(x = 19.dp, y = 18.dp)
                            .size(width = 323.dp, height = 85.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFEDFFFF))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(width = 62.dp, height = 57.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFCDFFFF)),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.google_logo),
                                    contentDescription = "Google Icon",
                                    modifier = Modifier.size(28.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = "mikaelasotto1@gmail.com",
                                    fontSize = 14.sp,
                                    color = Color(0xFF484848),
                                    fontWeight = FontWeight.Normal
                                )
                                Text(
                                    text = "Google Account",
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    ChangePasswordRow(
                        icon = R.drawable.lock_icon,
                        label = "Change Password",
                        onClick = {navController.navigate("change_pass")
                        }
                    )

                    AssessmentRow(
                        icon = R.drawable.assessment_icon,
                        label = "My Assessment Report",
                        onClick = { }
                    )

                    DeleteAccountRow(
                        icon = R.drawable.bin_icon,
                        label = "Delete Account",
                        onClick = {
                            showDeleteAccountDialog = true
                        }
                    )



                    Spacer(modifier = Modifier.height(16.dp))
                    ProfileLogoutRow {
                        // Handle logout click
                    }
                }
            }

            if (showPhoto) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x80000000)) // semi-transparent backdrop
                ) {
                    Box(
                        modifier = Modifier
                            .offset(x = 60.dp, y = 300.dp)
                            .size(width = 296.dp, height = 295.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Full Photo",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Box(
                        modifier = Modifier
                            .offset(x = 56.dp, y = 296.dp)
                            .size(width = 37.dp, height = 35.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .clickable { showPhoto = false },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.x_icon), // replace with your actual 'X' icon
                            contentDescription = "Close",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }


            }
            if (showDeleteAccountDialog) {
                DeleteAccountDialog(
                    onDismiss = { showDeleteAccountDialog = false },
                    onDeleteConfirmed = { password ->
                        isLoading = true

                        val credential = EmailAuthProvider.getCredential(email!!, password)

                        user!!.reauthenticate(credential)
                            .addOnCompleteListener { authTask ->
                                if (authTask.isSuccessful) {
                                    user.delete()
                                        .addOnCompleteListener { deleteTask ->
                                            isLoading = false
                                            if (deleteTask.isSuccessful) {
                                                // ✅ Show toast and navigate safely using context from remember
                                                Toast.makeText(context, "Account deleted", Toast.LENGTH_SHORT).show()

                                                navController.navigate("login") {
                                                    popUpTo("profile") { inclusive = true }
                                                }
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    deleteTask.exception?.message ?: "Failed to delete account",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        }
                                } else {
                                    isLoading = false
                                    Toast.makeText(
                                        context,
                                        authTask.exception?.message ?: "Wrong password",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    }
                )
            }


        }
    }
}

@Composable
fun ChangePasswordRow(icon: Int, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 43.92.dp, height = 40.26.dp)
                .clip(CircleShape)
                .background(Color(0xFFEDFFFF)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = label,
            fontSize = 16.sp,
            color = Color(0xFF484848),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .size(width = 26.dp, height = 24.dp)
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "Navigate",
                tint = Color(0xFF0FB2B2),
                modifier = Modifier.size(width = 8.03.dp, height = 12.dp)
            )
        }
    }
}

@Composable
fun AssessmentRow(icon: Int, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 43.92.dp, height = 40.26.dp)
                .clip(CircleShape)
                .background(Color(0xFFEDFFFF)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = label,
            fontSize = 16.sp,
            color = Color(0xFF484848),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .size(width = 26.dp, height = 24.dp)
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "Navigate",
                tint = Color(0xFF0FB2B2),
                modifier = Modifier.size(width = 8.03.dp, height = 12.dp)
            )
        }
    }
}

@Composable
fun DeleteAccountRow(icon: Int, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 43.92.dp, height = 40.26.dp)
                .clip(CircleShape)
                .background(Color(0xFFEDFFFF)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = label,
            fontSize = 16.sp,
            color = Color(0xFF484848),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .size(width = 26.dp, height = 24.dp)
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "Navigate",
                tint = Color(0xFF0FB2B2),
                modifier = Modifier.size(width = 8.03.dp, height = 12.dp)
            )
        }
    }
}

@Composable
fun ProfileLogoutRow(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logout_icon),
            contentDescription = "Logout",
            tint = Color(0xFF00B2B2),
            modifier = Modifier.size(width = 18.66.dp, height = 13.09.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "Logout",
            color = Color(0xFF484848),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun DeleteAccountDialog(
    onDismiss: () -> Unit,
    onDeleteConfirmed: (String) -> Unit
) {
    var password by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Confirm Deletion",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Red
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Enter your current password to delete your account.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            onDeleteConfirmed(password)
                        },
                        enabled = password.isNotBlank()
                    ) {
                        Text("Delete", color = Color.White)
                    }
                }
            }
        }
    }
}

