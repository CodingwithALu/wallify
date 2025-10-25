package com.example.wallify.feature.wallify.home

import android.annotation.SuppressLint
import com.example.wallify.feature.wallify.home.widgets.ImageMasonryList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallify.common.widgets.appbar.TTabBar
import com.example.wallify.common.widgets.shimmer.TImageVerticalEffect
import com.example.wallify.common.widgets.shimmer.TabRowEffect
import com.example.wallify.feature.wallify.home.viewmodel.HomeViewModel
import com.example.wallify.feature.wallify.home.widgets.TAppbarHome
import com.example.wallify.feature.wallify.home.widgets.VerticalTopBar
import com.example.wallify.navigation.BottomAppBarr
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen

@SuppressLint("ResourceType")
@Composable
fun HomeScreen(
    navController: NavController,
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val topics by viewModel.topics.collectAsState()
    val imagesByCategory by viewModel.photosByTopics.collectAsState()
    val isLoading = viewModel.isLoading
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    // show pager
    val pagerState = rememberPagerState(pageCount = { topics.size })
    var showTopBar by rememberSaveable { mutableStateOf(true) }
    LaunchedEffect(topics) {
        val firstId: String? = topics.firstOrNull()?.id
        if (firstId != null) {
            viewModel.fetchPhotosForTopics(firstId)
            pagerState.scrollToPage(selectedTabIndex)
        }
    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.scrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }
    LaunchedEffect(selectedTabIndex) {
        val categoryId: String? = topics.getOrNull(selectedTabIndex)?.id
        if (categoryId != null) {
            viewModel.fetchPhotosForTopics(categoryId)
            pagerState.scrollToPage(selectedTabIndex)
        }
    }
    Scaffold (
        bottomBar = {
            BottomAppBarr(
                showBar = true,
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
                        },
                        showNotification = true,
                        searchClick = {
                            navController.navigate(Screen.Search.route)
                        }
                    )
                },
                showTopBar = showTopBar,
                modifier = Modifier.padding(
                    horizontal = TSizes.md,
                )
            )
            when {
                topics.isEmpty() -> {
                    Column {
                        TabRowEffect()
                        Spacer(modifier = Modifier.height(TSizes.sm))
                        TImageVerticalEffect(
                            onScroll = { isScrollingUp ->
                                showTopBar = isScrollingUp
                            }
                        )
                    }
                }
                else -> {
                    TTabBar(
                        tabs = topics,
                        selectedTabIndex = selectedTabIndex,
                        onTabSelected = { index -> selectedTabIndex = index }
                    )
                }
            }
            Spacer(modifier = Modifier.height(TSizes.sm))
            if (topics.isNotEmpty()) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                ) { page ->
                    val category = topics[page]
                    val images = imagesByCategory[category.id] ?: emptyList()
                    when{
                        isLoading -> {
                            TImageVerticalEffect(
                                onScroll = { isScrollingUp ->
                                    showTopBar = isScrollingUp
                                }
                            )
                        }
                        else -> {
                            ImageMasonryList(
                                topics = images,
                                navController = navController,
                                onScroll = { isScrollingUp ->
                                    showTopBar = isScrollingUp
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}