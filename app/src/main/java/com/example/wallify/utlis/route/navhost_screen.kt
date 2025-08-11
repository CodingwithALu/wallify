package com.example.wallify.utlis.route

sealed class Screen(val route: String) {
    data object OnBoarding : Screen("onboarding")
    data object Home : Screen("home")
}