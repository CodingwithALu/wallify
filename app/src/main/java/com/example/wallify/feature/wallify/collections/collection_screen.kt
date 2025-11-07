package com.example.wallify.feature.wallify.collections

import CollectionItemScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallify.common.widgets.texts.TSectionHeading
import com.example.wallify.feature.wallify.collections.controller.CollectionViewModel
import com.example.wallify.feature.wallify.home.widgets.TAppbarHome
import com.example.wallify.feature.wallify.home.widgets.VerticalTopBar
import com.example.wallify.navigation.BottomAppBarr
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen

@Composable
fun CollectionScreen(navController: NavController) {
    val viewModel: CollectionViewModel = hiltViewModel()
    val collection by viewModel.collections.collectAsState()
    val isLoading = viewModel.isLoading
    Scaffold(
        bottomBar = {
            BottomAppBarr(
                showBar = true,
                navController = navController
            )
        }
    ) { innerPadding ->
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
                        },
                        showNotification = true
                    )
                },
                showTopBar = true,
                modifier = Modifier.padding(
                    horizontal = TSizes.md,
                )
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(collection) { item ->
                    CollectionItemScreen(
                        item = item,
                        onClick = {
                            navController.navigate("${Screen.CollectionPhotos.route}/${item.id}")
                        }
                    )
                }
            }
        }
    }
}