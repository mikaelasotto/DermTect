package com.example.dermtect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.ui.screens.Register
import com.example.dermtect.ui.screens.Login
import com.example.dermtect.ui.screens.DermaHomeScreen
import com.example.dermtect.ui.screens.ForgotPass1
import com.example.dermtect.ui.screens.ForgotPass2
import com.example.dermtect.ui.screens.ForgotPass3
import com.example.dermtect.ui.screens.ForgotPass4
import com.example.dermtect.ui.screens.UserHomeScreen
import com.example.dermtect.ui.screens.QuestionnaireScreen
import com.example.dermtect.ui.screens.NotificationScreen
import com.example.dermtect.ui.screens.HighlightArticle
import com.example.dermtect.ui.screens.OnboardingScreen1
import com.example.dermtect.ui.screens.OnboardingScreen2
import com.example.dermtect.ui.screens.OnboardingScreen3
import com.example.dermtect.ui.screens.SplashScreen
import com.example.dermtect.ui.screens.TutorialScreen0
import com.example.dermtect.ui.screens.TutorialScreen1
import com.example.dermtect.ui.screens.TutorialScreen2
import com.example.dermtect.ui.screens.TutorialScreen3
import com.example.dermtect.ui.screens.TutorialScreen4
import com.example.dermtect.ui.screens.TutorialScreen5
import com.example.dermtect.ui.theme.DermtectTheme
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        setContent {
            DermtectTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") { SplashScreen(navController) }
                    composable("onboarding_screen1") { OnboardingScreen1(navController) }
                    composable("onboarding_screen2") { OnboardingScreen2(navController) }
                    composable("onboarding_screen3") { OnboardingScreen3(navController) }
                    composable("login") { Login(navController = navController) }
                    composable("register") { Register(navController = navController) }
                    composable("forgot_pass1") { ForgotPass1(navController) }
                    composable("forgot_pass2") { ForgotPass2(navController) }
                    composable("forgot_pass3") { ForgotPass3(navController) }
                    composable("forgot_pass3") { ForgotPass4(navController) }
                    composable("user_home") {UserHomeScreen(navController = navController) }
                    composable("derma_home") {DermaHomeScreen(navController = navController) }
                    composable("notifications") {NotificationScreen(navController = navController) }
                    composable("questionnaire") { QuestionnaireScreen(navController = navController)}
                    composable("highlightarticle") {HighlightArticle(navController = navController) }
                    composable("tutorial_screen0") {TutorialScreen0(navController = navController) }
                    composable("tutorial_screen1") {TutorialScreen1(navController) }
                    composable("tutorial_screen2") { TutorialScreen2(navController) }
                    composable("tutorial_screen3") { TutorialScreen3(navController) }
                    composable("tutorial_screen4") { TutorialScreen4(navController) }
                    composable("tutorial_screen5") { TutorialScreen5(navController) }
                }

            }
            }
        }
    }
