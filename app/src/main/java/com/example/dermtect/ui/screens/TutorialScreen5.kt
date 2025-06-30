package com.example.dermtect.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.TutorialScreenTemplate

@Composable
fun TutorialScreen5(navController: NavController) {
    TutorialScreenTemplate(
        navController = navController,
        imageResId = R.drawable.tutorial_pic5,
        title = "Download & Consult",
        description = "Generate your PDF report anytime — and use the clinic locator to find a nearby dermatologist.",
        nextRoute = "questionnaire",
        onBackClick = { navController.popBackStack() },
        onSkipClick = { navController.navigate("home") }
    )
}

@Preview(showBackground = true)
@Composable
fun TutorialScreen5Preview() {
    TutorialScreen5(navController = rememberNavController())
}