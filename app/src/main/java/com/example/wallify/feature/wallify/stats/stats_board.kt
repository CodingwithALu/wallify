package com.example.wallify.feature.wallify.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallify.feature.wallify.stats.widgets.StatCard

@Composable
fun StatsBoard() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tôi iu",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF212121),
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        )
        StatCard(
            title = "All-time views",
            bigValue = "1,392,145,503,237",
            extra = listOf(
                "Views this month" to "+16,044,420,130",
                "Views per second" to "6,189"
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        StatCard(
            title = "All-time downloads",
            bigValue = "8,445,608,419",
            extra = listOf(
                "Downloads this month" to "+81,665,642",
                "Downloads per second" to "31"
            )
        )
    }
}