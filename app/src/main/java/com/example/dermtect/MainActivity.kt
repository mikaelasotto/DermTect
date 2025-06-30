package com.example.dermtect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.ui.screens.Register
import com.example.dermtect.ui.screens.Login
import com.example.dermtect.ui.screens.VerifyEmailScreen
import com.example.dermtect.ui.screens.DermaHomeScreen
import com.example.dermtect.ui.screens.UserHomeScreen
import com.example.dermtect.ui.screens.QuestionnaireScreen
import com.example.dermtect.ui.screens.NotificationScreen
import com.example.dermtect.ui.screens.HighlightArticle
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
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { Login(navController = navController) }
                    composable("register") { Register(navController = navController) }
                    composable("verify_email/{email}") { backStackEntry ->
                        val email = backStackEntry.arguments?.getString("email")
                        VerifyEmailScreen(navController = navController, email = email) }
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
