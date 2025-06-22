package com.example.dermtect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.poppinsFont
import com.example.dermtect.ui.theme.DermTectTheme
import com.example.dermtect.ui.components.InputField

@Composable
fun Register(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpass by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top-left bubble
        Image(
            painter = painterResource(id = R.drawable.bubbles_top),
            contentDescription = "Top Left Bubble",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-24).dp, y = (-24).dp)
                .size(200.dp)
        )

        // Bottom-right bubble
        Image(
            painter = painterResource(id = R.drawable.bubbles_bottom),
            contentDescription = "Bottom Right Bubble",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (24).dp, y = (24).dp)
                .size(200.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Title
            Text(
                text = "Sign Up",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D)
            )

            Spacer(modifier = Modifier.height(32.dp))

            InputField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email",
                iconRes = R.drawable.icon_email
            )

            Spacer(modifier = Modifier.height(20.dp))

            InputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                iconRes = R.drawable.icon_pass
            )

            Spacer(modifier = Modifier.height(20.dp))

            InputField(
                value = confirmpass,
                onValueChange = { confirmpass = it },
                placeholder = "Confirm Password",
                iconRes = R.drawable.icon_pass // you can use a different icon if needed
            )


            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { /* navController.navigate("home") */ },
                modifier = Modifier
                    .width(299.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0FB2B2),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Register",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFont
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Other",
                fontSize = 12.sp,
                fontFamily = poppinsFont,
                color = Color(0xFF828282)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = "Google Login",
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text(
                    text = "Already have an account? ",
                    fontSize = 12.sp,
                    fontFamily = poppinsFont,
                    color = Color(0xFF1D1D1D)
                )
                Text(
                    text = "Login",
                    fontSize = 12.sp,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF2FD8D8),
                    modifier = Modifier.clickable {
                        navController.navigate("login")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    DermTectTheme {
        // Safe preview with empty lambda
        Register(navController = rememberNavController())
    }
}