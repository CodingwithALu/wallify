package com.example.wallify.common.widgets.custom_shapes.container

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.TextUnit
import com.example.wallify.ui.theme.onSurfaceDark
import com.example.wallify.ui.theme.outlineVariantDark
import com.example.wallify.ui.theme.scrimDark
import com.example.wallify.ui.theme.surfaceBrightDark
import com.example.wallify.utlis.constants.TSizes

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun TSearchContainer(
    text: String,
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
    showBackground: Boolean = true,
    showBorder: Boolean = true,
    onTap: (() -> Unit)? = null,
    paddingHorizontal: Dp = TSizes.defaultSpace,
    containerPadding: Dp = TSizes.md,
    borderRadius: Dp = TSizes.defaultSpace,
    borderColor: Color = outlineVariantDark,
    backgroundLight: Color = onSurfaceDark,
    backgroundDark: Color = scrimDark,
    textColor: Color = surfaceBrightDark,
    fontSize: TextUnit = TextUnit.Unspecified,
    isDarkMode: Boolean = false,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    Box(
        modifier = modifier
            .clickable(enabled = onTap != null) { onTap?.invoke() }
            .padding(horizontal = paddingHorizontal)
    ) {
        Row(
            modifier = Modifier
                .width(screenWidth)
                .background(
                    color = if (showBackground) {
                        if (isDarkMode) backgroundDark else backgroundLight
                    } else Color.Transparent,
                    shape = RoundedCornerShape(borderRadius)
                )
                .then(
                    if (showBorder) Modifier.border(
                        width = 1.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(borderRadius)
                    )
                    else Modifier
                )
                .padding(all = containerPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                icon()
            } else {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = textColor
                )
            }
            Spacer(modifier = Modifier.width(TSizes.spaceBtwItems))
            Text(
                text = text,
                color = textColor,
                fontSize = fontSize
            )
        }
    }
}