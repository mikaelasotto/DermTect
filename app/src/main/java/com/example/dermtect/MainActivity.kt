package com.example.dermtect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dermtect.ui.theme.DermTectTheme
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.res.fontResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import coil.ImageLoader
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.dermtect.R



val poppinsFont = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold)
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DermTectTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") { SplashScreen(navController) }
                    composable("onboarding_screen1") { OnboardingScreen1(navController) }
                    composable("onboarding_screen2") { OnboardingScreen2(navController) }
                    composable("onboarding_screen3") { OnboardingScreen3(navController) }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(true) {
        delay(2000) // 2 seconds
        navController.navigate("onboarding_screen1"){
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0FB2B2)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "DermTect",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFont,
            color = Color.White
        )
    }
}

@Preview(name = "Splash Screen", showBackground = true)
@Composable
fun SplashScreenPreview() {
    DermTectTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0FB2B2)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "DermTect",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFont,
                color = Color.White
            )
        }
    }
}


@Composable
fun GifImage_Boarding1() {
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(R.drawable.welcome) // Make sure welcome.gif exists
            .build(),
        imageLoader = imageLoader,
        contentDescription = "DermTect Animation",
        modifier = Modifier
            .size(280.dp)
            .padding(bottom = 58.dp)
    )
}


@Composable
fun OnboardingScreen1(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 32.dp) // optional for better spacing
        ) {

            GifImage_Boarding1()

            Text(
                text = "Welcome to DermTect!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D)
                )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Your AI-powered companion for\nearly skin health checks.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(148.dp))

            Button(
                onClick = { navController.navigate("onboarding_screen2") },
                modifier = Modifier
                    .width(320.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0FB2B2), // Background color
                    contentColor = Color.White         // Text color
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFont
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen1Preview() {
    DermTectTheme {
        // Safe preview with empty lambda
        OnboardingScreen1(navController = rememberNavController())
    }
}



@Composable
fun GifImage_Boarding2() {
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(R.drawable.scan)
            .build(),
        imageLoader = imageLoader,
        contentDescription = "DermTect Animation",
        modifier = Modifier
            .size(280.dp)
            .padding(bottom = 58.dp)
    )
}


@Composable
fun OnboardingScreen2(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 32.dp) // optional for better spacing
        ) {

            GifImage_Boarding2()

            Text(
                text = "Scan. Assess. Find Help",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Snap a photo, answer a few\nquestions, and get instant results.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(148.dp))

            Button(
                onClick = { navController.navigate("onboarding_screen3") },
                modifier = Modifier
                    .width(320.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0FB2B2), // Background color
                    contentColor = Color.White         // Text color
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFont
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen2Preview() {
    DermTectTheme {
        // Safe preview with empty lambda
        OnboardingScreen2(navController = rememberNavController())
    }
}



@Composable
fun GifImage_Boarding3() {
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(R.drawable.skin_health)
            .build(),
        imageLoader = imageLoader,
        contentDescription = "DermTect Animation",
        modifier = Modifier
            .size(280.dp)
            .padding(bottom = 58.dp)
    )
}


@Composable
fun OnboardingScreen3(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 32.dp) // optional for better spacing
        ) {

            GifImage_Boarding3()

            Text(
                text = "Your Skin Health,\nJust a Tap Away",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Fast, simple, and secure skin\nassessments anytime.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(148.dp))

            Button(
                onClick = { /* navController.navigate("login") */ },
                modifier = Modifier
                    .width(320.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0FB2B2), // Background color
                    contentColor = Color.White         // Text color
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFont
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen3Preview() {
    DermTectTheme {
        // Safe preview with empty lambda
        OnboardingScreen3(navController = rememberNavController())
    }
}


@Composable
fun AccountTypeButton(
    title: String,
    subtitle: String,
    imageResId: Int,
    onClick: () -> Unit,
    containerColor: Color = Color(0xFFC5FFFF) // default to light blue
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
        shape = RoundedCornerShape(16.dp) // reduce if you want less roundness
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


@Composable
fun ChooseAccount(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        // Top-left bubble
        Image(
            painter = painterResource(id = R.drawable.bubbles_top),
            contentDescription = "Top Left Bubble",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-24).dp, y = (-24).dp) // move up and left slightly
                .size(200.dp) // adjust size as needed
        )

        // Bottom-right bubble
        Image(
            painter = painterResource(id = R.drawable.bubbles_bottom),
            contentDescription = "Bottom Right Bubble",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (24).dp, y = (24).dp) // move down and right slightly
                .size(200.dp) // adjust size as needed
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 32.dp) // optional for better spacing
        ) {

            Text(
                text = "Type of Account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Choose type of your account",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(56.dp))

            AccountTypeButton(
                title = "Regular User",
                subtitle = "Scan and check your skin lesions, locate nearby clinics, and download a personal report",
                imageResId = R.drawable.regular_user,
                onClick = { navController.navigate("login") }
            )

            Spacer(modifier = Modifier.height(24.dp))

            AccountTypeButton(
                title = "Doctor",
                subtitle = "Access patient reports, view skin cases, and provide diagnostic insights.",
                imageResId = R.drawable.doctor,
                onClick = { navController.navigate("login_professional") },
                containerColor = Color(0xFFFFE3CD)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChooseAccountPreview() {
    DermTectTheme {
        // Safe preview with empty lambda
        ChooseAccount(navController = rememberNavController())
    }
}


