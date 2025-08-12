package com.example.wallify.utlis.route

sealed class Screen(val route: String) {
    data object OnBoarding : Screen("onboarding")
    data object Home : Screen("Home")
    data object Streak : Screen("Streaks")
    data object Collection : Screen("Collections")
    data object Favorite : Screen("Favorites")

    companion object {
        val routesToHideBottomBar = listOf(
            OnBoarding.route
        )
    }
}
