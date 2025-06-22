package com.example.dermtect.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.ui.theme.DermTectTheme
import com.example.dermtect.ui.components.TutorialScreenTemplate

@Composable
fun TutorialScreen1(navController: NavController) {
    TutorialScreenTemplate(
        navController = navController,
        imageResId = R.drawable.tutorial_pic1,
        title = "Frame the Spot",
        description = "Take a square photo with the mole or lesion centered in the frame.",
        nextRoute = "tutorial_screen2",
        onSkipClick = { /* skip logic */ }
    )
}


@Preview(showBackground = true)
@Composable
fun TutorialScreen1Preview() {
    DermTectTheme {
        // Safe preview with empty lambda
        TutorialScreen1(navController = rememberNavController())
    }
}