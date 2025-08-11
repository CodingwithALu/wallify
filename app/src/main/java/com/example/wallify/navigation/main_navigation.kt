package com.example.wallify.navigation

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wallify.feature.authentication.onboarding.OnBoardingScreen
import com.example.wallify.utlis.route.Screen

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
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
    }
}