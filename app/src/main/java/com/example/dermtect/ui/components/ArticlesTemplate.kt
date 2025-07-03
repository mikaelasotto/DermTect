package com.example.dermtect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dermtect.model.NewsItem
import androidx.compose.ui.text.SpanStyle

@Composable
fun ArticleTemplate(
    newsItem: NewsItem,
    onBackClick: () -> Unit
) {
    BubblesBackground {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 23.dp)
        ) {
            BackButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(top = 50.dp, start = 80.dp, end = 20.dp, bottom = 10.dp)
        ) {
            Text(
                text = newsItem.title,
                textAlign = TextAlign.End,
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            newsItem.imageResId?.let { resId ->
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = "Article Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 180.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "✎ ${newsItem.source}",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold, color = Color.Gray)
                )
                Text(
                    text = newsItem.date,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold, color = Color(0xFFFFA726))
                )
            }

            Column(modifier = Modifier.fillMaxWidth(0.9f)) {
                Text(
                    text = newsItem.title,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(10.dp))


                Spacer(modifier = Modifier.height(12.dp))

                val body = newsItem.body

                Text(
                    buildAnnotatedString {
                        val boldRegex = Regex("\\*\\*(.*?)\\*\\*")
                        var lastIndex = 0

                        for (match in boldRegex.findAll(body)) {
                            append(body.substring(lastIndex, match.range.first))
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(match.groupValues[1])
                            }
                            lastIndex = match.range.last + 1
                        }
                        append(body.substring(lastIndex))
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp
                )
            }
        }
    }
}
