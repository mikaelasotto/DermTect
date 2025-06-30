package com.example.dermtect.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.TutorialScreenTemplate

@Composable
fun TutorialScreen2(navController: NavController) {
    TutorialScreenTemplate(
        navController = navController,
        imageResId = R.drawable.tutorial_pic2,
        title = "Get Close, But Not Too Close",
        description = "Hold your camera 2–4 inches (5–10 cm) away for a clear, focused image.",
        nextRoute = "tutorial_screen3",
        onBackClick = { navController.popBackStack() },
        onSkipClick = { navController.navigate("home") }
    )
}

@Preview(showBackground = true)
@Composable
fun TutorialScreen2Preview() {
        TutorialScreen2(navController = rememberNavController())
    }
