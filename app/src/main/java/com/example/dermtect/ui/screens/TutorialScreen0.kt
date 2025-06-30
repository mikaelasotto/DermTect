package com.example.dermtect.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.TutorialScreenTemplate

@Composable
fun TutorialScreen0(navController: NavController) {
    TutorialScreenTemplate(
        navController = navController,
        imageResId = R.drawable.tutorial_pic0,
        title = "Quick Skin Check First!",
        description = "Your answers give context to the scan, helping us assess your skin more accurately and with care.",
        nextRoute = "questionnaire",
        onSkipClick = { navController.navigate("tutorial_screen1") }, // temporarily go to home
        onBackClick = { navController.popBackStack() },
        nextButtonText = "Answer Questionnaire",
        skipButtonText = "Skip to tutorial"
    )
}

@Preview(showBackground = true)
@Composable
fun TutorialScreen0Preview() {
    TutorialScreen0(navController = rememberNavController())
}
