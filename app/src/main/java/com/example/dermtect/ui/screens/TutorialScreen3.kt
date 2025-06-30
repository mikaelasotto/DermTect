package com.example.dermtect.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.TutorialScreenTemplate

@Composable
fun TutorialScreen3(navController: NavController) {
    TutorialScreenTemplate(
        navController = navController,
        imageResId = R.drawable.tutorial_pic3,
        title = "Keep It Clean",
        description = "Make sure the skin is well-lit and free of hair, jewelry, or makeup that may block the view.",
        nextRoute = "tutorial_screen4",
        onBackClick = { navController.popBackStack() },
        onSkipClick = { navController.navigate("home") }
    )
}

@Preview(showBackground = true)
@Composable
fun TutorialScreen3Preview() {
        TutorialScreen3(navController = rememberNavController())

}