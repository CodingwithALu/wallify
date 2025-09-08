package com.example.wallify

import PointCount
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wallify.common.widgets.appbar.TAppBar
import com.example.wallify.feature.wallify.home.widgets.NotificationIcon
import com.example.wallify.feature.wallify.home.widgets.TSubAppbarHome
import com.example.wallify.navigation.MainNavigation
import com.example.wallify.navigation.NavigationMenu
import com.example.wallify.ui.theme.AppTheme
import com.example.wallify.utlis.route.Screen

@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()
        MainNavigation(navController = navController)
        }
}
