package com.example.wallify.utlis.route

sealed class Screen(val route: String) {
    data object OnBoarding : Screen("onboarding")
    data object Home : Screen("Home")
    data object Streak : Screen("Streaks")
    data object Collection : Screen("Collections")
    data object Favorite : Screen("Favorites")
    data object ProductList: Screen("allProducts/{item}")
    data object ProductDetails: Screen("ProductDetails/{items}")
    companion object {
        val routesToHideBottomBar = listOf(
            OnBoarding.route,
            ProductList.route,
            ProductDetails.route
        )
    }
}
