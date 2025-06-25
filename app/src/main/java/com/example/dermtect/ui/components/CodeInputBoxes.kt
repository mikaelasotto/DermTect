package com.example.dermtect.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.TextStyle
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
    val code = remember { List(count) { mutableStateOf("") } }
    val focusRequesters = remember { List(count) { FocusRequester() } }

    Row(
        horizontalArrangement = Arrangement.spacedBy(boxSpacing),
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        code.forEachIndexed { index, state ->
            OutlinedTextField(
                value = state.value,
                onValueChange = {
                    if (it.length <= 1 && it.all { char -> char.isDigit() }) {
                        state.value = it
                        if (it.isNotEmpty() && index < count - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }
                        if (code.all { it.value.length == 1 }) {
                            onCodeComplete(code.joinToString("") { it.value })
                        }
                    }
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = poppinsFont,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .size(boxSize)
                    .focusRequester(focusRequesters[index])
                    .onKeyEvent {
                        if (it.key == Key.Backspace && it.type == KeyEventType.KeyDown) {
                            if (state.value.isEmpty() && index > 0) {
                                focusRequesters[index - 1].requestFocus()
                            }
                        }
                        false
                    },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            )
        }
    }

    // Optional: auto focus first box
    LaunchedEffect(Unit) {
        focusRequesters.first().requestFocus()
    }
}

