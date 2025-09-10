package com.example.wallify.feature.wallify.home.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalTopBar(
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit)? = null,
    banner: @Composable (() -> Unit)? = null,
    tabRow: @Composable (() -> Unit)? = null,
    showTopBar: Boolean = true,
    showBanner: Boolean = true,
) {
    Column(modifier = modifier) {
        if (showTopBar) {
            topBar?.invoke()
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (showBanner) {
            banner?.invoke()
            Spacer(modifier = Modifier.height(8.dp))
        }
            tabRow?.invoke()
        }
}