package com.example.dermtect.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.dermtect.R
import com.example.dermtect.poppinsFont
import com.example.dermtect.ui.components.TopBottomBubbles

@Composable
fun DoctorAssessmentScreen(
    scanTitle: String = "Scan 1",
    lesionImage: Painter,
    onBack: () -> Unit,
    onSubmit: (diagnosis: String, notes: String) -> Unit,
    onCancel: () -> Unit
) {
    var selectedDiagnosis by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        TopBottomBubbles()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 25.dp)
                .padding(top = 20.dp)
        ) {
            // Back & Title Row
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp), // Adjust height if needed
                contentAlignment = Alignment.Center
            ) {
                // Title in center
                Text(
                    text = scanTitle,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFont,
                    color = Color(0xFF1D1D1D)
                )

                // Back icon on the left
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(90.dp) // Container size (touch target)
                        .align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(75.dp) // Image size itself
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Lesion Image
            Image(
                painter = lesionImage,
                contentDescription = "Lesion Photo",
                modifier = Modifier
                    .size(width = 319.dp, height = 177.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(15.dp))
            )

            Spacer(modifier = Modifier.height(27.dp))

            // Context Card
            Box(
                modifier = Modifier
                    .size(width = 325.dp, height = 61.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFD3FFFD), RoundedCornerShape(10.dp))
                    .padding(start = 18.dp, top = 10.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.context_icon), // Replace with your icon
                            contentDescription = "Context Icon",
                            modifier = Modifier.size(16.67.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Additional Context",
                            fontSize = 11.sp,
                            fontFamily = poppinsFont,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "View Risk Assessment & Report",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppinsFont,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Assessment",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFont,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(19.dp))

            // Diagnosis Buttons
            Row(horizontalArrangement = Arrangement.spacedBy(21.dp)) {
                Button(
                    onClick = { selectedDiagnosis = "Benign" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedDiagnosis == "Benign") Color(0xFF0FB2B2) else Color(0xFFF0F0F0),
                        contentColor = if (selectedDiagnosis == "Benign") Color.White else Color.Black
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Benign", fontFamily = poppinsFont)
                }

                Button(
                    onClick = { selectedDiagnosis = "Malignant" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedDiagnosis == "Malignant") Color(0xFF0FB2B2) else Color(0xFFF0F0F0),
                        contentColor = if (selectedDiagnosis == "Malignant") Color.White else Color.Black
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Malignant", fontFamily = poppinsFont)
                }
            }

            Spacer(modifier = Modifier.height(19.dp))

            Text(
                text = "Notes",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFont,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(19.dp))

            // Notes TextField
            TextField(
                value = notes,
                onValueChange = { notes = it },
                placeholder = { Text("Write your notes here", fontFamily = poppinsFont) },
                modifier = Modifier
                    .size(width = 311.dp, height = 92.dp)
                    .background(Color(0xFFF0F0F0), RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF0F0F0),
                    unfocusedContainerColor = Color(0xFFF0F0F0),
                    disabledContainerColor = Color(0xFFF0F0F0),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(34.dp))

            // Submit Button
            Button(
                onClick = { onSubmit(selectedDiagnosis, notes) },
                modifier = Modifier
                    .width(325.dp)
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0FB2B2))
            ) {
                Text(
                    text = "Submit Assessment",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFont,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            // Cancel Button
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier
                    .width(325.dp)
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                border = BorderStroke(0.5.dp, Color(0xFF0FB2B2))
            ) {
                Text(
                    text = "Cancel",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFont,
                    color = Color(0xFF0FB2B2)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DoctorAssessmentScreenPreview() {
    DoctorAssessmentScreen(
        scanTitle = "Scan 1",
        lesionImage = painterResource(id = R.drawable.lesion_photo),
        onBack = {},
        onSubmit = { _, _ -> },
        onCancel = {}
    )
}
