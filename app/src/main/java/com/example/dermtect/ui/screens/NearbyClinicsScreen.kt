package com.example.dermtect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dermtect.R
import com.example.dermtect.ui.components.BubblesBackground

@Composable
fun VMClinicScreen(navController: NavController) {
    BubblesBackground {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                ) {
                    Text(
                        text = "Saved Clinics",
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

                Spacer(modifier = Modifier.height(30.dp))

                // Clinic Card (no background)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 24.dp)
                ) {
                    // Clinic Name and Heart Icon
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Medicure Derma Clinic",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.empty_heart_icon),
                            contentDescription = "Favorite",
                            tint = Color.Cyan,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Subtitle
                    Text(
                        text = "Confidence Begins with Clear Skin.",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Description
                    Text(
                        text = "Founded by board-certified dermatologist," +
                                "Dr. Joshua Gamboa Ramirez.",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Address
                    InlineLabelText(
                        label = "Address: ",
                        value = "De Leo Rd. Dolores, San Fernando, Pampanga"
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Contact Number
                    InlineLabelText(
                        label = "Contact Number: ",
                        value = "09206257438"
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Consultation Schedule
                    InlineLabelText(
                        label = "Consultation Schedule: ",
                        value = "8 AM - 4 PM" +
                                "Monday to Saturday"
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // Divider
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color(0xFFE9F6FE))
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // Map
                    Image(
                        painter = painterResource(id = R.drawable.clinic_map),
                        contentDescription = "Clinic Location",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(279.dp)
                            .clip(RoundedCornerShape(11.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun VMInlineLabelText(label: String, value: String) {
    Text(
        text = buildAnnotatedString {
            append(
                AnnotatedString(
                    label,
                    spanStyle = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
            )
            append(
                AnnotatedString(
                    value,
                    spanStyle = SpanStyle(fontWeight = FontWeight.Normal)
                )
            )
        },
        fontSize = 13.sp,
        color = Color.Black
    )
}


//Vitality Clinic
@Composable
fun OrtizClinicScreen(navController: NavController) {
    BubblesBackground {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                ) {
                    Text(
                        text = "Saved Clinics",
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

                Spacer(modifier = Modifier.height(30.dp))

                // Clinic Card (no background)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 24.dp)
                ) {
                    // Clinic Name and Heart Icon
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Vitality Dermatology",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.empty_heart_icon),
                            contentDescription = "Favorite",
                            tint = Color.Cyan,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Subtitle
                    Text(
                        text = "Your Skin, Our Specialty.",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Description
                    Text(
                        text = "Founded by board-certified dermatologist," +
                                "Dr. Yvonne Tiongco Reyes.",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Address
                    InlineLabelText(
                        label = "Address: ",
                        value = "Kim-Cha Bldg., Cabambangan, Bacolor, Pampanga"
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Contact Number
                    InlineLabelText(
                        label = "Contact Number: ",
                        value = "09206257438"
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Consultation Schedule
                    InlineLabelText(
                        label = "Consultation Schedule: ",
                        value = "8 AM - 4 PM" +
                                "Monday to Saturday"
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // Divider
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color(0xFFE9F6FE))
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // Map
                    Image(
                        painter = painterResource(id = R.drawable.clinic_map),
                        contentDescription = "Clinic Location",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(279.dp)
                            .clip(RoundedCornerShape(11.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun OrtizInlineLabelText(label: String, value: String) {
    Text(
        text = buildAnnotatedString {
            append(
                AnnotatedString(
                    label,
                    spanStyle = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
            )
            append(
                AnnotatedString(
                    value,
                    spanStyle = SpanStyle(fontWeight = FontWeight.Normal)
                )
            )
        },
        fontSize = 13.sp,
        color = Color.Black
    )
}

//SkinHealth Clinic
@Composable
fun SkinBenefitClinicScreen(navController: NavController) {
    BubblesBackground {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                ) {
                    Text(
                        text = "Saved Clinics",
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

                Spacer(modifier = Modifier.height(30.dp))

                // Clinic Card (no background)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 24.dp)
                ) {
                    // Clinic Name and Heart Icon
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "SkinHealth Clinic",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.empty_heart_icon),
                            contentDescription = "Favorite",
                            tint = Color.Cyan,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Subtitle
                    Text(
                        text = "Where every skin story matters.",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Description
                    Text(
                        text = "Founded by board-certified dermatologist," +
                                "Dr. Ailynne Marie Vergara-Wijangco.",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Address
                    InlineLabelText(
                        label = "Address: ",
                        value = "Macabakle, San Fernando, 2000 Pampanga"
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Contact Number
                    InlineLabelText(
                        label = "Contact Number: ",
                        value = "09206257438"
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Consultation Schedule
                    InlineLabelText(
                        label = "Consultation Schedule: ",
                        value = "8 AM - 4 PM" +
                                "Monday to Saturday"
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // Divider
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color(0xFFE9F6FE))
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // Map
                    Image(
                        painter = painterResource(id = R.drawable.clinic_map),
                        contentDescription = "Clinic Location",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(279.dp)
                            .clip(RoundedCornerShape(11.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun SkinBenefitInlineLabelText(label: String, value: String) {
    Text(
        text = buildAnnotatedString {
            append(
                AnnotatedString(
                    label,
                    spanStyle = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
            )
            append(
                AnnotatedString(
                    value,
                    spanStyle = SpanStyle(fontWeight = FontWeight.Normal)
                )
            )
        },
        fontSize = 13.sp,
        color = Color.Black
    )
}

