package com.example.wallify.navigation

import SettingScreen
import com.example.wallify.feature.wallify.product.all_product.AllProductScreen
import StreakScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import com.example.wallify.feature.wallify.home.HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wallify.feature.authentication.screen.onboarding.OnBoardingScreen
import com.example.wallify.feature.wallify.collections.CollectionScreen
import com.example.wallify.feature.wallify.favorites.FavoritesScreen
import com.example.wallify.feature.wallify.product.product_set_wallpaper.ProductSetsScreen
import com.example.wallify.utlis.route.Screen
import com.google.gson.Gson
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_viewmodel.controller.onboarding.OnBoardingViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import com.example.wallify.common.widgets.shimmer.FastCircularProgressIndicator
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.feature.wallify.streak.StreakListScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    val onboardingViewModel: OnBoardingViewModel = hiltViewModel()
    val isFirstTimeState = onboardingViewModel.isFirstTime.collectAsState()
    val isFirstTime = isFirstTimeState.value
    if (isFirstTime == null) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            FastCircularProgressIndicator()
        }
        return
    }
    val startDestination = if (isFirstTime) Screen.OnBoarding.route else Screen.Home.route
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen(
                onSkip = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnBoarding.route) { inclusive = true }
                    }
                },
                viewModel = onboardingViewModel
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Streak.route){
            StreakScreen(navController = navController)
        }
        composable(Screen.Collection.route){
            CollectionScreen(navController = navController)
        }
        composable(Screen.Favorite.route){
            FavoritesScreen(
                navController
            )
        }
        composable(Screen.Setting.route){
            SettingScreen(navController = navController)
        }
        // allProduct
        composable("${Screen.ProductList.route}/{item}") { backStackEntry ->
            val categoryJson = backStackEntry.arguments?.getString("item")
            val item = Gson().fromJson(categoryJson, Image::class.java)
            AllProductScreen(
                item,
                navController)
        }
        // ProductSets
        composable("${Screen.ProductDetails.route}/{item}"){ backStackEntry ->
            val product = backStackEntry.arguments?.getString("item")
            val items = Gson().fromJson(product, Image::class.java)
            ProductSetsScreen(items, navController)
        }
        composable("${Screen.StreakList.route}/{item}"){ backStackEntry ->
            val product = backStackEntry.arguments?.getString("item")
            val items = Gson().fromJson(product, Image::class.java)
            StreakListScreen(items,
                navController = navController)
        }
    }
}