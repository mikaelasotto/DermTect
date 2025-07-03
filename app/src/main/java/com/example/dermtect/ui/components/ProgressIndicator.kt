package com.example.dermtect.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicator(
    totalDots: Int = 5,
    selectedIndex: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(totalDots) { index ->
            val isSelected = index == selectedIndex
            val width = if (isSelected) 20.dp else 10.dp
            val color = if (isSelected) Color(0xFF0FB2B2) else Color.LightGray

            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .height(10.dp)
                    .width(width)
                    .background(
                        color = color,
                        shape = RoundedCornerShape(percent = 50)
                    )
            )
        }
    }
}
