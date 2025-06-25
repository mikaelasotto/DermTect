package com.example.dermtect.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import com.example.dermtect.poppinsFont
import com.example.dermtect.ui.components.InputField
import com.example.dermtect.ui.components.PrimaryButton
import com.example.dermtect.ui.components.ScreenLayout
import com.example.dermtect.ui.components.ScreenTitle
import com.example.dermtect.ui.components.SubText
import com.example.dermtect.ui.components.TopBottomBubbles
import com.example.dermtect.ui.theme.DermTectTheme

@Composable
fun ForgotPass4(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }

    val isPasswordValid = password.length >= 6
    val isMatch = password == confirmPass
    val canSubmit = password.isNotBlank() && confirmPass.isNotBlank() && isPasswordValid && isMatch

    ScreenLayout {
        TopBottomBubbles()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            ScreenTitle("Set a new password")
            Spacer(modifier = Modifier.height(8.dp))
            SubText("Create a new password. Ensure it differs from previous ones for security.")
            Spacer(modifier = Modifier.height(24.dp))

            // Label
            Text(
                text = "Password",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, bottom = 8.dp)
            )

            InputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                iconRes = R.drawable.icon_pass,
                isPassword = true,
                errorMessage = if (password.isNotBlank() && !isPasswordValid) "Password must be at least 6 characters" else null
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Label
            Text(
                text = "Confirm Password",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, bottom = 8.dp)
            )

            InputField(
                value = confirmPass,
                onValueChange = { confirmPass = it },
                placeholder = "Confirm Password",
                iconRes = R.drawable.icon_pass,
                isPassword = true,
                errorMessage = if (confirmPass.isNotBlank() && !isMatch) "Passwords do not match" else null
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                text = "Update Password",
                onClick = {
                    navController.navigate("login")
                },
                enabled = canSubmit
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