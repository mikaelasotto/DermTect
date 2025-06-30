package com.example.dermtect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dermtect.ui.components.BackButton
import com.example.dermtect.ui.components.BubblesBackground
import com.example.dermtect.ui.components.TopRightNotificationIcon

@Composable
fun ArticleTemplate(
    title: String,
    imageResId: Int,
    source: String,
    timestamp: String,
    subtitle: String,
    body: String,
    onBackClick: () -> Unit
) {
    BubblesBackground {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 23.dp)
        ) {
            // Back button aligned to start
            BackButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(top = 50.dp, start = 80.dp, end = 20.dp, bottom = 10.dp),
        ) {
            // Title centered independently
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                maxLines = 2
            )
        }

        Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 110.dp, bottom = 20.dp)
                            .padding(horizontal = 20.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(20.dp))
                        Image(
                            painter = painterResource(id = imageResId),
                            contentDescription = "Article Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 180.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(horizontal = 10.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(bottom = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "✎ $source",
                                style = MaterialTheme.typography.bodySmall

                            )

                            Text(
                                text = timestamp,
                                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFFFA726))
                            )
                        }
                        Column (
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                        ) {
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "(Adapted from $source)",
                            style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = body,
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 20.sp
                        )

                        }
                    }
                }
        }

