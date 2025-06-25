package com.example.dermtect.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dermtect.poppinsFont

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true // ✅ support 'enabled'
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(299.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) Color(0xFF0FB2B2) else Color(0xFFBDBDBD),
            contentColor = Color.White
        ),
        enabled = enabled // ✅ built-in control
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFont
        )
    }
}

