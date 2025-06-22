package com.example.dermtect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.dermtect.poppinsFont
import com.example.dermtect.ui.theme.DermTectTheme
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size


@Composable
fun OnboardingScreen(
    imageRes: Int,
    title: String,
    description: String,
    buttonText: String = "Next",
    onNextClick: () -> Unit
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageRes)
                    .build(),
                imageLoader = imageLoader,
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp)
                    .padding(bottom = 58.dp)
            )

            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = description,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(148.dp))

            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .width(320.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0FB2B2),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = buttonText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFont
                )
            }
        }
    }
}
