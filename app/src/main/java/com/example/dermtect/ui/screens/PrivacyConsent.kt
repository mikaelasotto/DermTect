package com.example.dermtect.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun PrivacyConsentDialog(
    show: Boolean,
    onConsent: () -> Unit,
    onDecline: () -> Unit
) {
    if (!show) return

    Dialog(onDismissRequest = {}) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color.White, shape = RoundedCornerShape(25.dp))
                .border(0.5.dp, Color(0xFF0FB2B2), shape = RoundedCornerShape(25.dp))
                .padding(24.dp)
        ) {
            PrivacyConsentContent(
                onConsent = onConsent,
                onDecline = onDecline
            )
        }
    }
}

@Composable
fun PrivacyConsentContent(
    onConsent: () -> Unit,
    onDecline: () -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }

    val fullText = "DermTect uses cookies and similar technologies to enhance your experience, analyze platform usage, and deliver content tailored to your needs. These tools help us improve our services while ensuring your data remains secure and protected. Granting permission does not give us access to your personal information from other apps or websites."

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 450.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "We Care About Your Privacy",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF0FB2B2)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = fullText,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF1D1D1D)
        )

        Spacer(modifier = Modifier.height(23.dp))

        Row(
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                    .background(
                        if (isChecked) Color(0xFF0FB2B2) else Color.Transparent,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable { isChecked = !isChecked },
                contentAlignment = Alignment.Center
            ) {
                if (isChecked) {
                    Text(
                        text = "✓",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "I understand that my skin photos, reports, and details may be securely stored to support analysis, track results, and enable dermatologist feedback.",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                color = Color(0xFF1D1D1D),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(23.dp))

        Button(
            onClick = onConsent,
            enabled = isChecked,
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isChecked) Color(0xFF0FB2B2) else Color(0xFF626262),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Consent", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal))
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onDecline,
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            border = BorderStroke(0.5.dp, Color(0xFF0FB2B2)),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF0FB2B2),
                containerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Do Not Consent", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal))
        }
    }
}

fun checkPrivacyConsent(uid: String, onResult: (Boolean) -> Unit) {
    FirebaseFirestore.getInstance().collection("users").document(uid)
        .get()
        .addOnSuccessListener { document ->
            val consent = document.getBoolean("privacyConsent") ?: false
            onResult(consent)
        }
}

fun saveConsent(uid: String) {
    FirebaseFirestore.getInstance().collection("users").document(uid)
        .update("privacyConsent", true)
}
