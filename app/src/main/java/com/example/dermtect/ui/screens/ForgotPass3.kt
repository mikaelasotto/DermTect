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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun ForgotPass3(navController: NavController) {
    ScreenLayout {
        TopBottomBubbles()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            ScreenTitle(title = "Password Reset")

            Spacer(modifier = Modifier.height(8.dp))

            SubText(
                text = "Your password has been successfully reset.\nClick confirm to set a new password"
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                text = "Confirm",
                onClick = {
                    // navController.navigate("forgotpass4") or wherever
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ForgotPass3Preview() {
    DermTectTheme {
        // Safe preview with empty lambda
        ForgotPass3(navController = rememberNavController())
    }
}