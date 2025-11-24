package com.example.wallify.feature.wallify.stats.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun StatCard(title: String, bigValue: String, extra: List<Pair<String, String>>) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color(0xFF545454)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = bigValue,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color(0xFF212121)
            )
            Spacer(Modifier.height(12.dp))
            extra.forEach { (label, value) ->
                Text(
                    text = label,
                    fontSize = 14.sp,
                    color = Color(0xFF818181)
                )
                Text(
                    text = value,
                    fontSize = 14.sp,
                    color = Color(0xFF212121),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }
        }
    }
}