package com.example.wallify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.core_viewmodel.repository.AppRepository
import com.example.wallify.ui.theme.AppTheme
import com.example.wallify.utlis.route.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                AppTheme {
                    val navController = rememberNavController()
                    val context = LocalContext.current
                    var isReady by remember { mutableStateOf(false) }

                    val repository = remember {
                        AppRepository(
                            context = context,
                            onCallBack = {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(0)
                                }
                            },
                            onBoardingClick = {
                                navController.navigate(Screen.OnBoarding.route) {
                                    popUpTo(0)
                                }
                            }
                        )
                    }
//                    LaunchedEffect(Unit) {
//                        repository.onReady()
//                        isReady = true
//                    }
//                    if (!isReady) {
//                        // Splash Screen
//                    } else {
                        App(navController = navController)
//                    }
                    }
            }
        }
}