package com.example.dermtect.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import com.example.dermtect.poppinsFont

@Composable
fun ScreenTitle(
    title: String,
    subtitle: String? = null // optional subtitle
) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = poppinsFont,
        color = Color(0xFF1D1D1D),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, bottom = 8.dp)
    )

    subtitle?.let {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = it,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = poppinsFont,
            color = Color(0xFF989898),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, bottom = 8.dp)
        )
    }
}
