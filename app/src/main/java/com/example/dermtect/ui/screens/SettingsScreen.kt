package com.example.dermtect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.BackButton
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.ui.components.DialogTemplate
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SettingsScreen(navController: NavController) {
    var showPhoto by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFEAFDFD))
    ) {
        // Top Profile Section
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
                    text = "Settings",
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

        // Settings Card
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
                // Account Box
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

                SettingsRow(
                    icon = R.drawable.user_vector_settings,
                    label = "Profile",
                    onClick = {
                        navController.navigate("profile")
                    }
                )

                SettingsRow(
                    icon = R.drawable.info,
                    label = "About/Credits",
                    onClick = {
                        navController.navigate("about")
                    }
                )

                NotificationRow(
                    icon = R.drawable.notifications_vector,
                    label = "Notification",
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it },
                    onClick = {
                        navController.navigate("notifications")
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                LogoutRow {
                    showLogoutDialog = true
                }
                if (showLogoutDialog) {
                    LogoutDialog(
                        show = showLogoutDialog,
                        onDismiss = { showLogoutDialog = false },
                        onLogoutConfirmed = {
                            showLogoutDialog = false
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate("login") {
                                popUpTo("settings") { inclusive = true }
                            }
                        }
                    )

                }


            }
        }

        // Full Image Preview
        if (showPhoto) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000)) // semi-transparent backdrop
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
    }
}


@Composable
fun LogoutDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onLogoutConfirmed: () -> Unit
) {
    DialogTemplate(
        show = show,
        title = "Logout",
        description = "Are you sure you want to logout?",
        primaryText = "Logout",
        onPrimary = onLogoutConfirmed,
        secondaryText = "Cancel",
        onSecondary = onDismiss,
        onDismiss = onDismiss
    )
}


@Composable
fun SettingsRow(icon: Int, label: String, onClick: () -> Unit) {
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
fun NotificationRow(
    icon: Int,
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit
) {
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

        Image(
            painter = painterResource(
                id = if (checked) R.drawable.toggle_on else R.drawable.toggle_off
            ),
            contentDescription = if (checked) "Enabled" else "Disabled",
            modifier = Modifier
                .size(width = 51.73.dp, height = 26.dp)
                .clickable { onCheckedChange(!checked) }
        )
    }
}

@Composable
fun LogoutRow(onClick: () -> Unit) {
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsPreview() {
    SettingsScreen(navController = rememberNavController())
}
