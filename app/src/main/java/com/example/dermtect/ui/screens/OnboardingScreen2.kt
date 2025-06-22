package com.example.dermtect.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.ui.theme.DermTectTheme

@Composable
fun OnboardingScreen2(navController: NavController) {
    OnboardingScreen(
        imageRes = com.example.dermtect.R.drawable.scan,
        title = "Scan. Assess. Find Help",
        description = "Snap a photo, answer a few\nquestions, and get instant results.",
        onNextClick = { navController.navigate("onboarding_screen3") }
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen2Preview() {
    DermTectTheme {
        OnboardingScreen2(navController = rememberNavController())
    }
}
