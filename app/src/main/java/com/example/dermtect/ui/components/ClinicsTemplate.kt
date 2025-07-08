package com.example.dermtect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.BubblesBackground

@Composable
fun FindClinic1Screen(navController: NavController) {
    BubblesBackground {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                ) {
                    Text(
                        text = "Find Nearest Clinics",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .size(37.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .clickable { navController.popBackStack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "+ Add your Address",
                    fontSize = 14.sp,
                    color = Color(0xFF1D1D1D),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .size(width = 335.dp, height = 44.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF33E4DB), Color(0xFF00BBD3))
                            ),
                            shape = RoundedCornerShape(18.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Turn On Location or Manually Enter",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                Image(
                    painter = painterResource(id = R.drawable.map1),
                    contentDescription = "Map",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 335.dp, height = 248.dp)
                        .clip(RoundedCornerShape(11.dp))
                )

                Spacer(modifier = Modifier.height(40.dp))

                Divider(
                    color = Color(0xFF1D1D1D).copy(alpha = 0.3f),
                    thickness = 1.dp,
                    modifier = Modifier.width(335.dp)
                )

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier.width(335.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Saved Clinics",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.heart_icon),
                        contentDescription = "Saved",
                        tint = Color(0xFF0FB2B2),
                        modifier = Modifier.size(width = 22.dp, height = 22.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // MediCure clinic with navigation
                ClinicItem(
                    name = "MediCure Derma Clinic",
                    address = """
                        Address:
                        De Leo Rd. Dolores, San Fernando,
                        Pampanga

                        Attention Schedule:
                        7:15 AM - 6:30 PM
                    """.trimIndent(),
                    distance = "Distance: 2.3 Km",
                    onClick = {
                        navController.navigate("medicure")
                    }
                )

                Divider(
                    color = Color(0xFFE9F6FE),
                    thickness = 1.dp,
                    modifier = Modifier.width(348.dp)
                )

                // Vitality - non-clickable
                ClinicItem(
                    name = "Vitality Dermatology",
                    address = """
                        Address:
                        Kim-Cha Bldg., Cabambangan,
                        Bacolor, Pampanga

                        Attention Schedule:
                        9:00 AM - 8:30 PM
                    """.trimIndent(),
                    distance = "Distance: 3.5 Km",
                    onClick = {
                        navController.navigate("vitality")
                    }
                )

                Divider(
                    color = Color(0xFFE9F6FE),
                    thickness = 1.dp,
                    modifier = Modifier.width(348.dp)
                )

                // Skinhealth - non-clickable
                ClinicItem(
                    name = "Skinhealth Clinic",
                    address = """
                        Address:
                        778 Locust View Drive, Oakland, CA

                        Attention Schedule:
                        9:00 AM - 8:30 PM
                    """.trimIndent(),
                    distance = "Distance: 5.6 Km",
                    onClick = {
                        navController.navigate("skin_health")
                    }
                )
            }
        }
    }
}

@Composable
fun ClinicItem(
    name: String,
    address: String,
    distance: String,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .width(335.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.location_icon),
                contentDescription = "Location Icon",
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 6.dp)
            )
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(width = 94.3.dp, height = 14.58.dp)
                    .clip(RoundedCornerShape(6.5.dp))
                    .border(0.5.dp, Color.Black, shape = RoundedCornerShape(6.5.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = distance,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = address,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF484848)
        )
    }
}

@Composable
fun FindClinic2Screen(navController: NavController) {
    BubblesBackground {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                ) {
                    Text(
                        text = "Find Nearest Clinics",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .size(37.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .clickable { navController.popBackStack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Address",
                    fontSize = 14.sp,
                    color = Color(0xFF1D1D1D),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .size(width = 335.dp, height = 44.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF33E4DB), Color(0xFF00BBD3))
                            ),
                            shape = RoundedCornerShape(18.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "T+398 San Fernando, Pampanga, Philippines",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                Image(
                    painter = painterResource(id = R.drawable.map1),
                    contentDescription = "Map",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 335.dp, height = 248.dp)
                        .clip(RoundedCornerShape(11.dp))
                )

                Spacer(modifier = Modifier.height(40.dp))

                Divider(
                    color = Color(0xFF1D1D1D).copy(alpha = 0.3f),
                    thickness = 1.dp,
                    modifier = Modifier.width(335.dp)
                )

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier.width(335.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Nearby Clinics",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.heart_icon),
                        contentDescription = "Saved",
                        tint = Color(0xFF0FB2B2),
                        modifier = Modifier.size(width = 22.dp, height = 22.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // MediCure clinic with navigation
                ClinicItem(
                    name = "VW Dermatology",
                    address = """
                        Address:
                        Camansi St. Dolores, San Fernando,
                        Pampanga

                        Attention Schedule:
                        8 AM - 4 PM
                    """.trimIndent(),
                    distance = "Distance: 2.3 Km",
                    onClick = {
                        navController.navigate("vm")
                    }
                )

                Divider(
                    color = Color(0xFFE9F6FE),
                    thickness = 1.dp,
                    modifier = Modifier.width(348.dp)
                )

                // Vitality - non-clickable
                ClinicItem(
                    name = "Ortiz Skin Clinic",
                    address = """
                        Address:
                        San Juan, Mexico, Pampanga

                        Attention Schedule:
                        9:00 AM - 8:30 PM
                    """.trimIndent(),
                    distance = "Distance: 3.5 Km",
                    onClick = {
                        navController.navigate("ortiz")
                    }
                )

                Divider(
                    color = Color(0xFFE9F6FE),
                    thickness = 1.dp,
                    modifier = Modifier.width(348.dp)
                )

                // Skinhealth - non-clickable
                ClinicItem(
                    name = "Skin Benefit Dermatology",
                    address = """
                        Address:
                        Camp Olivas, San Fernando, Pampanga

                        Attention Schedule:
                        9:00 AM - 8:30 PM
                    """.trimIndent(),
                    distance = "Distance: 5.6 Km",
                    onClick = {
                        navController.navigate("skin_benefit")
                    }
                )
            }
        }
    }
}

@Composable
fun Clinic2Item(
    name: String,
    address: String,
    distance: String,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .width(335.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.location_icon),
                contentDescription = "Location Icon",
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 6.dp)
            )
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(width = 94.3.dp, height = 14.58.dp)
                    .clip(RoundedCornerShape(6.5.dp))
                    .border(0.5.dp, Color.Black, shape = RoundedCornerShape(6.5.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = distance,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = address,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF484848)
        )
    }
}
