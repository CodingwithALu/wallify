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
}

// Tách routesToHideBottomBar ra ngoài companion object để tránh lỗi khởi tạo
val routesToHideBottomBar = listOf(
    "onboarding",
    "product_list",
    "product_details",
    "setting"
)
