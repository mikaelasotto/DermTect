package com.example.dermtect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dermtect.R

@Composable
fun ChangePasswordRow(icon: Int, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 43.92.dp, height = 40.26.dp)
                .clip(CircleShape)
                .background(Color(0xFFEDFFFF)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = label,
            fontSize = 16.sp,
            color = Color(0xFF484848),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .size(width = 26.dp, height = 24.dp)
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "Navigate",
                tint = Color(0xFF0FB2B2),
                modifier = Modifier.size(width = 8.03.dp, height = 12.dp)
            )
        }
    }
}

@Composable
fun AssessmentRow(icon: Int, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 43.92.dp, height = 40.26.dp)
                .clip(CircleShape)
                .background(Color(0xFFEDFFFF)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = label,
            fontSize = 16.sp,
            color = Color(0xFF484848),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .size(width = 26.dp, height = 24.dp)
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "Navigate",
                tint = Color(0xFF0FB2B2),
                modifier = Modifier.size(width = 8.03.dp, height = 12.dp)
            )
        }
    }
}

@Composable
fun DeleteAccountRow(icon: Int, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 43.92.dp, height = 40.26.dp)
                .clip(CircleShape)
                .background(Color(0xFFEDFFFF)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = label,
            fontSize = 16.sp,
            color = Color(0xFF484848),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .size(width = 26.dp, height = 24.dp)
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "Navigate",
                tint = Color(0xFF0FB2B2),
                modifier = Modifier.size(width = 8.03.dp, height = 12.dp)
            )
        }
    }
}

@Composable
fun ProfileLogoutRow(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logout_icon),
            contentDescription = "Logout",
            tint = Color(0xFF00B2B2),
            modifier = Modifier.size(width = 18.66.dp, height = 13.09.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "Logout",
            color = Color(0xFF484848),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )
    }
}
