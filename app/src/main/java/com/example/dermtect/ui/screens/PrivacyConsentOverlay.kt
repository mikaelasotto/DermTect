package com.example.dermtect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dermtect.poppinsFont
import com.example.dermtect.ui.theme.DermTectTheme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun PrivacyConsentOverlay() {
    var isChecked by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val fullText = "DermTect uses cookies and similar technologies to enhance your experience, analyze platform usage, and deliver content tailored to your needs. These tools help us improve our services while ensuring your data remains secure and protected. Granting permission does not give us access to your personal information from other apps or websites."
    val shortText = fullText.take(150) + "..."

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xD9353535)), // 85% opacity
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(367.dp)
                .heightIn(min = 450.dp)
                .background(Color.White, shape = RoundedCornerShape(25.dp))
                .border(0.5.dp, Color(0xFF0FB2B2), shape = RoundedCornerShape(25.dp))
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {

            // Title
            Text(
                text = "We Care About Your Privacy",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFont,
                color = Color(0xFF0FB2B2),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Expandable Subtext
            Column {
                Text(
                    text = if (expanded) fullText else shortText,
                    fontSize = 14.sp,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF1D1D1D)
                )

                Text(
                    text = if (expanded) "See less" else "See more...",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFont,
                    color = Color(0xFF0FB2B2),
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .clickable { expanded = !expanded }
                )
            }

            Spacer(modifier = Modifier.height(23.dp))

            // Checkbox Row
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                        .background(
                            if (isChecked) Color(0xFF0FB2B2) else Color.Transparent,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .clickable { isChecked = !isChecked },
                    contentAlignment = Alignment.Center
                ) {
                    if (isChecked) {
                        Text(
                            text = "✓",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "I understand that my skin photos, reports, and details may be securely stored to support analysis, track results, and enable dermatologist feedback.",
                    fontSize = 10.sp,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF1D1D1D),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(23.dp))

            // Consent Button
            Button(
                onClick = { /* handle consent */ },
                enabled = isChecked,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isChecked) Color(0xFF0FB2B2) else Color(0xFF626262),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Consent", fontFamily = poppinsFont, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Do Not Consent Button
            OutlinedButton(
                onClick = { /* handle no consent */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                border = BorderStroke(0.5.dp, Color(0xFF0FB2B2)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF0FB2B2),
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Do Not Consent", fontFamily = poppinsFont, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(8.dp)) // Ensure no clipping
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrivacyConsentOverlayPreview() {
    DermTectTheme {
        PrivacyConsentOverlay()
    }
}
