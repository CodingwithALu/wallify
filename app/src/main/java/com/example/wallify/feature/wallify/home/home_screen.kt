package com.example.wallify.feature.wallify.home

import ImageMasonryList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallify.common.widgets.appbar.TTabBar
import com.example.wallify.common.widgets.shimmer.TImageVerticalEffect
import com.example.wallify.common.widgets.shimmer.TabRowEffect
import com.example.wallify.feature.wallify.home.viewmodel.HomeViewModel
import com.example.wallify.feature.wallify.home.widgets.TAppbarHome
import com.example.wallify.feature.wallify.home.widgets.VerticalTopBar
import com.example.wallify.navigation.BottomAppBarr
import com.example.wallify.navigation.MainNavigation
import com.example.wallify.utlis.constants.TSizes

@Composable
fun HomeScreen(
    navController: NavController,
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val categories by viewModel.category.collectAsState()
    val imagesByCategory by viewModel.imagesByCategory.collectAsState()
    val isLoading = viewModel.isLoading
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(pageCount = { categories.size })
    var showTopBar by rememberSaveable { mutableStateOf(true) }
    var showBottomBar by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(categories) {
        val firstId: Int? = categories.firstOrNull()?.id_cate
        if (firstId != null) {
            viewModel.fetchImagesForCategory(firstId)
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
        val categoryId: Int? = categories.getOrNull(selectedTabIndex)?.id_cate
        if (categoryId != null) {
            viewModel.fetchImagesForCategory(categoryId)
            pagerState.scrollToPage(selectedTabIndex)
        }
    }
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
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp)
                    )
                },
                showTopBar = showTopBar,
                modifier = Modifier.padding(
                    horizontal = TSizes.md,
                )
            )
            when {
                categories.isEmpty() -> {
                    Column {
                        TabRowEffect()
                        Spacer(modifier = Modifier.height(TSizes.sm))
                        TImageVerticalEffect(
                            onScroll = { isScrollingUp ->
                                showTopBar = isScrollingUp
                                showBottomBar = !isScrollingUp
                            }
                        )
                    }
                }
                else -> {
                    TTabBar(
                        tabs = categories,
                        selectedTabIndex = selectedTabIndex,
                        onTabSelected = { index -> selectedTabIndex = index }
                    )
                }
            }
            Spacer(modifier = Modifier.height(TSizes.sm))
            if (categories.isNotEmpty()) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                ) { page ->
                    val category = categories[page]
                    val images = imagesByCategory[category.id_cate] ?: emptyList()
                    if (images.isEmpty() || isLoading) {
                        TImageVerticalEffect(
                            onScroll = { isScrollingUp ->
                                showTopBar = isScrollingUp
                                showBottomBar = !isScrollingUp
                            }
                        )
                    } else {
                        ImageMasonryList(
                            categories = images,
                            navController = navController,
                            onScroll = { isScrollingUp ->
                                showTopBar = isScrollingUp
                                showBottomBar = !isScrollingUp
                            }
                        )
                    }
                }
            }
        }
    }
}