package com.example.dermtect.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.BackButton
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.dermtect.ui.components.ProgressIndicator

@Composable
fun TutorialScreenTemplate(
    navController: NavController,
    imageResId: Int,
    title: String,
    description: String,
    nextRoute: String,
    onSkipClick: () -> Unit, // ✅ fixed: added missing type + comma
    onBackClick: (() -> Unit)? = null, // ✅ optional
    nextButtonText: String = "Next",
    skipButtonText: String = "Skip",
    currentIndex: Int? = null

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        // ✅ Optional back button
        if (onBackClick != null) {
            BackButton(
                onClick = onBackClick,
                modifier = Modifier
                    .offset(x = 25.dp, y = 50.dp)
                    .align(Alignment.TopStart)
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize(0.9f)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()), // Add scroll support
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(80.dp)) // Leave space for back button

                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Tutorial Image",
                    modifier = Modifier
                        .size(280.dp)
                )
                Spacer(modifier = Modifier.height(60.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.displaySmall,
                    color = Color(0xFF1D1D1D),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                    color = Color(0xFF1D1D1D),
                    textAlign = TextAlign.Center
                )


            }
            Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                if (currentIndex != null) {
                    ProgressIndicator(
                        totalDots = 4,
                        selectedIndex = currentIndex
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = { navController.navigate(nextRoute) },
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .wrapContentHeight(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0FB2B2),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            nextButtonText,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = onSkipClick,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .wrapContentHeight(),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFF0FB2B2)
                        ),
                        border = BorderStroke(1.dp, Color(0xFF0FB2B2))
                    ) {
                        Text(
                            skipButtonText,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal)
                        )
                    }
                }

            }
    }
}

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
        onSkipClick = { navController.navigate("user_home") },
        currentIndex = 0
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
        onSkipClick = { navController.navigate("user_home") },
        currentIndex = 1
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
        onSkipClick = { navController.navigate("user_home") },
        currentIndex = 2
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
        onSkipClick = { navController.navigate("user_home") },
        currentIndex = 3
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