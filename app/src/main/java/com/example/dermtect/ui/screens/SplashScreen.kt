package com.example.dermtect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dermtect.poppinsFont
import com.example.dermtect.ui.theme.DermTectTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000) // 2 seconds
        navController.navigate("onboarding_screen1"){
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0FB2B2)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "DermTect",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFont,
            color = Color.White
        )
    }
}

@Preview(name = "Splash Screen", showBackground = true)
@Composable
fun SplashScreenPreview() {
    DermTectTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0FB2B2)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "DermTect",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFont,
                color = Color.White
            )
        }
    }
}