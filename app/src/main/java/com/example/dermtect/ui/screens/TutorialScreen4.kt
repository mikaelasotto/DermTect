package com.example.dermtect.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.TutorialScreenTemplate

@Composable
fun TutorialScreen4(navController: NavController) {
    TutorialScreenTemplate(
        navController = navController,
        imageResId = R.drawable.tutorial_pic4,
        title = "Save and Track",
        description = "After scanning, your result is saved automatically. You can track your scan history and monitor changes.",
        nextRoute = "tutorial_screen5",
        onBackClick = { navController.popBackStack() },
        onSkipClick = { navController.navigate("home") }
    )
}

@Preview(showBackground = true)
@Composable
fun TutorialScreen4Preview() {
   TutorialScreen4(navController = rememberNavController())
}