package com.example.dermtect.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dermtect.ui.components.BackButton
import com.example.dermtect.ui.components.BubblesBackground
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Row
import com.example.dermtect.ui.components.DialogTemplate
import com.example.dermtect.R

@Composable
fun QuestionnaireScreen(navController: NavController) {
    BubblesBackground {
        var answers by remember {
            mutableStateOf(List<Boolean?>(8) { null }) // null: unanswered, true: yes, false: no
        }
        var showWarning by remember { mutableStateOf(false) }
        var showDialog by remember { mutableStateOf(false) }

        BackButton(
            onClick = {navController.popBackStack() },
            modifier = Modifier
                .offset(x = 25.dp, y = 50.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Column(
                modifier = Modifier
                    .fillMaxHeight(0.85f)
                    .padding(top = 25.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text(
                text = "Skin Check Questionnaire",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Before we scan your skin, please answer a few short questions for additional context.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(0.8f),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                val questions = listOf(
                    "Have you noticed this skin spot recently appearing or changing in size?",
                    "Does the lesion have uneven or irregular borders?",
                    "Is the color of the spot unusual (black, blue, red, or a mix of colors)?",
                    "Has the lesion been bleeding, itching, or scabbing recently?",
                    "Have you noticed this skin spot recently appearing or changing in size?",
                    "Does the lesion have uneven or irregular borders?",
                    "Is the color of the spot unusual (black, blue, red, or a mix of colors)?",
                    "Has the lesion been bleeding, itching, or scabbing recently?"
                )

                itemsIndexed(questions) { index, question ->
                    Column {
                        Text(
                            text = "${index + 1}. $question",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = answers[index] == true,
                                onClick = {
                                    answers = answers.toMutableList().apply { this[index] = true }
                                }
                            )
                            Text("YES")

                            RadioButton(
                                selected = answers[index] == false,
                                onClick = {
                                    answers = answers.toMutableList().apply { this[index] = false }
                                }
                            )
                            Text("NO")
                        }
                        if (index != questions.lastIndex) {
                            HorizontalDivider(modifier = Modifier.padding(top = 15.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

                if (showWarning) {
                    Text(
                        text = "⚠ Please answer all questions before submitting.",
                        color = Color(0xFFa90505),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Button(
                    onClick = {
                        if (answers.any { it == null }) {
                            showWarning = true
                        } else {
                            showWarning = false
                            navController.navigate("user_home") // ✅ Correct navigation
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .wrapContentHeight(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0FB2B2),
                        contentColor = Color.White
                    )
                ) {
                    Text("Submit", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal))
                }

                Spacer(modifier = Modifier.height(8.dp))
                if (showDialog) {
                    DialogTemplate(
                        show = true,
                        title = "Saved!",
                        imageResId = R.drawable.pdf_download,
                        autoDismiss = true,
                        onDismiss = { showDialog = false }
                    )
                }

                OutlinedButton(
                    onClick = {showDialog = true },
                            modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .wrapContentHeight(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF0FB2B2)),
                    border = BorderStroke(1.dp, Color(0xFF0FB2B2))
                ) {
                    Text("Skip", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal))
                }


            }
    }}
}

@Preview(showBackground = true)
@Composable
fun QuestionnaireScreenPreview() {
    QuestionnaireScreen(navController = rememberNavController())
}
