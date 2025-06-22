package com.example.dermtect.ui.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dermtect.R
import com.example.dermtect.poppinsFont

@Composable
fun TutorialScreenTemplate(
    navController: NavController,
    imageResId: Int,
    title: String,
    description: String,
    nextRoute: String,
    onSkipClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Back Button
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "Back",
            modifier = Modifier
                .size(75.dp)
                .offset(x = 25.dp, y = 50.dp)
        )

        // Center Image
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Tutorial Image",
            modifier = Modifier
                .offset(x = 66.dp, y = 155.dp)
                .size(width = 280.dp, height = 280.dp)
        )

        // Bottom Description Box
        Box(
            modifier = Modifier
                .offset(y = 501.dp)
                .fillMaxWidth()
                .height(391.dp)
                .border(
                    width = 0.5.dp,
                    color = Color(0xFF0FB2B2),
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
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFont,
                    color = Color(0xFF1D1D1D)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = description,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFont,
                    color = Color(0xFF1D1D1D),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Next
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
                    Text("Next", fontFamily = poppinsFont, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Skip
                OutlinedButton(
                    onClick = onSkipClick,
                    modifier = Modifier
                        .width(307.dp)
                        .height(46.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF0FB2B2)
                    ),
                    border = BorderStroke(0.5.dp, Color(0xFF0FB2B2))
                ) {
                    Text("Skip", fontFamily = poppinsFont, fontSize = 16.sp)
                }
            }
        }
    }
}
