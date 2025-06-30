package com.example.dermtect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.BackButton
import java.util.*

// Notification data model
data class NotificationItem(
    val id: String,
    val title: String,
    val message: String,
    val type: Int,
    val timestamp: String,
    var isRead: Boolean = true,
    val date: Date = Date()
)

enum class FilterOption { ALL, READ, UNREAD, NEWEST_FIRST, OLDEST_FIRST }

@Composable
fun NotificationScreen(navController: NavController) {
    var filterOption by remember { mutableStateOf(FilterOption.ALL) }
    var showFilterMenu by remember { mutableStateOf(false) }
    var notifications by remember {
        mutableStateOf(
            listOf(
                NotificationItem("1", "Skin Check Result Ready", "Your skin check report is now available.", 1, "2 hours ago", true),
                NotificationItem("2", "App Update Available", "Update the app to the latest version.", 2, "Yesterday", true),
                NotificationItem("3", "Scan Upload Successful", "Your skin scan has been uploaded and is pending review.", 5, "1 day ago", false),
                NotificationItem("4", "Health Tip of the Day", "Avoid prolonged sun exposure between 10 AM – 4 PM to reduce risk.", 7, "Today", true),
                NotificationItem("5", "Incomplete Questionnaire Reminder", "You haven't completed the skin questionnaire.", 9, "Just now", false)
            )
        )
    }

    fun applyFilter(list: List<NotificationItem>): List<NotificationItem> {
        val sortedList = when (filterOption) {
            FilterOption.NEWEST_FIRST -> list.sortedByDescending { it.date }
            FilterOption.OLDEST_FIRST -> list.sortedBy { it.date }
            else -> list
        }
        return when (filterOption) {
            FilterOption.READ -> sortedList.filter { it.isRead }
            FilterOption.UNREAD -> sortedList.filter { !it.isRead }
            else -> sortedList
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAFDFD))
    ) {
        BackButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.offset(x = 25.dp, y = 50.dp)
        )

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(modifier = Modifier.align(Alignment.End).padding(end = 20.dp)) {
                IconButton(onClick = { showFilterMenu = !showFilterMenu }) {
                    Icon(painter = painterResource(id = R.drawable.filter), contentDescription = "Filter", modifier = Modifier.size(20.dp))
                }
                DropdownMenu(expanded = showFilterMenu, onDismissRequest = { showFilterMenu = false }, modifier = Modifier.background(Color.White)) {
                    DropdownMenuItem(text = { Text("All", style = MaterialTheme.typography.labelMedium) }, onClick = {
                        filterOption = FilterOption.ALL
                        showFilterMenu = false
                    })
                    DropdownMenuItem(text = { Text("Read",style = MaterialTheme.typography.labelMedium) }, onClick = {
                        filterOption = FilterOption.READ
                        showFilterMenu = false
                    })
                    DropdownMenuItem(text = { Text("Unread",  style = MaterialTheme.typography.labelMedium) }, onClick = {
                        filterOption = FilterOption.UNREAD
                        showFilterMenu = false
                    })
                    DropdownMenuItem(text = { Text("Newest to Oldest", style = MaterialTheme.typography.labelMedium) }, onClick = {
                        filterOption = FilterOption.NEWEST_FIRST
                        showFilterMenu = false
                    })
                    DropdownMenuItem(text = { Text("Oldest to Newest", style = MaterialTheme.typography.labelMedium) }, onClick = {
                        filterOption = FilterOption.OLDEST_FIRST
                        showFilterMenu = false
                    })
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            val filteredList = applyFilter(notifications)

            if (filteredList.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.notifications_vector),
                        contentDescription = "No Notifications Icon",
                        tint = Color(0xFFB0BEC5),
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "No notifications yet",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = Color.Gray
                    )
                }
            } else {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(40.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredList, key = { it.id }) { notification ->
                            var menuExpanded by remember { mutableStateOf(false) }

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Image(
                                        painter = painterResource(
                                            id = when (notification.type) {
                                                1 -> R.drawable.notif_type1
                                                2 -> R.drawable.notif_type2
                                                3 -> R.drawable.notif_type3
                                                4 -> R.drawable.notif_type4
                                                5 -> R.drawable.notif_type5
                                                else -> R.drawable.notif_type5
                                            }
                                        ),
                                        contentDescription = "Notification Icon",
                                        modifier = Modifier
                                            .size(27.dp)
                                            .padding(top = 5.dp)
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = notification.title,
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = notification.message,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Text(
                                            text = notification.timestamp,
                                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                                            modifier = Modifier.padding(top = 4.dp)
                                        )
                                    }
                                    Box {
                                        IconButton(onClick = { menuExpanded = true }) {
                                            Icon(Icons.Default.MoreHoriz, contentDescription = "Options")
                                        }
                                        DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false },modifier = Modifier.background(Color.White)) {
                                            DropdownMenuItem(
                                                text = { Text("Delete",  style = MaterialTheme.typography.labelMedium) },
                                                onClick = {
                                                    notifications = notifications.filterNot { it.id == notification.id }
                                                    menuExpanded = false
                                                }
                                            )
                                            DropdownMenuItem(
                                                text = { Text(if (notification.isRead) "Mark as Unread" else "Mark as Read", style = MaterialTheme.typography.labelMedium) },
                                                onClick = {
                                                    notifications = notifications.map {
                                                        if (it.id == notification.id) it.copy(isRead = !it.isRead) else it
                                                    }
                                                    menuExpanded = false
                                                }
                                            )
                                        }
                                    }
                                }
                                if (notification != filteredList.last()) {
                                    HorizontalDivider(modifier = Modifier.padding(top = 15.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreen(navController = rememberNavController())
}
