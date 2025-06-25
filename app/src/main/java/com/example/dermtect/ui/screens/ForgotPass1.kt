package com.example.dermtect.ui.screens

import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.dermtect.ui.components.InputField


@Composable
fun ForgotPass1(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }

    val isEmailValid = remember(email) {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    ScreenLayout {
        TopBottomBubbles()

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle("Forgot password")
            Spacer(modifier = Modifier.height(8.dp))
            SubText("Please enter your email to reset the password")
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Your Email",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, bottom = 8.dp)
            )

            InputField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = if (it.isBlank()) {
                        "Email is required"
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(it).matches()) {
                        "Invalid email address"
                    } else {
                        ""
                    }
                },
                placeholder = "name@example.com",
                iconRes = R.drawable.icon_email,
                textColor = Color.Black,
                errorMessage = emailError.takeIf { it.isNotEmpty() } // only show if not empty
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                text = "Reset Password",
                onClick = {
                    navController.navigate("forgot_pass2")
                },
                enabled = isEmailValid && email.isNotBlank()
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ForgotPass1Preview() {
    DermTectTheme {
        // Safe preview with empty lambda
        ForgotPass1(navController = rememberNavController())
    }
}