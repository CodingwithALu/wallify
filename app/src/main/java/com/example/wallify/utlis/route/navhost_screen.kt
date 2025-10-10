package com.example.wallify.utlis.route

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onboarding")
    object Home : Screen("home")
    object Streak : Screen("streak")
    object Collection : Screen("collection")
    object Favorite : Screen("favorite")
    object Setting : Screen("setting")
    object PhotosList : Screen("photos_list")
    object ProductDetails : Screen("product_details")
    object StreakList : Screen("streak_list")
    object Search : Screen("search")
}

val routesToHideBottomBar = listOf(
    Screen.OnBoarding.route,
    "photos_list",
    "product_details",
    "setting"
)
