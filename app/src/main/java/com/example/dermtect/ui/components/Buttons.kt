package com.example.dermtect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dermtect.R // Make sure ic_back is in your drawable

@Composable
fun TopRightNotificationIcon(
    onNotifClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(50.dp)
            .background(
                color = Color(0xFFCDFFFF),
                shape = CircleShape
            )
            .clickable { onNotifClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.notifications_vector),
            contentDescription = "Notifications",
            modifier = Modifier.size(28.dp)
        )
    }
}
@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    Box(
        modifier = modifier

            .size(50.dp)
            .shadow(
                elevation = 6.dp,
                shape = CircleShape
            )
            .background(
                color = Color.White,
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back), // Your back icon
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(28.dp)
        )
    }
}


