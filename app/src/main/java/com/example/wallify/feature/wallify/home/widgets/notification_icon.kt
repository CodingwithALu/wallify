package com.example.wallify.feature.wallify.home.widgets

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.wallify.R
@Composable
fun NotificationIcon(onClick: () -> Unit = {},
                     navController: NavController) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.point),
            contentDescription = "Bell",
            tint = Color.Unspecified
        )
    }
}