package com.example.wallify.feature.wallify.home.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
        exit = fadeOut(animationSpec = tween(600)),
        modifier = modifier
    ) {
        if (showTopBar) {
            topBar?.invoke()
        }
    }
}