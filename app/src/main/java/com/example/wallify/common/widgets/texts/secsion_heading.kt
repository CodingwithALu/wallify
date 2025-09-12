package com.example.wallify.common.widgets.texts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun TSectionHeading(
    title: String,
    modifier: Modifier = Modifier,
    textColor: Color? = null,
    showActionButton: Boolean = true,
    buttonTitle: String? = null,
    onPressed: (() -> Unit)? = null,
    viewAllText: String = "View All"
) {
    Row(modifier = modifier,
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        if (showActionButton) {
            Spacer(modifier = Modifier.width(8.dp))
            TextButton(
                onClick = { onPressed?.invoke() },
                enabled = onPressed != null
            ) {
                Text(text = buttonTitle ?: viewAllText)
            }
        }
    }
}