package com.example.dermtect.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.ui.theme.DermTectTheme

@Composable
fun OnboardingScreen3(navController: NavController) {
    OnboardingScreen(
        imageRes = com.example.dermtect.R.drawable.skin_health,
        title = "Your Skin Health,\nJust a Tap Away",
        description = "Fast, simple, and secure skin\nassessments anytime.",
        onNextClick = { navController.navigate("onboarding_screen4") }
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen3Preview() {
    DermTectTheme {
        OnboardingScreen3(navController = rememberNavController())
    }
}
