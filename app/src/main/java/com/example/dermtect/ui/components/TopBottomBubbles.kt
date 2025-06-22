package com.example.dermtect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dermtect.R

@Composable
fun TopBottomBubbles() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bubbles_top),
            contentDescription = "Top Left Bubble",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-24).dp, y = (-24).dp)
                .size(200.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.bubbles_bottom),
            contentDescription = "Bottom Right Bubble",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (24).dp, y = (24).dp)
                .size(200.dp)
        )
    }
}
