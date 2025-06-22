package com.example.dermtect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dermtect.poppinsFont



@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    iconRes: Int
) {
    Box(
        modifier = Modifier
            .width(299.dp)
            .height(52.dp)
            .background(Color(0xFFF4F3F3), shape = RoundedCornerShape(12.dp))
            .padding(start = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier
                    .width(21.21.dp)
                    .height(16.dp)
            )
            Spacer(modifier = Modifier.width(16.59.dp))
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = placeholder,
                        fontFamily = poppinsFont,
                        color = Color(0xFF1D1D1D)
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF4F3F3),
                    focusedContainerColor = Color(0xFFF4F3F3),
                    disabledContainerColor = Color(0xFFF4F3F3),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

