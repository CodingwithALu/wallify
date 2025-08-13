package com.example.wallify.common.style

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import com.example.wallify.ui.theme.onBackgroundLight
import com.example.wallify.ui.theme.onSurfaceLight


object Shadows {
    // Primary shadow style
    val primary = Shadow(
        color = onBackgroundLight,
        offset = Offset(0f, 4f),
        blurRadius = 8f
    )
    // Card shadow style
    val card = Shadow(
        color = onSurfaceLight,
        offset = Offset(0f, 6f),
        blurRadius = 12f
    )
}