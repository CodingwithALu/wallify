package com.example.wallify.common.widgets.custom_shapes.curved_edges

import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density

object TCustomCurvedEdges : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            lineTo(0f, size.height)
            quadraticTo(
                0f, size.height - 20f,
                30f, size.height - 20f
            )
            quadraticTo(
                0f, size.height - 20f,
                size.width - 30f, size.height - 20f
            )
            quadraticTo(
                size.width, size.height - 20f,
                size.width, size.height
            )
            lineTo(size.width, 0f)
            close()
        }
        return Outline.Generic(path)
    }
}