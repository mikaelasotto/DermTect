package com.example.dermtect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dermtect.R
import com.example.dermtect.poppinsFont
import com.example.dermtect.ui.theme.DermTectTheme

@Composable
fun LoggingInOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xD9353535)) // 85% opacity
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .size(width = 318.dp, height = 323.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 30.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Logging you in",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFont,
                    color = Color(0xFF0FB2B2),
                    textAlign = TextAlign.Center
                )

                Image(
                    painter = painterResource(id = R.drawable.loader),
                    contentDescription = "Loading...",
                    modifier = Modifier.size(188.dp)
                )

                Text(
                    text = "Please wait ...",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFont,
                    color = Color(0xFF0FB2B2),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoggingInOverlayPreview() {
    DermTectTheme {
        LoggingInOverlay()
    }
}
