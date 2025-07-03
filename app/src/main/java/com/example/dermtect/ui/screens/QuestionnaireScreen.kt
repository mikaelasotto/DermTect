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
import com.example.dermtect.ui.viewmodel.QuestionnaireViewModel
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun QuestionnaireScreen(navController: NavController) {
    val questionnaireViewModel = remember { QuestionnaireViewModel() }
    val loading by questionnaireViewModel.loading.collectAsState()
    val saveSuccess by questionnaireViewModel.saveSuccess.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    val answers = remember { mutableStateListOf<Boolean?>().apply { repeat(8) { add(null) } } }

    var showWarning by remember { mutableStateOf(false) }
    var showBackDialog by remember { mutableStateOf(false) }
    var showCancelDialog by remember { mutableStateOf(false) }

    val existingAnswers by questionnaireViewModel.existingAnswers.collectAsState()
    var isEditMode by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        questionnaireViewModel.loadQuestionnaireAnswers()
        isEditMode = false // reset edit mode on screen entry
    }
    LaunchedEffect(existingAnswers) {
        if (existingAnswers == null) {
            isEditMode = true
        } else {
            isEditMode = false
            answers.clear()
            answers.addAll(existingAnswers!!)
        }
    }

    if (existingAnswers != null && !isEditMode) {
        answers.clear()
        answers.addAll(existingAnswers!!)
    }


    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            snackbarHostState.showSnackbar("Answers saved successfully!")
            questionnaireViewModel.resetSuccessFlag()
        }
    }
    LaunchedEffect(existingAnswers) {
        if (existingAnswers != null && !isEditMode) {
            answers.clear()
            answers.addAll(existingAnswers!!)
        }
    }


    BubblesBackground {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState) { data ->
                    Snackbar(
                        snackbarData = data,
                        containerColor = Color(0xFF0FB2B2),
                        contentColor = Color.White,
                        shape = RoundedCornerShape(8.dp),
                        actionColor = Color.White,
                        modifier = Modifier .fillMaxWidth(0.7f)
                    )
                }
            },
            containerColor = Color.Transparent

        ) { innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {

                BackButton(
                    onClick = {
                        if (isEditMode || existingAnswers == null) {
                            showBackDialog = true
                        } else {
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .offset(x = 25.dp, y = 50.dp)
                )


                BackHandler {
                    if (isEditMode || existingAnswers == null) {
                        showBackDialog = true
                    } else {
                        navController.popBackStack()
                    }
                }


                Column(
                    modifier = Modifier
                        .padding(innerPadding)
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
                            verticalArrangement = Arrangement.spacedBy(24.dp),
                            contentPadding = PaddingValues(bottom = 10.dp)

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
                                            onClick = if (isEditMode) {
                                                { answers[index] = true }
                                            } else null,
                                            enabled = isEditMode
                                        )
                                        Text("YES")
                                        Spacer(modifier = Modifier.width(10.dp))
                                        RadioButton(
                                            selected = answers[index] == false,
                                            onClick = if (isEditMode) {
                                                { answers[index] = false }
                                            } else null,
                                            enabled = isEditMode
                                        )
                                        Text("NO")

                                    }
                                    if (index != questions.lastIndex) {
                                        HorizontalDivider(modifier = Modifier.padding(top = 10.dp))
                                    }
                                }

                            }

                        }



                        // Below the RadioButton list

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

                        when {
                            // 🔹 1. First-time user (no existing answers)
                            existingAnswers == null -> {
                                Button(
                                    onClick = {
                                        val allAnswered = answers.all { it != null }
                                        showWarning = !allAnswered
                                        if (allAnswered) {
                                            questionnaireViewModel.saveQuestionnaireAnswers(
                                                answers = answers,
                                                onSuccess = {
                                                    isEditMode = false
                                                    questionnaireViewModel.loadQuestionnaireAnswers() // 🔁 Reload the saved data
                                                },
                                                        onError = { showWarning = true }
                                            )
                                        }
                                    },
                                    enabled = !loading,
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .wrapContentHeight(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF0FB2B2),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(if (loading) "Submitting..." else "Submit")
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                OutlinedButton(
                                    onClick = { navController.navigate("user_home") },
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .wrapContentHeight(),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF0FB2B2)),
                                    border = BorderStroke(1.dp, Color(0xFF0FB2B2))
                                ) {
                                    Text("Skip")
                                }
                            }

                            // 🔹 2. Edit mode
                            isEditMode -> {
                                Button(
                                    onClick = {
                                        val allAnswered = answers.all { it != null }
                                        showWarning = !allAnswered
                                        if (allAnswered) {
                                            questionnaireViewModel.saveQuestionnaireAnswers(
                                                answers = answers,
                                                onSuccess = { isEditMode = false },
                                                onError = { showWarning = true }
                                            )
                                        }
                                    },
                                    enabled = !loading,
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .wrapContentHeight(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF0FB2B2),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(if (loading) "Submitting..." else "Submit")
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                OutlinedButton(
                                    onClick = {
                                        showCancelDialog = true
                                                                            },
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .wrapContentHeight(),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF0FB2B2)),
                                    border = BorderStroke(1.dp, Color(0xFF0FB2B2))
                                ) {
                                    Text("Cancel")
                                }
                            }

                            // 🔹 3. Read mode with existing answers
                            else -> {
                                Button(
                                    onClick = { isEditMode = true },
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .wrapContentHeight(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF0FB2B2),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("Edit")
                                }
                            }
                        }

                        if (showBackDialog) {
                            DialogTemplate(
                                show = showBackDialog,
                                title = "Exit?",
                                description = "Your answers won’t be saved.",
                                primaryText = "Yes, exit",
                                onPrimary = {
                                    showBackDialog = false
                                    navController.popBackStack()
                                },
                                secondaryText = "Cancel",
                                onSecondary = {
                                    showBackDialog = false
                                },
                                onDismiss = {
                                    showBackDialog = false
                                }
                            )
                        }
                        if (showCancelDialog) {
                            DialogTemplate(
                                show = true,
                                title = "Discard changes?",
                                description = "Your answers will revert to your previous submission.",
                                primaryText = "Yes, discard",
                                onPrimary = {
                                    isEditMode = false
                                    answers.clear()
                                    answers.addAll(existingAnswers ?: List(8) { null })
                                    showCancelDialog = false
                                },
                                secondaryText = "No, keep editing",
                                onSecondary = {
                                    showCancelDialog = false
                                },
                                onDismiss = {
                                    showCancelDialog = false
                                }
                            )
                        }


                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionnaireScreenPreview() {
    QuestionnaireScreen(navController = rememberNavController())
}
