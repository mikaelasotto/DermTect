package com.example.dermtect.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController

@Composable
fun TutorialScreenTemplate(
    navController: NavController,
    imageResId: Int,
    title: String,
    description: String,
    nextRoute: String,
    onSkipClick: () -> Unit, // ✅ fixed: added missing type + comma
    onBackClick: (() -> Unit)? = null, // ✅ optional
    nextButtonText: String = "Next",
    skipButtonText: String = "Skip"
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // ✅ Optional back button
        if (onBackClick != null) {
            BackButton(
                onClick = onBackClick,
                modifier = Modifier
                    .offset(x = 25.dp, y = 50.dp)
            )
        }

        // Main tutorial image
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Tutorial Image",
            modifier = Modifier
                .offset(x = 66.dp, y = 155.dp)
                .size(width = 280.dp, height = 280.dp)
        )

        // Bottom info container
        Box(
            modifier = Modifier
                .offset(y = 501.dp)
                .fillMaxWidth()
                .height(391.dp)
                .border(
                    width = 0.5.dp,
                    color = (Color.White),
                    shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(64.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.displayMedium,
                    color = Color(0xFF1D1D1D)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF1D1D1D),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { navController.navigate(nextRoute) },
                    modifier = Modifier
                        .width(307.dp)
                        .height(46.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0FB2B2),
                        contentColor = Color.White
                    )
                ) {
                    Text(nextButtonText, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal))
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = onSkipClick,
                    modifier = Modifier
                        .width(307.dp)
                        .height(46.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF0FB2B2)
                    ),
                    border = BorderStroke(1.dp, Color(0xFF0FB2B2))
                ) {
                    Text(skipButtonText, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal))
                }
            }
        }
    }
}
