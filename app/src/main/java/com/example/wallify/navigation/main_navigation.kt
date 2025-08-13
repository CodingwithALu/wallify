package com.example.wallify.navigation

import com.example.wallify.feature.wallify.product.all_product.AllProductScreen
import StreakScreen
import com.example.wallify.feature.wallify.home.HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core_model.Category
import com.example.wallify.feature.authentication.onboarding.OnBoardingScreen
import com.example.wallify.feature.wallify.collections.CollectionScreen
import com.example.wallify.feature.wallify.favorites.FavoritesScreen
import com.example.wallify.utlis.route.Screen
import com.google.gson.Gson

@Composable
fun MainNavigation(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.OnBoarding.route) {
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen(
                onNext = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnBoarding.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Streak.route){
            StreakScreen()
        }
        composable(Screen.Collection.route){
            CollectionScreen()
        }
        composable(Screen.Favorite.route){
            FavoritesScreen()
        }
        // allProduct
        composable(Screen.ProductList.route) { backStackEntry ->
            val categoryJson = backStackEntry.arguments?.getString("item")
            val item = Gson().fromJson(categoryJson, Category::class.java)
            AllProductScreen(item.title, navController)
        }
    }
}