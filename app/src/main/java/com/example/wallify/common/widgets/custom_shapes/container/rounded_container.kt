package com.example.wallify.common.widgets.custom_shapes.container

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wallify.ui.theme.onBackgroundDark
import com.example.wallify.ui.theme.onSurfaceDark
import com.example.wallify.utlis.constants.TSizes

@Composable
fun TRoundedContainer(
    modifier: Modifier = Modifier,
    width: Dp? = null,
    height: Dp? = null,
    radius: Dp = TSizes.md,
    showBorder: Boolean = false,
    borderColor: Color = onSurfaceDark,
    backgroundColor: Color = onBackgroundDark,
    padding: PaddingValues = PaddingValues(0.dp),
    content: @Composable () -> Unit = {},
    onTap: () -> Unit = {}
){
    var m = modifier
        .clickable { onTap() }
        .padding(padding)
        .clip(RoundedCornerShape(radius))
        .background(backgroundColor)
    if (showBorder) {
        m = m.border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(radius))
    }
    if (width != null) {
        m = m.then(Modifier.size(width = width, height = height ?: width))
    } else if (height != null) {
        m = m.then(Modifier.height(height))
    }
    Box(
        modifier = m
    ){
        content()
    }
}