package com.example.wallify.utlis.route

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onboarding")
    object Home : Screen("home")
    object Streak : Screen("streak")
    object Collection : Screen("collection")
    object Favorite : Screen("favorite")
    object Setting : Screen("setting")
    object ProductList : Screen("product_list")
    object ProductDetails : Screen("product_details")
    object StreakList : Screen("streak_list")
}

val routesToHideBottomBar = listOf(
    Screen.OnBoarding.route,
    "product_list",
    "product_details",
    "setting"
)
