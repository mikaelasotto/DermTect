package com.example.dermtect.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.ui.theme.DermTectTheme

@Composable
fun OnboardingScreen1(navController: NavController) {
    OnboardingScreen(
        imageRes = com.example.dermtect.R.drawable.welcome,
        title = "Welcome to DermTect!",
        description = "Your AI-powered companion for\nearly skin health checks.",
        onNextClick = { navController.navigate("onboarding_screen2") }
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen1Preview() {
    DermTectTheme {
        OnboardingScreen1(navController = rememberNavController())
    }
}
