package com.example.wallify.navigation

import SettingScreen
import com.example.wallify.feature.wallify.product.all_product.AllProductScreen
import StreakScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import com.example.wallify.feature.wallify.home.HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core_model.Category
import com.example.core_model.ProductModel
import com.example.wallify.feature.authentication.screen.onboarding.OnBoardingScreen
import com.example.wallify.feature.wallify.collections.CollectionScreen
import com.example.wallify.feature.wallify.favorites.FavoritesScreen
import com.example.wallify.feature.wallify.product.product_details.ProductDetailsScreen
import com.example.wallify.utlis.route.Screen
import com.google.gson.Gson
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_viewmodel.controller.onboarding.OnBoardingViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.example.wallify.feature.wallify.home.model.Image

@Composable
fun MainNavigation(navController: NavHostController) {
    val onboardingViewModel: OnBoardingViewModel = hiltViewModel()
    val context = LocalContext.current
    val isFirstTimeState = onboardingViewModel.isFirstTime.collectAsState()
    val isFirstTime = isFirstTimeState.value

    LaunchedEffect(Unit) {
        onboardingViewModel.loadIsFirstTime(context)
    }

    // Loading UI khi chưa có dữ liệu
    if (isFirstTime == null) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
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
            FavoritesScreen()
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
        // ProductDetails
        composable("${Screen.ProductDetails.route}/{items}"){ backStackEntry ->
            val product = backStackEntry.arguments?.getString("items")
            val items = Gson().fromJson(product, ProductModel::class.java)
            ProductDetailsScreen(items, navController)
        }
    }
}