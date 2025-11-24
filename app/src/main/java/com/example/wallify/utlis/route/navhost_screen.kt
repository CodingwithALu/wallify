package com.example.wallify.utlis.route

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onboarding")
    object Home : Screen("Home")
    object Stats : Screen("Stats")
    object Collection : Screen("Collection")
    object Favorite : Screen("Favorite")
    object Setting : Screen("Setting")
    object PhotosList : Screen("photos_list")
    object ProductDetails : Screen("product_details")
    object StreakList : Screen("streak_list")
    object Search : Screen("search")
    object CollectionPhotos: Screen("collection_photos")
}

val routesToHideBottomBar = listOf(
    Screen.OnBoarding.route,
    "photos_list",
    "product_details",
    "Setting"
)
