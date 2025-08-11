package com.example.wallify.common.widgets.icons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.wallify.utlis.constants.TSizes

@Composable
fun TCircularIcon(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    width: Dp? = null,
    height: Dp? = null,
    size: Dp = TSizes.lg,
    color: Color? = null,
    backgroundColor: Color? = null,
    onPressed: (() -> Unit)? = null
) {
    val darkMode = isSystemInDarkTheme()
    val bgColor = backgroundColor ?: if (darkMode) {
        Color.Black.copy(alpha = 0.5f)
    } else {
        Color.White.copy(alpha = 0.5f)
    }

    Box(
        modifier = modifier
            .then(
                if (width != null && height != null) {
                    Modifier.size(width, height)
                } else if (width != null) {
                    Modifier.size(width)
                } else if (height != null) {
                    Modifier.size(height)
                } else {
                    Modifier
                }
            )
            .clip(CircleShape)
            .background(bgColor)
    ) {
        IconButton(
            onClick = { onPressed?.invoke() },
            modifier = Modifier
                .then(Modifier.size(size))
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color ?: MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(size)
            )
        }
    }
}