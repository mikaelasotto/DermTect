package com.example.dermtect.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BubblesBackground(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top-left circle
        Box(
            modifier = Modifier
                .size(180.dp)
                .offset(x = (-90).dp, y = (-30).dp)
                .background(Color(0x8800FFFF).copy(alpha = 0.3f), shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(180.dp)
                .offset(x = 10.dp, y = (-90).dp)
                .background(Color(0x8800FFFF).copy(alpha = 0.3f) , shape = CircleShape)
        )


        // Bottom-right circle
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 90.dp, y = 30.dp)
                .background(Color(0x8800FFFF).copy(alpha = 0.3f), shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.BottomEnd)
                .offset(x = (-10).dp, y = 90.dp)
                .background(Color(0x8800FFFF).copy(alpha = 0.3f), shape = CircleShape)
        )

        content()
    }
}

@Preview(showBackground = true)
@Composable
fun SampleScreen() {
    BubblesBackground {}
}