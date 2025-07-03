package com.example.dermtect.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.ui.components.BubblesBackground
import com.example.dermtect.ui.components.BackButton
import com.example.dermtect.R
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun LesionResultTemplate(
    imageResId: Int,
    title: String,
    timestamp: String,
    riskTitle: String,
    riskDescription: String,
    showActions: Boolean = true,
    onBackClick: () -> Unit,
    onDownloadClick: () -> Unit,
    onFindClinicClick: () -> Unit,
    onSaveResultClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    BubblesBackground {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 10.dp)
        ) {
            BackButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 23.dp)
            )

            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.Center) // This ensures the title is centered in the Box
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
            ) {
                Text(
                    text = "$timestamp",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 140.dp, start = 20.dp, end = 20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Image
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Lesion Result",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight()
                ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(riskTitle)
                            }
                            append(riskDescription)
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "You can also",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Action Cards
                ResultActionCard(
                    text = "Download Full PDF Report\n& Risk Assessment Questionnaires",
                    backgroundColor = Color(0xFFBAFFFF),
                    imageResId = R.drawable.risk_image,
                    onClick = onDownloadClick
                )

                Spacer(modifier = Modifier.height(16.dp))

                ResultActionCard(
                    text = "Find Nearby Derma Clinics \nNear You",
                    backgroundColor = Color(0xFFBAFFD7),
                    imageResId = R.drawable.nearby_clinics,
                    onClick = onFindClinicClick
                )

                Spacer(modifier = Modifier.height(20.dp))

                if (showActions) {
                    Button(
                        onClick = onSaveResultClick,
                        modifier = Modifier
                            .fillMaxWidth(0.9f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0FB2B2))
                    ) {
                        Text(
                            "Save Result",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal)
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    OutlinedButton(
                        onClick = onCancelClick,
                        modifier = Modifier
                            .fillMaxWidth(0.9f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF0FB2B2)),
                        border = BorderStroke(1.dp, Color(0xFF0FB2B2))
                    ) {
                        Text("Cancel", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal))
                    }
                }
            }

            }
        }




@Composable
fun ResultActionCard(
    text: String,
    backgroundColor: Color,
    imageResId: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.fillMaxWidth()
                )
            }


            Spacer(modifier = Modifier.width(10.dp))

            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Skin Check Icon",
                modifier = Modifier.size(70.dp)
            )
        }
    }
}

@Composable
fun SampleResultScreen(navController: NavController) {
    val formatter = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    val timestamp = formatter.format(Date())

    LesionResultTemplate(
        imageResId = R.drawable.sample_skin, // Replace with your actual image
        title = "Results",
        timestamp = timestamp,
        riskTitle = "Risk assessment:",
        riskDescription = "No serious risks detected. The lesion appears non-cancerous. Please monitor it for any changes in size, shape, or color. For your peace of mind, feel free to consult a dermatologist.",
        showActions = true,
        onBackClick = { navController.popBackStack() },
        onDownloadClick = { /* TODO: Download PDF */ },
        onFindClinicClick = { /* TODO: Navigate to clinics */ },
        onSaveResultClick = { /* TODO: Save result */ },
        onCancelClick =  { /* TODO: Save result */ },
    )
}


@Preview(showBackground = true)
@Composable
fun SampleResultScreenPreview() {
    SampleResultScreen(navController = rememberNavController())
}
