package com.example.dermtect.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import com.example.dermtect.poppinsFont

@Composable
fun SubText(
    text: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(start = 32.dp, bottom = 8.dp),
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = poppinsFont,
        color = Color(0xFF989898),
        modifier = modifier,
        textAlign = textAlign
    )
}
