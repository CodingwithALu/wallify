package com.example.wallify.common.widgets.shimmer

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.wallify.utlis.constants.TSizes

@Composable
fun TShimmerEffect(
    modifier: Modifier = Modifier,
    clipSize: Dp = TSizes.lg,
    color: Color? = null
) {
    val dark = isSystemInDarkTheme()
    val baseColor = if (dark) Color(0xFF212121) else Color(0xFFE0E0E0)
    val highlightColor = if (dark) Color(0xFF616161) else Color(0xFFF5F5F5)
    val bgColor = color ?: if (dark) Color(0xFF323232) else Color.White

    // Animation for shimmer
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "shimmer_anim"
    )
    val brush = Brush.linearGradient(
        colors = listOf(baseColor, highlightColor, baseColor),
        start = Offset(translateAnim.value - 1000f, 0f),
        end = Offset(translateAnim.value, 0f)
    )
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(clipSize))
            .background(brush)

    )
}
