package com.example.wallify.common.widgets.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wallify.common.widgets.custom_shapes.container.TCircularContainer
import com.example.wallify.ui.theme.backgroundDark
import com.example.wallify.ui.theme.onTertiaryLight
import com.example.wallify.ui.theme.outlineLight
import com.example.wallify.ui.theme.primaryDark
import com.example.wallify.utlis.helpers.THelperFunctions

@Composable
fun TChoiceChip(
    text: String,
    selected: Boolean,
    onSelected: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val color = THelperFunctions.getColor(text)
    val isColorChip = color != null

    Surface(
        color = (if (isColorChip) color else if (selected) primaryDark else backgroundDark)!!,
        shape = if (isColorChip) CircleShape else RoundedCornerShape(24.dp),
        modifier = modifier
            .clip(if (isColorChip) CircleShape else RoundedCornerShape(24.dp))
            .clickable(enabled = onSelected != null) { onSelected?.invoke(!selected) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .then(
                    if (isColorChip) Modifier
                        .size(50.dp)
                        .padding(0.dp)
                    else Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
        ) {
            if (isColorChip) {
                TCircularContainer(
                    backgroundColor = color!!,
                    width = 50.dp,
                    height = 50.dp
                )
            } else {
                Text(
                    text = text,
                    color = if (selected) onTertiaryLight else outlineLight,
                )
            }
        }
    }
}
