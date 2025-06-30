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
import androidx.compose.ui.unit.sp
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
                .offset(x = -20.dp, y = 50.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.Start
        ) {

            // Header Section

                Text(
                    text = "Hello,",
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Normal)
                )
                Text(
                    text = "Doc Mika!",
                    style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Early Detection Saves Lives.",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal)
                )

            Spacer(modifier = Modifier.height(30.dp))
            StatCardRow (onPendingCasesClick = {})

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
fun StatCardRow (onPendingCasesClick: () -> Unit){
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            StatCard(
                label = "Pending Cases",
                value = "20",
                imageRes = R.drawable.pending_cases,
                imageCardColor = Color(0xFFD7F2D6),
                modifier = Modifier.weight(1f)
            )
            StatCard(
                label = "Total Cases",
                value = "20",
                imageRes = R.drawable.total_cases,
                imageCardColor = Color(0xFFDCD2DE),
                modifier = Modifier.weight(1f)
            )
        }
    }
}


@Composable
fun StatCard(
    value: String,
    label: String,
    imageRes: Int,
    modifier: Modifier = Modifier,
    imageCardColor: Color = Color.White,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .height(150.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFCDFFFF))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        )   {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, color = Color.DarkGray),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Medium, fontSize = 30.sp),
                modifier = Modifier
                    .padding(start = 15.dp)
                    .fillMaxWidth(0.5f)
            )
            Card (
                colors = CardDefaults.cardColors(containerColor = imageCardColor),
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.End)
                    .padding(end = 10.dp, bottom = 10.dp)
                    .offset(x = -5.dp, y = -5.dp)
            ){
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = label,
                modifier = Modifier
                    .size(60.dp)
                    .padding(10.dp)

            ) }
        }
    }
}

fun onClick() {
    TODO("Not yet implemented")
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

