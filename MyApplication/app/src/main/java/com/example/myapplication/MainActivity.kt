package com.example.dermtect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dermtect.ui.theme.DermtectTheme
import com.example.dermtect.ui.WeightTestRow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DermtectTheme {
                WeightTestRow()  // Run your weight test here
            }
        }
    }
}
