package com.example.wallify.feature.wallify.streak.widgets

import BottomSheetSet
import StreakItemScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.feature.wallify.streak.controller.StreakViewModel

@Composable
fun StreakListScreen(
    item: Image,
    navController: NavController
) {
    val viewModel: StreakViewModel = hiltViewModel()
    val streaks by viewModel.streakByPrice.collectAsState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var streak by remember { mutableStateOf<Image>(item) }
    LaunchedEffect(item) {
        viewModel.fetchStreakByPrice(item.id_point?: 0)
    }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(streaks) { items ->
                    StreakItemScreen(
                        item = items,
                        showPoint = false,
                        onClick = {
                            streak = item
                            showBottomSheet = !showBottomSheet
                        })
                }
            }
        }
        if (showBottomSheet){
            BottomSheetSet(
                item = streak,
                navController = navController,
                onDismiss = {
                    showBottomSheet = it
                }
            )
        }
    }
}