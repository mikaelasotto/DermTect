package com.example.dermtect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.dermtect.ui.components.TopBottomBubbles
import com.example.dermtect.ui.components.ScreenLayout
import com.example.dermtect.ui.components.ScreenTitle
import com.example.dermtect.ui.components.SubText
import com.example.dermtect.ui.components.PrimaryButton
import com.example.dermtect.ui.components.CodeInputBoxes
import com.example.dermtect.ui.components.ResendEmailText

@Composable
fun ForgotPass4(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var confirmpass by remember { mutableStateOf("") }

    ScreenLayout {
        TopBottomBubbles()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            ScreenTitle(title = "Set a new password")
            Spacer(modifier = Modifier.height(8.dp))

            SubText(text = "Create a new password. Ensure it differs from previous ones for security.")
            Spacer(modifier = Modifier.height(24.dp))

            // Password label
            Text(
                text = "Password",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Password input
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password", fontFamily = poppinsFont) },
                modifier = Modifier
                    .width(299.dp)
                    .height(52.dp)
                    .background(Color(0xFFF4F3F3), RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Confirm Password label
            Text(
                text = "Confirm Password",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Confirm Password input
            TextField(
                value = confirmpass,
                onValueChange = { confirmpass = it },
                placeholder = { Text("Confirm Password", fontFamily = poppinsFont) },
                modifier = Modifier
                    .width(299.dp)
                    .height(52.dp)
                    .background(Color(0xFFF4F3F3), RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                text = "Update Password",
                onClick = {
                    // navController.navigate("home") or your desired screen
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPass4Preview() {
    DermTectTheme {
        // Safe preview with empty lambda
        ForgotPass4(navController = rememberNavController())
    }
}