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
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import com.example.dermtect.ui.components.BackButton
import com.example.dermtect.ui.components.ChangePasswordRow
import com.example.dermtect.ui.components.AssessmentRow
import com.example.dermtect.ui.components.DeleteAccountRow


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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFEAFDFD))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(color = Color(0xFFCDFFFF))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 23.dp)
            ) {
                BackButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 26.sp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape)
                        .clickable { showPhoto = true },
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Mikaela Manalang",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .offset(y = (-30).dp)
                .shadow(8.dp, RoundedCornerShape(15.dp)),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFDFD)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(85.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFEDFFFF))
                        .padding(start = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
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

                Spacer(modifier = Modifier.height(15.dp))

                ChangePasswordRow(
                    icon = R.drawable.lock_icon,
                    label = "Change Password",
                    onClick = { navController.navigate("change_pass") }
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

            }
        }

        if (showPhoto) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000))
            ) {
                Box(
                    modifier = Modifier
                        .offset(x = 60.dp, y = 300.dp)
                        .size(width = 286.dp, height = 285.dp),
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
                        painter = painterResource(id = R.drawable.x_icon),
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
