package com.example.dermtect.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.R
import kotlinx.coroutines.delay

@Composable
fun DialogTemplate(
    show: Boolean,
    title: String,
    description: String? = null,
    imageResId: Int? = null,
    primaryText: String? = null,
    onPrimary: (() -> Unit)? = null,
    secondaryText: String? = null,
    onSecondary: (() -> Unit)? = null,
    tertiaryText: String? = null,
    onTertiary: (() -> Unit)? = null,
    autoDismiss: Boolean = false,
    dismissDelay: Long = 1000L,
    onDismiss: () -> Unit
) {
    if (show) {
        LaunchedEffect(show) {
            if (autoDismiss && primaryText == null && secondaryText == null && tertiaryText == null) {
                delay(dismissDelay)
                onDismiss()
            }
        }

        Dialog(onDismissRequest = { onDismiss() }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .wrapContentSize(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF0FB2B2)
                    )

                    imageResId?.let {
                        Spacer(modifier = Modifier.height(10.dp))
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            modifier = Modifier.size(150.dp)
                        )
                    }

                    description?.let {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            color = Color.DarkGray
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    val buttonCount = listOfNotNull(primaryText, secondaryText, tertiaryText).size
                    val allFilled = buttonCount == 3

                    // PRIMARY
                    primaryText?.let {
                        Button(
                            onClick = {
                                onPrimary?.invoke()
                                onDismiss()
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .wrapContentHeight(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0FB2B2),
                                contentColor = Color.White
                            )
                        ) {
                            Text(it, style = MaterialTheme.typography.bodyLarge)
                        }
                    }

                    // SECONDARY
                    secondaryText?.let {
                        Spacer(modifier = Modifier.height(5.dp))
                        if (allFilled) {
                            Button(
                                onClick = {
                                    onSecondary?.invoke()
                                    onDismiss()
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .wrapContentHeight(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF0FB2B2),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(it, style = MaterialTheme.typography.bodyLarge)
                            }
                        } else {
                            OutlinedButton(
                                onClick = {
                                    onSecondary?.invoke()
                                    onDismiss()
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .wrapContentHeight(),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF0FB2B2)),
                                border = BorderStroke(1.dp, Color(0xFF0FB2B2))
                            ) {
                                Text(it, style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }

                    // TERTIARY
                    tertiaryText?.let {
                        Spacer(modifier = Modifier.height(5.dp))
                        if (allFilled) {
                            Button(
                                onClick = {
                                    onTertiary?.invoke()
                                    onDismiss()
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .wrapContentHeight(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF0FB2B2),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(it, style = MaterialTheme.typography.bodyLarge)
                            }
                        } else {
                            TextButton(
                                onClick = {
                                    onTertiary?.invoke()
                                    onDismiss()
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .wrapContentHeight()
                            ) {
                                Text(it, style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF0FB2B2)))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Dialog1(navController: NavController) {
    var show by remember { mutableStateOf(true) }

    DialogTemplate(
        show = show,
        title = "Saved!",
        imageResId = R.drawable.pdf_download,
        autoDismiss = true,
        onDismiss = { show = false }
    )
}

@Composable
fun Dialog2(navController: NavController) {
    var show by remember { mutableStateOf(true) }

    DialogTemplate(
        show = show,
        title = "Exit?",
        description = "Your answers won’t be saved.",
        primaryText = "Yes, exit",
        onPrimary = { navController.popBackStack() },
        secondaryText = "Cancel",
        onSecondary = { show = false },
        onDismiss = { show = false }
    )
}

@Composable
fun Dialog3(navController: NavController) {
    var show by remember { mutableStateOf(true) }

    DialogTemplate(
        show = show,
        title = "Action required",
        imageResId = R.drawable.pdf_download,
        primaryText = "Download",
        onPrimary = { /* Handle primary */ },
        secondaryText = "Cancel",
        onSecondary = { /* Handle cancel */ },
        tertiaryText = "Learn more",
        onTertiary = { /* Handle info */ },
        onDismiss = { show = false }
    )
}

@Preview(showBackground = true)
@Composable
fun Dialog1Preview() {
    Dialog1(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun Dialog2Preview() {
    Dialog2(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun Dialog3Preview() {
    Dialog3(navController = rememberNavController())
}
