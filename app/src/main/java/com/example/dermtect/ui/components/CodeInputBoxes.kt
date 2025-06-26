package com.example.dermtect.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dermtect.poppinsFont


@Composable
fun CodeInputBoxes(
    count: Int = 5,
    boxSize: Dp = 56.dp,
    boxSpacing: Dp = 20.dp,
    onCodeComplete: (String) -> Unit = {}
) {
    var code by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    // Focus the hidden text field on load
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    // Hidden input to capture entire code
    BasicTextField(
        value = code,
        onValueChange = {
            if (it.length <= count && it.all { char -> char.isDigit() }) {
                code = it
                if (it.length == count) onCodeComplete(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .size(0.dp) // hidden
            .focusRequester(focusRequester)
            .focusable()
    )

    // Code boxes display
    Row(
        horizontalArrangement = Arrangement.spacedBy(boxSpacing),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusRequester.requestFocus() }, // tap anywhere to focus
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(count) { index ->
            val char = code.getOrNull(index)?.toString() ?: ""
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(boxSize)
                    .border(2.dp, Color(0xFF648DDB), RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = char,
                    fontSize = 20.sp,
                    fontFamily = poppinsFont,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

