package com.example.dermtect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.dermtect.ui.theme.DermTectTheme
import kotlinx.coroutines.delay


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
                    composable("choose_account") { ChooseAccount(navController) }
                    composable("login") { Login(navController) }
                    composable("register") { Register(navController) }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
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
                onClick = { navController.navigate("choose_account") },
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



//@Composable
//fun InputField(iconRes: Int, label: String) {
//    Box(
//        modifier = Modifier
//            .width(299.dp)
//            .height(52.dp)
//            .background(Color(0xFFF4F3F3), shape = RoundedCornerShape(12.dp))
//            .padding(start = 16.dp),
//        contentAlignment = Alignment.CenterStart
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(id = iconRes),
//                contentDescription = null,
//                modifier = Modifier
//                    .width(21.21.dp)
//                    .height(16.dp)
//            )
//
//            Spacer(modifier = Modifier.width(16.59.dp))
//
//            Text(
//                text = label,
//                fontSize = 16.sp,
//                fontFamily = poppinsFont,
//                color = Color(0xFF1D1D1D)
//            )
//        }
//    }
//}


@Composable
fun Login(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top-left bubble
        Image(
            painter = painterResource(id = R.drawable.bubbles_top),
            contentDescription = "Top Left Bubble",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-24).dp, y = (-24).dp)
                .size(200.dp)
        )

        // Bottom-right bubble
        Image(
            painter = painterResource(id = R.drawable.bubbles_bottom),
            contentDescription = "Bottom Right Bubble",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (24).dp, y = (24).dp)
                .size(200.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center), // 💡 centers entire column in the screen
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Title
            Text(
                text = "Login",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D)
            )

            Spacer(modifier = Modifier.height(32.dp))

//            // Email Field
//            InputField(
//                iconRes = R.drawable.icon_email, // put email icon in drawable
//                label = "Email"
//            )

            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email", fontFamily = poppinsFont) },
                modifier = Modifier
                    .width(299.dp)
                    .height(52.dp)
                    .background(Color(0xFFF4F3F3), RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Password Field
//            InputField(
//                iconRes = R.drawable.icon_pass, // put password icon in drawable
//                label = "Password"
//            )

            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password", fontFamily = poppinsFont) },
                modifier = Modifier
                    .width(299.dp)
                    .height(52.dp)
                    .background(Color(0xFFF4F3F3), RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .width(299.dp)
                    .padding(top = 8.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Forgot Password?",
                    fontSize = 12.sp,
                    fontFamily = poppinsFont,
                    color = Color(0xFF1D1D1D)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Login Button
            Button(
                onClick = { /* navController.navigate("home") */ },
                modifier = Modifier
                    .width(299.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0FB2B2),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Login",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFont
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Other",
                fontSize = 12.sp,
                fontFamily = poppinsFont,
                color = Color(0xFF828282)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(id = R.drawable.google_icon), // replace with your 36x36 icon
                contentDescription = "Google Login",
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text(
                    text = "Don't have an account? ",
                    fontSize = 12.sp,
                    fontFamily = poppinsFont,
                    color = Color(0xFF1D1D1D)
                )
                Text(
                    text = "Register",
                    fontSize = 12.sp,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF2FD8D8),
                    modifier = Modifier.clickable {
                        navController.navigate("register")
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Login() {
    DermTectTheme {
        // Safe preview with empty lambda
        Login(navController = rememberNavController())
    }
}

@Composable
fun Register(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpass by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top-left bubble
        Image(
            painter = painterResource(id = R.drawable.bubbles_top),
            contentDescription = "Top Left Bubble",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-24).dp, y = (-24).dp)
                .size(200.dp)
        )

        // Bottom-right bubble
        Image(
            painter = painterResource(id = R.drawable.bubbles_bottom),
            contentDescription = "Bottom Right Bubble",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (24).dp, y = (24).dp)
                .size(200.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Title
            Text(
                text = "Sign Up",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFont,
                color = Color(0xFF1D1D1D)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email", fontFamily = poppinsFont) },
                modifier = Modifier
                    .width(299.dp)
                    .height(52.dp)
                    .background(Color(0xFFF4F3F3), RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Password
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password", fontFamily = poppinsFont) },
                modifier = Modifier
                    .width(299.dp)
                    .height(52.dp)
                    .background(Color(0xFFF4F3F3), RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Confirm Password
            TextField(
                value = confirmpass,
                onValueChange = { confirmpass = it },
                placeholder = { Text("Password", fontFamily = poppinsFont) },
                modifier = Modifier
                    .width(299.dp)
                    .height(52.dp)
                    .background(Color(0xFFF4F3F3), RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { /* navController.navigate("home") */ },
                modifier = Modifier
                    .width(299.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0FB2B2),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Register",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFont
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Other",
                fontSize = 12.sp,
                fontFamily = poppinsFont,
                color = Color(0xFF828282)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = "Google Login",
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text(
                    text = "Already have an account? ",
                    fontSize = 12.sp,
                    fontFamily = poppinsFont,
                    color = Color(0xFF1D1D1D)
                )
                Text(
                    text = "Login",
                    fontSize = 12.sp,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF2FD8D8),
                    modifier = Modifier.clickable {
                        navController.navigate("login")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Register() {
    DermTectTheme {
        // Safe preview with empty lambda
        Register(navController = rememberNavController())
    }
}


