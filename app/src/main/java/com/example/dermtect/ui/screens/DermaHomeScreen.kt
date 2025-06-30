package com.example.dermtect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.TopRightNotificationIcon
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.navigation.compose.rememberNavController

@Composable
fun DermaHomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Notification Button
        TopRightNotificationIcon(
            onNotifClick = { navController.navigate("notifications") },
            modifier = Modifier
                .align(Alignment.End)
                .offset(x = -25.dp, y = 50.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.Start
        ) {

            // Header Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE6FAFB), RoundedCornerShape(10.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Hello,",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Doc Mika!",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Text(
                    text = "Early Detection Saves Lives.",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Summary Cards Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    title = "Pending Cases",
                    value = "25",
                    iconRes = R.drawable.pending_cases,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Total Cases",
                    value = "139",
                    iconRes = R.drawable.total_cases,
                    modifier = Modifier.weight(1f)
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            // Pending Cases Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pending Cases",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    painter = painterResource(id = R.drawable.expand),
                    contentDescription = "Expand",
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Case Items
            listOf(
                Triple(R.drawable.sample_skin_1, "Scan 1", "May 8, 2025"),
                Triple(R.drawable.sample_skin_1, "Scan 2", "May 9, 2025"),
                Triple(R.drawable.sample_skin_1, "Scan 3", "May 10, 2025")
            ).forEach { (img, title, date) ->
                CaseItem(img, title, date)
                Divider(modifier = Modifier.padding(vertical = 12.dp))
            }

            Spacer(modifier = Modifier.height(60.dp)) // Space for bottom nav
        }
    }
}

@Composable
fun StatCard(title: String, value: String, iconRes: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color(0xFFBDF7F8), RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                tint = Color.Gray
            )
        }
    }
}


@Composable
fun CaseItem(image: Int, title: String, date: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(Color(0xFFFFA500), shape = CircleShape)
                    .align(Alignment.TopStart)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(title, fontWeight = FontWeight.Medium)
            Text(date, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DermaHomeScreenPreview() {
    DermaHomeScreen(navController = rememberNavController())
}

