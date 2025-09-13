package com.example.wallify.feature.wallify.product.all_product.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wallify.R

@Composable
fun ButtonRow(
    animatedAlpha: Float = 1f
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .graphicsLayer {
                alpha = animatedAlpha
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.download_24dp),
                    contentDescription = "Download",
                    tint = Color.White.copy(alpha = animatedAlpha)
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.share_24dp),
                    contentDescription = "Share",
                    tint = Color.White.copy(alpha = animatedAlpha)
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.wallpaper_24dp),
                    contentDescription = "Dislike",
                    tint = Color.White.copy(alpha = animatedAlpha)
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Exit",
                    tint = Color.White.copy(alpha = animatedAlpha)
                )
            }
        }
    }
}