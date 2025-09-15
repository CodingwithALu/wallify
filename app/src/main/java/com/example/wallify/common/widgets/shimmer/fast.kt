package com.example.wallify.common.widgets.shimmer

import android.graphics.Canvas
import android.graphics.Color
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.wallify.ui.theme.primaryLight

@Composable
fun FastCircularProgressIndicator(
    modifier: Modifier = Modifier,
    strokeWidth: Float = 4f,
    speed: Float = 2f // bình thường là 1f, tăng lên sẽ nhanh hơn
) {
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (1000 / speed).toInt(), // càng nhỏ càng nhanh
                easing = LinearEasing
            )
        ),
        label = "angle"
    )

    Canvas(modifier = modifier.size(40.dp)) {
        drawArc(
            color = primaryLight,
            startAngle = angle,
            sweepAngle = 270f,
            useCenter = false,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            )
        )
    }
}
