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
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.PrivacyConsentDialog
import com.example.dermtect.ui.components.saveConsent
import com.example.dermtect.ui.components.TopRightNotificationIcon
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.CoroutineScope

@Composable
fun UserHomeScreen(navController: NavController) {

    val auth = FirebaseAuth.getInstance()
    val userId = auth.currentUser?.uid ?: ""

    val coroutineScope = rememberCoroutineScope()
    var showConsentDialog by remember { mutableStateOf(false) }
    var hasCheckedConsent by remember { mutableStateOf(false) }
    var hasConsented by remember { mutableStateOf(false) }
    var firstName by remember { mutableStateOf("User") }

    val db = FirebaseFirestore.getInstance()
    var pendingCameraAction by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (userId.isNotEmpty()) {
            val userDoc = db.collection("users").document(userId).get().await()
            firstName = userDoc.getString("firstName")?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            } ?: "User"
        }
    }

    // Check Consent on Launch
    LaunchedEffect(userId) {
        if (userId.isNotEmpty() && !hasCheckedConsent) {
            val userDoc = db.collection("users").document(userId).get().await()
            val consent = userDoc.getBoolean("privacyConsent") == true
            hasConsented = consent             // ✅ set state to be reused anywhere
            showConsentDialog = !consent       // ✅ show dialog only if not yet consented
            hasCheckedConsent = true
        }
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        TopRightNotificationIcon(
            onNotifClick = {
                if (!hasConsented) {
                    showConsentDialog = true
                    return@TopRightNotificationIcon
                }
                navController.navigate("notifications")
            },
            modifier = Modifier
                .padding(top = 50.dp, end = 25.dp)
                .align(Alignment.End)
        )


        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "Hello,",
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Normal)
            )
            Text(
                text = "$firstName!",
                style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "Early Detection Saves Lives.",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal)
            )

            Spacer(modifier = Modifier.height(30.dp))
            HomeFeatureButtonsRow(
                hasConsented = hasConsented,
                onShowConsentDialog = { showConsentDialog = true },
                onSkinReportClick = { navController.navigate("questionnaire") }
            )

            Spacer(modifier = Modifier.height(20.dp))
            HighlightCard(onHighlightClick = { navController.navigate("highlightarticle") })
            Spacer(modifier = Modifier.height(10.dp))
            NewsSection()
            Spacer(modifier = Modifier.height(10.dp))
            NewsCarousel(newsItems = sampleNews)
        }

        BottomNavBar(
            navController = navController,
            hasConsented = hasConsented,
            onShowConsentDialog = {
                pendingCameraAction = true
                showConsentDialog = true
            },
            setPendingCameraAction = { pendingCameraAction = it },
            coroutineScope = coroutineScope,
            modifier = Modifier.fillMaxWidth()
        )

        if (showConsentDialog) {
            PrivacyConsentDialog(
                show = showConsentDialog,
                onConsent = {
                    coroutineScope.launch {
                        saveConsent(userId)                      // Save to Firestore
                        hasConsented = true                      // ✅ Mark as consented
                        showConsentDialog = false                // ✅ Hide dialog forever
                    }
                },
                onDecline = {
                    showConsentDialog = false                    // Just hide dialog, no access to other features
                }
            )

        }
    }
}


@Composable
fun HomeFeatureButtonsRow(
    hasConsented: Boolean,
    onShowConsentDialog: () -> Unit,
    onSkinReportClick: () -> Unit
) {

    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            HomeFeatureButton(
                label = "Skin Report",
                imageRes = R.drawable.skin_report,
                modifier = Modifier.weight(1f),
                onClick = {
                    if (!hasConsented) {
                        onShowConsentDialog()
                        return@HomeFeatureButton
                    }
                    onSkinReportClick()
                }

            )
            HomeFeatureButton(
                label = "Nearby Clinics",
                imageRes = R.drawable.nearby_clinics,
                modifier = Modifier.weight(1f),
                onClick = {
                    if (!hasConsented) {
                        onShowConsentDialog()
                        return@HomeFeatureButton
                    }
                    // TODO: Add clinic navigation
                }

            )
        }
    }
}

@Composable
fun HomeFeatureButton(
    label: String,
    imageRes: Int,
    modifier: Modifier = Modifier,
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = label,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun HighlightCard(onHighlightClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.Center)
                .clickable { onHighlightClick() },
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF2E8))
        ) {
            Row(
                modifier = Modifier.padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Skin Tone & Cancer Risk",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Understand the link between skin tone and cancer risk.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.risk_image),
                    contentDescription = "Skin Check Icon",
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    }
}

@Composable
fun NewsSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Infos & News",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}

data class NewsItem(val imageRes: Int, val title: String, val description: String)

val sampleNews = listOf(
    NewsItem(R.drawable.news1, "What is the UV Index?", "Learn how UV levels affect your skin health."),
    NewsItem(R.drawable.news1, "Protect Your Skin Today", "Top dermatologist tips for sun protection."),
    NewsItem(R.drawable.news1, "Check UV Levels Near You", "Stay safe: Know your area's UV index daily.")
)

@Composable
fun NewsCarousel(newsItems: List<NewsItem>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp)
    ) {
        items(newsItems) { item ->
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(150.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun BottomNavBar(
    navController: NavController,
    hasConsented: Boolean,
    onShowConsentDialog: () -> Unit,
    setPendingCameraAction: (Boolean) -> Unit,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(Color.White)
    ) {
        Surface(
            color = Color(0xFFCDFFFF),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home_vector),
                    contentDescription = "Home",
                    modifier = Modifier.size(26.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.user_vector),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(26.dp)
                        .clickable {
                            if (!hasConsented) {
                                onShowConsentDialog()
                                return@clickable
                            }
                            // navController.navigate("profile")
                        }
                )

            }
        }
        // ✅ Floating Camera Icon with Navigation
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-12).dp)
                .size(60.dp)
                .background(Color(0xFF0FB2B2), shape = CircleShape)
                .border(5.dp, Color.White, shape = CircleShape)
                .clickable {
                    if (!hasConsented) {
                        setPendingCameraAction(true)
                        onShowConsentDialog()
                        return@clickable
                    }

                    coroutineScope.launch {
                        val uid = FirebaseAuth.getInstance().currentUser?.uid
                        if (uid == null) {
                            navController.navigate("tutorial_screen0")
                            return@launch
                        }

                        try {
                            val document = FirebaseFirestore.getInstance()
                                .collection("questionnaires")
                                .document(uid)
                                .get()
                                .await()

                            if (document.exists()) {
                                navController.navigate("tutorial_screen1")
                            } else {
                                navController.navigate("tutorial_screen0")
                            }
                        } catch (_: Exception) {
                            navController.navigate("tutorial_screen0")
                        }
                    }
                },

                    contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.camera_vector),
                contentDescription = "Camera",
                modifier = Modifier.size(23.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserHomeScreenPreview() {
    UserHomeScreen(navController = rememberNavController())
}
