package com.example.dermtect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dermtect.poppinsFont

@Composable
fun AccountTypeButton(
    title: String,
    subtitle: String,
    imageResId: Int,
    onClick: () -> Unit,
    containerColor: Color = Color(0xFFC5FFFF)
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(360.dp)
            .height(182.dp)
            .padding(horizontal = 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = Color.Black
        ),
        contentPadding = PaddingValues(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsFont,
                    color = Color(0xFF1D1D1D)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFont,
                    color = Color(0xFF1D1D1D)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Account Type Icon",
                modifier = Modifier.size(100.dp)
            )
        }
    }
}
