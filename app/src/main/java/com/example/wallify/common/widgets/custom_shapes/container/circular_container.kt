package com.example.wallify.common.widgets.custom_shapes.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wallify.ui.theme.onSurfaceDark

@Composable
fun TCircularContainer(
    modifier: Modifier = Modifier,
    width: Dp = 400.dp,
    height: Dp = 400.dp,
    radius: Dp = 400.dp,
    padding: Dp = 4.dp,
    backgroundColor: Color = onSurfaceDark,
    content: @Composable () -> Unit = {}
){
    Box(
        modifier = modifier
            .size(width = width, height = height)
            .clip(RoundedCornerShape(radius))
            .background(backgroundColor)
    ){
        content()
    }
}