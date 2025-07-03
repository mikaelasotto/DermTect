//package com.example.dermtect.ui.screens
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.SpanStyle
//import androidx.compose.ui.text.buildAnnotatedString
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.withStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.dermtect.R
//import com.example.dermtect.poppinsFont
//import com.example.dermtect.ui.components.TopBottomBubbles
//
//
//@Composable
//fun AssessmentReportScreen(
//    scanTitle: String = "Scan 1",
//    lesionImage: Painter,
//    assessmentResult: String = "Benign",
//    notes: String = "No serious risks found. The lesion looks non-cancerous. Monitor for changes, and consult a dermatologist if needed.",
//    onBack: () -> Unit,
//    onSendReport: () -> Unit,
//    onCancel: () -> Unit
//
//) {
//    Box(modifier = Modifier.fillMaxSize()) {
//        TopBottomBubbles()
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 25.dp)
//                .padding(top = 40.dp)
//        ) {
//            // Back & Title
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(75.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = scanTitle,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Medium,
//                    fontFamily = poppinsFont,
//                    color = Color(0xFF1D1D1D)
//                )
//
//                IconButton(
//                    onClick = onBack,
//                    modifier = Modifier
//                        .size(90.dp)
//                        .align(Alignment.CenterStart)
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.back),
//                        contentDescription = "Back",
//                        tint = Color.Unspecified,
//                        modifier = Modifier.size(75.dp)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            // Assessment Report title
//            Text(
//                text = "Assessment Report",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.SemiBold,
//                fontFamily = poppinsFont,
//                color = Color.Black,
//                modifier = Modifier.offset(x = 47.dp) // x:72 - padding:25
//            )
//
//            Spacer(modifier = Modifier.height(26.dp))
//
//            // Lesion Image
//            Image(
//                painter = lesionImage,
//                contentDescription = "Lesion Image",
//                modifier = Modifier
//                    .size(width = 336.dp, height = 187.dp)
//                    .align(Alignment.CenterHorizontally)
//                    .clip(RoundedCornerShape(15.dp))
//            )
//
//            Spacer(modifier = Modifier.height(39.dp))
//
//            // Assessment text
//            Text(
//                text = buildAnnotatedString {
//                    withStyle(style = androidx.compose.ui.text.SpanStyle(
//                        fontWeight = FontWeight.SemiBold
//                    )) {
//                        append("Assessment: ")
//                    }
//                    withStyle(style = androidx.compose.ui.text.SpanStyle(
//                        fontWeight = FontWeight.Normal
//                    )) {
//                        append(assessmentResult)
//                    }
//                },
//                fontSize = 20.sp,
//                fontFamily = poppinsFont,
//                color = Color.Black
//            )
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            // Notes text
//            Text(
//                text = buildAnnotatedString {
//                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
//                        append("Notes: ")
//                    }
//                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
//                        append(notes)
//                    }
//                },
//                fontSize = 20.sp,
//                fontFamily = poppinsFont,
//                color = Color.Black
//            )
//
//            Spacer(modifier = Modifier.height(60.dp))
//
//            var isReportSent by remember { mutableStateOf(false) }
//
//            if (!isReportSent) {
//                Button(
//                    onClick = { isReportSent = true },
//                    modifier = Modifier
//                        .width(325.dp)
//                        .height(60.dp)
//                        .align(Alignment.CenterHorizontally),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0FB2B2))
//                ) {
//                    Text(
//                        text = "Send Report to User",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Medium,
//                        fontFamily = poppinsFont,
//                        color = Color.White
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(25.dp))
//
//                OutlinedButton(
//                    onClick = { /* cancel logic */ },
//                    modifier = Modifier
//                        .width(325.dp)
//                        .height(60.dp)
//                        .align(Alignment.CenterHorizontally),
//                    border = BorderStroke(0.5.dp, Color(0xFF0FB2B2))
//                ) {
//                    Text(
//                        text = "Cancel",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Medium,
//                        fontFamily = poppinsFont,
//                        color = Color(0xFF0FB2B2)
//                    )
//                }
//            } else {
//                // Report has been sent
//                Button(
//                    onClick = { /* do nothing or show a message */ },
//                    enabled = false,
//                    modifier = Modifier
//                        .width(325.dp)
//                        .height(60.dp)
//                        .align(Alignment.CenterHorizontally),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF626262))
//                ) {
//                    Text(
//                        text = "Assessment Done",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Medium,
//                        fontFamily = poppinsFont,
//                        color = Color.White
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(25.dp))
//
//                OutlinedButton(
//                    onClick = { /* edit logic */ },
//                    modifier = Modifier
//                        .width(325.dp)
//                        .height(60.dp)
//                        .align(Alignment.CenterHorizontally),
//                    border = BorderStroke(0.5.dp, Color(0xFF0FB2B2))
//                ) {
//                    Text(
//                        text = "Edit Assessment",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Medium,
//                        fontFamily = poppinsFont,
//                        color = Color(0xFF0FB2B2)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun AssessmentReportScreenPreview() {
//    AssessmentReportScreen(
//        lesionImage = painterResource(id = R.drawable.lesion_photo),
//        onBack = {},
//        onSendReport = {},
//        onCancel = {}
//    )
//}