package com.example.dermtect.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dermtect.poppinsFont
import com.example.dermtect.ui.components.PrimaryButton
import com.example.dermtect.ui.components.ResendEmailText
import com.example.dermtect.ui.components.ScreenLayout
import com.example.dermtect.ui.components.ScreenTitle
import com.example.dermtect.ui.components.SubText
import com.example.dermtect.ui.components.TopBottomBubbles
import com.example.dermtect.ui.theme.DermTectTheme
import kotlinx.coroutines.delay


@Composable
fun ForgotPass2(navController: NavController) {
    val codeLength = 5
    var fullCode by remember { mutableStateOf("") }

    var resendStatus by remember { mutableStateOf<String?>(null) }
    var isResendClickable by remember { mutableStateOf(true) }
    var triggerResend by remember { mutableStateOf(false) }

    ScreenLayout {
        TopBottomBubbles()

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle("Check your email")
            Spacer(modifier = Modifier.height(8.dp))
            SubText("We sent a reset link to example@email.com\nEnter the 5-digit code mentioned in the email")

            Spacer(modifier = Modifier.height(32.dp))

            // 👇 Fluid Input Code Boxes
            FluidCodeInput(
                code = fullCode,
                onCodeChange = { fullCode = it.take(codeLength) },
                count = codeLength
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                text = "Verify Code",
                enabled = fullCode.length == codeLength,
                onClick = {
                    navController.navigate("forgot_pass3")
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            ResendEmailText(
                onResendClick = {
                    if (isResendClickable) {
                        resendStatus = "Code resent!"
                        isResendClickable = false
                        triggerResend = true
                    }
                }
            )

            if (triggerResend) {
                LaunchedEffect(Unit) {
                    delay(3000)
                    resendStatus = null
                    isResendClickable = true
                    triggerResend = false
                }
            }

            resendStatus?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it,
                    color = Color(0xFF0FB2B2),
                    fontSize = 12.sp,
                    fontFamily = poppinsFont
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ForgotPass2Preview() {
    DermTectTheme {
        // Safe preview with empty lambda
        ForgotPass2(navController = rememberNavController())
    }
}


@Composable
fun FluidCodeInput(
    code: String,
    onCodeChange: (String) -> Unit,
    count: Int = 5,
    boxSize: Dp = 56.dp,
    boxSpacing: Dp = 20.dp
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        BasicTextField(
            value = code,
            onValueChange = {
                if (it.length <= count && it.all { c -> c.isDigit() }) {
                    onCodeChange(it)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            decorationBox = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(boxSpacing),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 0 until count) {
                        val char = code.getOrNull(i)?.toString() ?: ""
                        Box(
                            modifier = Modifier
                                .size(boxSize)
                                .border(
                                    width = 2.dp,
                                    color = Color(0xFF648DDB),
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = char,
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = poppinsFont,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                }
            }
        )
    }
}
