package com.example.wallify.common.widgets.appbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TAppBar(
    title: @Composable (() -> Unit)? = null,
    showBackArrow: Boolean = false,
    leadingIcon: ImageVector? = null,
    actions: List<@Composable RowScope.() -> Unit>? = null,
    leadingOnPressed: (() -> Unit)? = null,
    animatedAlpha: Float = 0f,
) {
    val dark = isSystemInDarkTheme()
    TopAppBar(
        modifier = Modifier
            .height(50.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = if (dark) Color.Black.copy(animatedAlpha) else Color.White.copy(animatedAlpha),
            scrolledContainerColor = if (dark) Color.Black.copy(animatedAlpha) else Color.White.copy(animatedAlpha)
        ),
        navigationIcon = {
            when {
                showBackArrow -> {
                    Icon(
                        painter = painterResource(R.drawable.chevron_back),
                        contentDescription = "Back",
                        modifier = Modifier.clickable {
                            leadingOnPressed?.invoke()
                        }
                    )

                }

                leadingIcon != null -> {
                    IconButton(onClick = { leadingOnPressed?.invoke() }) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = "Leading Icon"
                        )
                    }
                }

                else -> {}
            }
        },
        title = {
            title?.invoke()
        },
        actions = {
            actions?.forEach { action ->
                action()
            }
        }
    )
}


