package com.example.dermtect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dermtect.R
import com.example.dermtect.poppinsFont

@Composable
fun AssessmentSentOverlay(onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xD9353535)) // 85% opacity
            .wrapContentSize(Alignment.Center)
    ) {
        // Outer Box that allows overflow
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 40.dp)
        ) {
            // Your actual white rectangle
            Box(
                modifier = Modifier
                    .size(width = 318.dp, height = 323.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.White)
                    .align(Alignment.Center)
                    .padding(horizontal = 16.dp, vertical = 40.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Assessment Sent and\nNotified User",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppinsFont,
                        color = Color(0xFF0FB2B2),
                        textAlign = TextAlign.Center
                    )

                    Image(
                        painter = painterResource(id = R.drawable.verified),
                        contentDescription = "Loading...",
                        modifier = Modifier.size(188.dp)
                    )
                }
            }

            // Close Button Floating Half Outside
            IconButton(
                onClick = onClose,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.TopStart)
                    .offset(x = (-70).dp, y = (-65).dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = "Close",
                    modifier = Modifier.size(75.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AssessmentSentOverlayPreview() {
    AssessmentSentOverlay(onClose = {}) // empty lambda for preview
}

