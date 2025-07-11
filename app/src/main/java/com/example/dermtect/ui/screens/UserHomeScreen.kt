package com.example.dermtect.ui.screens


import android.net.Uri
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.TopRightNotificationIcon
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dermtect.ui.viewmodel.UserHomeViewModel
import com.example.dermtect.model.NewsItem
import com.google.gson.Gson


@Composable
fun UserHomeScreen(navController: NavController) {

    val viewModel: UserHomeViewModel = viewModel()
    var showConsentDialog by remember { mutableStateOf(false) }
    val firstName by viewModel.firstName.collectAsState()
    val hasConsented by viewModel.hasConsented.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val consentChecked by viewModel.consentChecked.collectAsState()
    var pendingCameraAction by remember { mutableStateOf(false) }
    val newsItems by viewModel.newsItems.collectAsState()
    val isLoadingNews by viewModel.isLoadingNews.collectAsState()
    val gson = remember { Gson() }
    LaunchedEffect(Unit) {
        viewModel.fetchUserInfo()
        viewModel.checkConsentStatus()
        viewModel.fetchNews()
    }

    LaunchedEffect(consentChecked, hasConsented) {
        if (consentChecked && !hasConsented) {
            showConsentDialog = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Add vertical space above the welcome section
        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(176.dp) // matches your light blue container height
                .background(Color(0x3DCDFFFF)) // 24% opacity of #CDFFFF
                .padding(start = 20.dp, end = 20.dp, top = 26.dp) // spacing from top
        ) {
            Column(
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(
                    text = "Hello,",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 32.sp,
                        color = Color(0xFF1D1D1D)
                    )
                )
                Text(
                    text = "$firstName!",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 42.sp,
                        color = Color(0xFF1D1D1D)
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Early Detection Saves Lives.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        color = Color(0xFF1D1D1D)
                    )
                )
            }

            TopRightNotificationIcon(
                onNotifClick = {
                    if (!hasConsented) {
                        showConsentDialog = true
                        return@TopRightNotificationIcon
                    }
                    navController.navigate("notifications")
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp)
            )
        }


        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            HomeFeatureButtonsRow(
                hasConsented = hasConsented,
                onShowConsentDialog = { showConsentDialog = true },
                onSkinReportClick = { navController.navigate("questionnaire") }
            )

            Spacer(modifier = Modifier.height(20.dp))
            HighlightCard(onHighlightClick = {
                val highlightItem = NewsItem(
                    imageResId = R.drawable.sample_skin,
                    title = "Skin Tone & Cancer Risk",
                    description =
                        "Skin cancer is among the most prevalent cancers globally...",
                    source = "Health Times",
                    date = "2025-07-02"
                )
                val json = Uri.encode(Gson().toJson(highlightItem))
                navController.navigate("highlightarticle/$json")
            })

            Spacer(modifier = Modifier.height(20.dp))
            NewsSection()
            Spacer(modifier = Modifier.height(10.dp))
            when {
                isLoadingNews -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                newsItems.isEmpty() -> {
                    Text(
                        text = "No news available at the moment.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                else -> {
                    NewsCarousel(
                        newsItems = newsItems,
                        onItemClick = { item ->
                            val json = Uri.encode(gson.toJson(item))
                            navController.navigate("article_detail_screen/$json")
                        }
                    )

                }
            }
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
            viewModel = viewModel,
            modifier = Modifier.fillMaxWidth()
        )

        if (showConsentDialog && !hasConsented) {
            PrivacyConsentDialog(
                show = showConsentDialog,
                onConsent = {
                    viewModel.saveUserConsent()
                    showConsentDialog = false
                },
                onDecline = {
                    showConsentDialog = false
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
                .height(100.dp)
                .align(Alignment.Center)
                .clickable { onHighlightClick() }, // ✅ add comma here
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFF2E8)
            ),
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Understand the link between\nskin tone and cancer risk.",
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

@Composable
fun NewsCarousel(newsItems: List<NewsItem>, onItemClick: (NewsItem) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(newsItems) { item ->
            Card(
                modifier = Modifier
                    .width(240.dp)
                    .clickable { onItemClick(item) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA))
            ) {
                Column {
                    // Image at the top with rounded corners (shared with card)
                    item.imageResId?.let { resId ->
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = item.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        )
                    }

                    // Text content
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF1D1D1D)
                            )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF1D1D1D)
                            ),
                            maxLines = 2
                        )
                    }
                }
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
    viewModel: UserHomeViewModel, // ✅ add this
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp) // Outer Box
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
                    .height(74.dp) // Inner Row
                    .padding(horizontal = 65.dp),
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
                    contentDescription = "Settings",
                    modifier = Modifier
                        .size(26.dp)
                        .clickable {
                            if (!hasConsented) {
                                onShowConsentDialog()
                                return@clickable
                            }
                            navController.navigate("profile")
                        }
                )

            }
        }
        // ✅ Floating Camera Icon with Navigation
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-12).dp)
                .size(65.dp)
                .background(Color(0xFF0FB2B2), shape = CircleShape)
                .border(3.dp, Color.White, shape = CircleShape)
                .clickable {
                    if (!hasConsented) {
                        setPendingCameraAction(true)
                        onShowConsentDialog()
                        return@clickable
                    }

                    coroutineScope.launch {
                        try {
                            val answered = viewModel.hasAnsweredQuestionnaire()
                            navController.navigate(if (answered) "tutorial_screen1" else "tutorial_screen0")
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
