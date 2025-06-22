package com.example.dermtect.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.poppinsFont
import com.example.dermtect.ui.theme.DermTectTheme
import com.example.dermtect.ui.components.TutorialScreenTemplate

@Composable
fun TutorialScreen5(navController: NavController) {
    TutorialScreenTemplate(
        navController = navController,
        imageResId = R.drawable.tutorial_pic5,
        title = "Download & Consult",
        description = "Generate your PDF report anytime — and use the clinic locator to find a nearby dermatologist.",
        nextRoute = "tutorial_screen5",
        onSkipClick = { /* skip logic */ }
    )
}

@Preview(showBackground = true)
@Composable
fun TutorialScreen5Preview() {
    DermTectTheme {
        // Safe preview with empty lambda
        TutorialScreen5(navController = rememberNavController())
    }
}