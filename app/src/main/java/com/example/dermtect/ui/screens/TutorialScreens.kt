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

@Composable
fun TutorialScreen1(navController: NavController) {
    TutorialScreenTemplate(
        navController = navController,
        imageResId = R.drawable.tutorial_pic1,
        title = "Frame the Spot",
        description = "Take a square photo with the mole or lesion centered in the frame.",
        nextRoute = "tutorial_screen2",
        onBackClick = { navController.popBackStack() },
        onSkipClick = { navController.navigate("user_home") }
    )
}

@Composable
fun TutorialScreen2(navController: NavController) {
    TutorialScreenTemplate(
        navController = navController,
        imageResId = R.drawable.tutorial_pic2,
        title = "Get Close, But Not Too Close",
        description = "Hold your camera 2–4 inches (5–10 cm) away for a clear, focused image.",
        nextRoute = "tutorial_screen3",
        onBackClick = { navController.popBackStack() },
        onSkipClick = { navController.navigate("user_home") }
    )
}

@Composable
fun TutorialScreen3(navController: NavController) {
    TutorialScreenTemplate(
        navController = navController,
        imageResId = R.drawable.tutorial_pic3,
        title = "Keep It Clean",
        description = "Make sure the skin is well-lit and free of hair, jewelry, or makeup that may block the view.",
        nextRoute = "tutorial_screen4",
        onBackClick = { navController.popBackStack() },
        onSkipClick = { navController.navigate("user_home") }
    )
}

@Composable
fun TutorialScreen4(navController: NavController) {
    TutorialScreenTemplate(
        navController = navController,
        imageResId = R.drawable.tutorial_pic4,
        title = "Save and Track",
        description = "After scanning, your result is saved automatically. You can track your scan history and monitor changes.",
        nextRoute = "tutorial_screen5",
        onBackClick = { navController.popBackStack() },
        onSkipClick = { navController.navigate("user_home") }
    )
}

@Composable
fun TutorialScreen5(navController: NavController) {
    TutorialScreenTemplate(
        navController = navController,
        imageResId = R.drawable.tutorial_pic5,
        title = "Download & Consult",
        description = "Generate your PDF report anytime — and use the clinic locator to find a nearby dermatologist.",
        nextRoute = "questionnaire",
        onBackClick = { navController.popBackStack() },
        onSkipClick = { navController.navigate("user_home") }
    )
}

@Preview(showBackground = true)
@Composable
fun TutorialScreen0Preview() {
    TutorialScreen0(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun TutorialScreen1Preview() {
    TutorialScreen1(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun TutorialScreen2Preview() {
    TutorialScreen2(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun TutorialScreen3Preview() {
    TutorialScreen3(navController = rememberNavController())

}

@Preview(showBackground = true)
@Composable
fun TutorialScreen4Preview() {
    TutorialScreen4(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun TutorialScreen5Preview() {
    TutorialScreen5(navController = rememberNavController())
}