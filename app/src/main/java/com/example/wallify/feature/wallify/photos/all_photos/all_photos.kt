@file:Suppress("UNCHECKED_CAST")

package com.example.wallify.feature.wallify.photos.all_photos

import CenterGripButton
import ProductVerticalEffect
import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallify.common.widgets.appbar.TAppBar
import com.example.wallify.feature.wallify.photos.viewmodel.PhotosViewModel
import kotlinx.coroutines.launch
import java.lang.Float.min
import com.example.wallify.common.widgets.products.WProductCardVertical
import com.example.wallify.feature.wallify.photos.all_photos.widgets.ButtonRow
import com.example.wallify.utlis.route.Screen

@SuppressLint("FrequentlyChangingValue", "ResourceType")
@Composable
fun AllPhotosScreen(
    id: String?,
    navController: NavController
) {
    //viewModel
    val viewModel: PhotosViewModel = hiltViewModel()
    val photo by viewModel.photo.collectAsState()
    val allImages by viewModel.allPhotos.collectAsState()
    val isLoading = viewModel.isLoading
    val context = LocalContext.current
    // State
    val listState = rememberLazyGridState()
    val lastPosition = remember { mutableStateOf(Pair(0, 0)) }
    val fadeOutOffset = 1000
    val alpha: Float = if (listState.firstVisibleItemIndex == lastPosition.value.first) {
        1f - min(1f, listState.firstVisibleItemScrollOffset / fadeOutOffset.toFloat())
    } else {
        0f
    }
    val alphaTopBar: Float = if (listState.firstVisibleItemIndex == lastPosition.value.first) {
        0f + min(0f, listState.firstVisibleItemScrollOffset / fadeOutOffset.toFloat())
    } else {
        1f
    }
    val animatedAlpha by animateFloatAsState(targetValue = alpha)
    val animatedAlphaTopBar by animateFloatAsState(targetValue = alphaTopBar)
    val coroutineScope = rememberCoroutineScope()
    var showImage by remember { mutableStateOf(false) }
    // BottomSheet
    LaunchedEffect(photo) {
        viewModel.fetchPhotoById(id!!)
    }
    LaunchedEffect(photo) {
        if (photo != null) {
            if (photo!!.tags.isNotEmpty()) {
                viewModel.fetchRelatedPhotosForQuery(photo!!.tags.mapNotNull { it.title })
            }
        }
    }
    LaunchedEffect(animatedAlpha) {
        if (animatedAlpha < 0.01f) {
            listState.animateScrollToItem(3)
        }
    }
    Scaffold(
        topBar = {
            if (!showImage && photo != null) {
                TAppBar(
                    title = {
                        if (photo!!.description?.isNotEmpty() == true) {
                            Text(
                                text = photo!!.description!!
                            )
                        }
                    },
                    showBackArrow = true,
                    leadingOnPressed = {
                        navController.popBackStack()
                    },
                    animatedAlpha = animatedAlphaTopBar
                )
            }
        }
    ) { innerPadding ->
        if (photo != null) {
            AsyncImage(
                model = photo!!.urls.regular,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        LazyVerticalGrid(
            state = listState,
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item(span = { GridItemSpan(3) }) {
                Box(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .clickable {
                            showImage = !showImage
                        }
                        .aspectRatio(if (!showImage) 0.7f else 0.1f)
                )
            }
            if (!showImage) {
                item(span = { GridItemSpan(3) }) {
                    if (photo != null) {
                        ButtonRow(
                            item = photo!!,
                            navController = navController,
                            animatedAlpha = animatedAlpha,
                            context = context
                        )
                    }
                }
                item(span = { GridItemSpan(3) }) {
                    CenterGripButton(
                        alpha = animatedAlpha,
                        onClick = {
                            coroutineScope.launch {
                                listState.animateScrollToItem(index = 3)
                            }
                        })
                }
                when {
                    isLoading || allImages.isEmpty() -> {
                        items(9) {
                            ProductVerticalEffect()
                        }
                    }

                    else -> {
                        items(allImages) { item ->
                            WProductCardVertical(
                                item = item,
                                onclick = {
                                    navController.navigate("${Screen.PhotosList.route}/${item.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
