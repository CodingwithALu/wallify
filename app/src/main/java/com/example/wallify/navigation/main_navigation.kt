package com.example.wallify.navigation

import StreakScreen
import com.example.wallify.feature.wallify.home.HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wallify.feature.authentication.onboarding.OnBoardingScreen
import com.example.wallify.feature.wallify.collections.CollectionScreen
import com.example.wallify.feature.wallify.favorites.FavoritesScreen
import com.example.wallify.utlis.route.Screen

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
            HomeScreen()
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
    }
}