package com.example.wallify.feature.wallify.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.wallify.common.widgets.products.WProductCardVertical
import com.example.wallify.R
import com.example.wallify.common.widgets.shimmer.AnimationLoader
import com.example.wallify.feature.wallify.home.widgets.TAppbarHome
import com.example.wallify.feature.wallify.home.widgets.VerticalTopBar
import com.example.wallify.navigation.BottomAppBarr
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen

@SuppressLint("ResourceType")
@Composable
fun FavoritesScreen(
    navController: NavController
) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val favorites by viewModel.favorites.collectAsState()
    val showBottomBar by remember { mutableStateOf(true) }
    var showTopBar by rememberSaveable { mutableStateOf(true) }
    Scaffold (
            bottomBar = {
                BottomAppBarr(
                    showBar = showBottomBar,
                    navController = navController
                )
            }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            VerticalTopBar(
                topBar = {
                    TAppbarHome(
                        onAvatarClick = {
                            navController.navigate(Screen.Setting.route)
                        }
                    )
                },
                showTopBar = showTopBar,
                modifier = Modifier.padding(
                    horizontal = TSizes.md,
                )
            )
            if (favorites.isEmpty()) {
                AnimationLoader(
                    resIdRes = R.raw.transparent,

                )
            } else {
                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    items(favorites) { item ->
                        WProductCardVertical(
                            item = item,
                        )
                    }
                }
            }
        }
    }
}