package com.example.dermtect.ui.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.example.dermtect.poppinsFont
import androidx.compose.runtime.*
import kotlinx.coroutines.delay


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
        style = TextStyle(fontSize = 12.sp, fontFamily = poppinsFont, color = Color(0xFF1D1D1D)),
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

