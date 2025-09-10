package com.example.wallify.common.widgets.appbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen
import com.example.wallify.utlis.route.routesToHideBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TAppBar(
    title: @Composable (() -> Unit)? = null,
    showBackArrow: Boolean = false,
    leadingIcon: ImageVector? = null,
    actions: List<@Composable RowScope.() -> Unit>? = null,
    leadingOnPressed: (() -> Unit)? = null,
    horizontalPadding: Dp = TSizes.xs,
) {
    TopAppBar(
        modifier = Modifier.padding(horizontal = horizontalPadding),
        navigationIcon = {
            when {
                showBackArrow -> {
                    IconButton(onClick = {
                        leadingOnPressed?.invoke()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
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
        },
        colors = TopAppBarDefaults.topAppBarColors()
    )
}


