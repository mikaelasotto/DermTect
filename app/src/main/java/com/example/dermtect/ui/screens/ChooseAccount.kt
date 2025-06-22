package com.example.dermtect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.dermtect.ui.components.AccountTypeButton
import com.example.dermtect.ui.theme.DermTectTheme

@Composable
fun ChooseAccount(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        // Top-left bubble
        Image(
            painter = painterResource(id = R.drawable.bubbles_top),
            contentDescription = "Top Left Bubble",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-24).dp, y = (-24).dp) // move up and left slightly
                .size(200.dp) // adjust size as needed
        )

        // Bottom-right bubble
        Image(
            painter = painterResource(id = R.drawable.bubbles_bottom),
            contentDescription = "Bottom Right Bubble",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (24).dp, y = (24).dp) // move down and right slightly
                .size(200.dp) // adjust size as needed
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 32.dp) // optional for better spacing
        ) {

            Text(
                text = "Type of Account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Choose type of your account",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(56.dp))

            AccountTypeButton(
                title = "Regular User",
                subtitle = "Scan and check your skin lesions, locate nearby clinics, and download a personal report",
                imageResId = R.drawable.regular_user,
                onClick = { navController.navigate("login") }
            )

            Spacer(modifier = Modifier.height(24.dp))

            AccountTypeButton(
                title = "Doctor",
                subtitle = "Access patient reports, view skin cases, and provide diagnostic insights.",
                imageResId = R.drawable.doctor,
                onClick = { navController.navigate("login_professional") },
                containerColor = Color(0xFFFFE3CD)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChooseAccountPreview() {
    DermTectTheme {
        // Safe preview with empty lambda
        ChooseAccount(navController = rememberNavController())
    }
}