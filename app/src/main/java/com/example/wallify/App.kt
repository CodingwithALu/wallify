package com.example.wallify

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.wallify.navigation.MainNavigation
import com.example.wallify.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()
        MainNavigation(navController = navController)
        }
}
