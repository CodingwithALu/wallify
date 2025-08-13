package com.example.wallify.feature.wallify.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NotificationCount(count: Int) {
    // Lục giác đơn giản (giả lập bằng rounded box)
    Box(
        modifier = Modifier
            .size(38.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF009688)), // màu xanh ngọc
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = count.toString(),
            color = Color.White,
            fontSize = 20.sp
        )
    }
}