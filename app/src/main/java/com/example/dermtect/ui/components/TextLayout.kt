package com.example.dermtect.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun ScreenTitle(
    title: String,
    subtitle: String? = null // optional subtitle
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        color = Color(0xFF1D1D1D),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, bottom = 8.dp)
    )

    subtitle?.let {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = it,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            color = Color(0xFF989898),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun SubText(
    text: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(start = 32.dp, bottom = 8.dp),
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
        color = Color(0xFF989898),
        modifier = modifier,
        textAlign = textAlign
    )
}


@Composable
fun ResendEmailText(onResendClick: () -> Unit) {
    var resendStatus by remember { mutableStateOf<String?>(null) }
    var isClickable by remember { mutableStateOf(true) }

    if (resendStatus != null) {
        LaunchedEffect(resendStatus) {
            delay(3000)
            resendStatus = null
            isClickable = true
        }
    }

    val annotatedText = buildAnnotatedString {
        append("Haven’t got the email yet? ")
        if (resendStatus == null) {
            pushStringAnnotation(tag = "RESEND", annotation = "resend")
            withStyle(SpanStyle(color = Color(0xFF2FD8D8), fontWeight = FontWeight.SemiBold)) {
                append("Resend email")
            }
            pop()
        } else {
            withStyle(SpanStyle(color = Color.Gray, fontWeight = FontWeight.SemiBold)) {
                append("Resending...")
            }
        }
    }

    ClickableText(
        text = annotatedText,
        style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF1D1D1D)),
        onClick = { offset ->
            if (isClickable) {
                annotatedText.getStringAnnotations(tag = "RESEND", start = offset, end = offset)
                    .firstOrNull()?.let {
                        resendStatus = "Resending..."
                        isClickable = false
                        onResendClick()
                    }
            }
        }
    )
}
