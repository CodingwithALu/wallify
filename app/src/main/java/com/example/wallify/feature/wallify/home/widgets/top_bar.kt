package com.example.wallify.feature.wallify.home.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalTopBar(
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit)? = null,
    showTopBar: Boolean = true,
) {
    AnimatedVisibility(
        visible = showTopBar,
        enter = slideInVertically(
            initialOffsetY = { -it },
            animationSpec = tween(600)
        ) + fadeIn(animationSpec = tween(600)),
        exit = slideOutVertically(
            targetOffsetY = { -it },
            animationSpec = tween(600)
        ) + fadeOut(animationSpec = tween(600)),
        modifier = modifier) {
        if (showTopBar) {
            topBar?.invoke()
        }
    }
}