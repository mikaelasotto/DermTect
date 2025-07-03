package com.example.dermtect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.ui.components.ProgressIndicator


@Composable
fun OnboardingScreen(
    imageRes: Int,
    title: String,
    description: String,
    buttonText: String = "Next",
    onNextClick: () -> Unit,
    currentIndex: Int? = null
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize(0.9f)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 70.dp)
                    .verticalScroll(rememberScrollState()), // Add scroll support
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(imageRes)
                        .build(),
                    imageLoader = imageLoader,
                    contentDescription = null,
                    modifier = Modifier
                        .size(280.dp)
                )
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF1D1D1D),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                    color = Color(0xFF1D1D1D),
                    textAlign = TextAlign.Center
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (currentIndex != null) {
                    ProgressIndicator(
                        totalDots = 3,
                        selectedIndex = currentIndex
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onNextClick,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .wrapContentHeight(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0FB2B2),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = buttonText,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    )
                }
            }
        }
    }
}


@Composable
fun OnboardingScreen1(navController: NavController) {
    OnboardingScreen(
        imageRes = com.example.dermtect.R.drawable.welcome,
        title = "Welcome to DermTect!",
        description = "Your AI-powered companion for\nearly skin health checks.",
        onNextClick = { navController.navigate("onboarding_screen2") },
        currentIndex = 0
    )
}

@Composable
fun OnboardingScreen2(navController: NavController) {
    OnboardingScreen(
        imageRes = com.example.dermtect.R.drawable.scan,
        title = "Scan. Assess. Find Help",
        description = "Snap a photo, answer a few\nquestions, and get instant results.",
        onNextClick = { navController.navigate("onboarding_screen3") },
        currentIndex = 1
    )
}

@Composable
fun OnboardingScreen3(navController: NavController) {
    OnboardingScreen(
        imageRes = com.example.dermtect.R.drawable.skin_health,
        title = "Your Skin Health,\nJust a Tap Away",
        description = "Fast, simple, and secure skin\nassessments anytime.",
        onNextClick = { navController.navigate("login") },
        currentIndex = 2

    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen1Preview() {
        OnboardingScreen1(navController = rememberNavController())
}


@Preview(showBackground = true)
@Composable
fun OnboardingScreen2Preview() {
        OnboardingScreen2(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen3Preview() {
        OnboardingScreen3(navController = rememberNavController())
}