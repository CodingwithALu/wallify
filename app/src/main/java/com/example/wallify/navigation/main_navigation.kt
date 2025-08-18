package com.example.wallify.navigation

import SettingScreen
import com.example.wallify.feature.wallify.product.all_product.AllProductScreen
import StreakScreen
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

@Composable
fun MainNavigation(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen(
                onSkip = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnBoarding.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController, modifier = modifier)
        }
        composable(Screen.Streak.route){
            StreakScreen(navController = navController, modifier = modifier)
        }
        composable(Screen.Collection.route){
            CollectionScreen(modifier = modifier, navController)
        }
        composable(Screen.Favorite.route){
            FavoritesScreen()
        }
        composable(Screen.Setting.route){
            SettingScreen(navController = navController)
        }
        // allProduct
        composable(Screen.ProductList.route) { backStackEntry ->
            val categoryJson = backStackEntry.arguments?.getString("item")
            val item = Gson().fromJson(categoryJson, Category::class.java)
            AllProductScreen(item.title, navController)
        }
        // ProductDetails
        composable(Screen.ProductDetails.route){ backStackEntry ->
            val product = backStackEntry.arguments?.getString("items")
            val items = Gson().fromJson(product, ProductModel::class.java)
            ProductDetailsScreen(items, navController)
        }
    }
}