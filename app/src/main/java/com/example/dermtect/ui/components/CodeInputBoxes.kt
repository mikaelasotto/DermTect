package com.example.dermtect.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun CodeInputBoxes(
    count: Int = 5,
    boxSize: Dp = 56.dp,
    boxSpacing: Dp = 20.dp
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(boxSpacing),
        modifier = Modifier.padding(top = 32.dp)
    ) {
        repeat(count) {
            Box(
                modifier = Modifier
                    .size(boxSize)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF648DDB),
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
    }
}
