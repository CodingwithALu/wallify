package com.example.wallify.common.widgets.texts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TSectionHeading(
    title: String,
    modifier: Modifier = Modifier,
    showActionButton: Boolean = true,
    buttonTitle: String? = null,
    onPressed: (() -> Unit)? = null,
    startText: Dp = 0.dp
) {
    Row(modifier = modifier,
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
                .padding(start = startText),
        )
        if (showActionButton) {
            TextButton(
                onClick = { onPressed?.invoke() },
                enabled = onPressed != null,
                modifier = Modifier.height(30.dp)
            ) {
                Text(text = buttonTitle ?: "View All")
            }
        }
    }
}