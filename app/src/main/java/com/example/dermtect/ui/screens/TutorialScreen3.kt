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
import com.example.dermtect.ui.components.TutorialScreenTemplate
import com.example.dermtect.ui.theme.DermTectTheme

@Composable
fun TutorialScreen3(navController: NavController) {
    TutorialScreenTemplate(
        navController = navController,
        imageResId = R.drawable.tutorial_pic3,
        title = "Keep It Clean",
        description = "Make sure the skin is well-lit and free of hair, jewelry, or makeup that may block the view.",
        nextRoute = "tutorial_screen4",
        onSkipClick = { /* skip logic */ }
    )
}

@Preview(showBackground = true)
@Composable
fun TutorialScreen3Preview() {
    DermTectTheme {
        // Safe preview with empty lambda
        TutorialScreen3(navController = rememberNavController())
    }
}