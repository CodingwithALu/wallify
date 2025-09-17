package com.example.wallify.feature.wallify.product.all_product

import BottomSheetSet
import CenterGripButton
import ProductVerticalEffect
import android.annotation.SuppressLint
import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallify.common.widgets.appbar.TAppBar
import com.example.wallify.common.widgets.products.WProductCardVertical
import com.example.wallify.common.widgets.shimmer.AnimationLoader
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.feature.wallify.product.all_product.widgets.ButtonRow
import com.example.wallify.feature.wallify.product.viewmodel.ProductViewModel
import kotlinx.coroutines.launch
import java.lang.Float.min
import com.example.wallify.R

@SuppressLint("FrequentlyChangingValue", "ResourceType")
@Composable
fun AllProductScreen(
    item: Image,
    navController: NavController
) {
    //viewModel
    val viewModel: ProductViewModel = hiltViewModel()
    val allImages by viewModel.allImages.collectAsState()
    var images by remember { mutableStateOf(item) }
    val isLoading = viewModel.isLoading
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
    LaunchedEffect(animatedAlpha) {
        if (animatedAlpha < 0.01f) {
            listState.animateScrollToItem(3)
        }
    }
    LaunchedEffect(images) {
        Log.d("ProductViewModel", "fetchRelatedImages id: ${images.id_image}")
        viewModel.fetchRelatedImages(images.id_image)
    }
    Scaffold(
        topBar = {
            if (!showImage) {
                TAppBar(
                    title = {
                        Text(
                            text = images.title,
                        )
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
        AsyncImage(
            model = images.subImage.first().url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Log.d("AllProductScreen", "AllProductScreen: ${allImages.size}")
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
                    ButtonRow(
                        item = images,
                        navController = navController,
                        animatedAlpha = animatedAlpha
                    )
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
                    isLoading -> {
                        items(9) {
                            ProductVerticalEffect()
                        }
                    }
                    allImages.isEmpty() ->
                        item(span = { GridItemSpan(3) }) {
                            AnimationLoader(
                                resIdRes = R.raw.empty,
                            )
                        }
                    else -> {
                        items(allImages) { product ->
                            WProductCardVertical(
                                item = product,
                                onclick = { item ->
                                    images = item
                                })
                        }
                    }
                }
            }
        }
    }
}
