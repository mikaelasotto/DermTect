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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            HighlightCard(onHighlightClick = {
                val highlightItem = NewsItem( //sample lang
                    imageResId = R.drawable.sample_skin,
                    title = "Skin Tone & Cancer Risk",
                    description =
                            "Skin cancer is among the most prevalent cancers globally, with millions of new cases diagnosed each year. While much attention has been given to sun protection, the role of skin tone in cancer risk is often overlooked.Individuals with lighter skin tones have less melanin, making them more vulnerable to ultraviolet (UV) radiation damage. This can increase the chances of developing basal cell carcinoma, squamous cell carcinoma, or melanoma. However, having a darker skin tone doesn’t make one immune — it often leads to delayed diagnoses because early signs are harder to detect.Dermatologists emphasize the importance of regular skin checks for everyone, regardless of skin color. Using sunscreen with at least SPF 30, avoiding tanning beds, and wearing protective clothing are essential preventive steps.Understanding your unique risk factors, including skin tone, is the first step toward early detection and treatment. Awareness saves lives, and it's time to include all skin types in the conversation" +
                            "Skin cancer is among the most prevalent cancers globally, with millions of new cases diagnosed each year. While much attention has been given to sun protection, the role of skin tone in cancer risk is often overlooked.Individuals with lighter skin tones have less melanin, making them more vulnerable to ultraviolet (UV) radiation damage. This can increase the chances of developing basal cell carcinoma, squamous cell carcinoma, or melanoma. However, having a darker skin tone doesn’t make one immune — it often leads to delayed diagnoses because early signs are harder to detect.Dermatologists emphasize the importance of regular skin checks for everyone, regardless of skin color. Using sunscreen with at least SPF 30, avoiding tanning beds, and wearing protective clothing are essential preventive steps.Understanding your unique risk factors, including skin tone, is the first step toward early detection and treatment. Awareness saves lives, and it's time to include all skin types in the conversation.",
                    source = "Health Times",
                    date = "2025-07-02"
                )
                val json = Uri.encode(Gson().toJson(highlightItem))
                navController.navigate("highlightarticle/$json")
            })

            Spacer(modifier = Modifier.height(10.dp))
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
            viewModel = viewModel, // ✅ add this
            modifier = Modifier.fillMaxWidth()
        )


        if (showConsentDialog && !hasConsented) {
            PrivacyConsentDialog(
        show = showConsentDialog,
                onConsent = {
                    viewModel.saveUserConsent()
                    showConsentDialog = false // just UI trigger
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

@Composable
fun NewsCarousel(newsItems: List<NewsItem>, onItemClick: (NewsItem) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp)
    ) {
        items(newsItems) { item ->
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .clickable { onItemClick(item) },
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                item.imageResId?.let { resId ->
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = item.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
                }

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
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
    viewModel: UserHomeViewModel, // ✅ add this
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
