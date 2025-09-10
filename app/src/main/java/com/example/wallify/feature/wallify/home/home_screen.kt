package com.example.wallify.feature.wallify.home
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallify.common.widgets.appbar.TAppBar
import com.example.wallify.common.widgets.shimmer.TShimmerEffect
import com.example.wallify.feature.wallify.home.viewmodel.BannerViewModel
import com.example.wallify.feature.wallify.home.viewmodel.CategoryViewModel
import com.example.wallify.feature.wallify.home.widgets.BannerCarousel
import com.example.wallify.feature.wallify.home.widgets.TSubAppbarHome
import com.example.wallify.feature.wallify.home.widgets.VerticalTopBar
import com.example.wallify.navigation.NavigationMenu
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen

@SuppressLint("FrequentlyChangingValue")
@Composable
fun HomeScreen(
    navController: NavController,
){
    val bannerViewModel: BannerViewModel = hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val banners by bannerViewModel.banners.collectAsState()
    val isLoading = bannerViewModel.isLoading
    val errorMessage = bannerViewModel.errorMessage
    val categories by categoryViewModel.category.collectAsState()
    // Load images for selected category
    val imagesByCategory by categoryViewModel.imagesByCategory.collectAsState()
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(pageCount = { categories.size })
    val pagerStateBanner = rememberPagerState(pageCount = { banners.size })
    val lazyListState = rememberLazyListState()
    var showTopBar by rememberSaveable { mutableStateOf(true) }
    var showBanner by rememberSaveable { mutableStateOf(true) }
    val tabRowIndex = 3 // Banner = 0, TabRow = 1
    LaunchedEffect(lazyListState.firstVisibleItemIndex, selectedTabIndex, lazyListState.firstVisibleItemScrollOffset) {
        showTopBar = if (showTopBar) {
            lazyListState.firstVisibleItemIndex == tabRowIndex && lazyListState.firstVisibleItemScrollOffset == 0
        } else {
            if (lazyListState.firstVisibleItemIndex == 0) true else showTopBar
        }
    }

    // Sync pager and tab selection
    LaunchedEffect(selectedTabIndex) {
        pagerState.scrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }
    LaunchedEffect(selectedTabIndex) {
        val categoryId = categories.getOrNull(selectedTabIndex)?.id_cate
        if (categoryId != null) {
            categoryViewModel.fetchImagesForCategory(categoryId)
            pagerState.scrollToPage(selectedTabIndex)
        }
    }
    Scaffold (
        topBar = {
            VerticalTopBar(
                topBar = {
                    TSubAppbarHome {}
                },
                banner = {
                    when {
                        isLoading -> {
                            TShimmerEffect(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1.9f)
                                    .padding(horizontal = TSizes.defaultSpace)
                                    .clip(RoundedCornerShape(24.dp))
                            )
                        }
                        errorMessage != null -> {
                            Column(modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                Text("Error: $errorMessage")
                            }
                        }
                        banners.isEmpty() -> {
                            Text("Không có banner nào")
                        }
                        else -> {
                            BannerCarousel(
                                banners = banners,
                                pagerState = pagerStateBanner
                            )
                        }
                    }
                },
                tabRow = {
                    when {
                        categoryViewModel.isLoading -> {
                            TShimmerEffect(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .padding(horizontal = TSizes.defaultSpace)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                        }
                        categories.isEmpty() -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Không có danh mục nào", color = Color.Gray)
                            }
                        }
                        else -> {
                            ScrollableTabRow(
                                selectedTabIndex = selectedTabIndex,
                                edgePadding = 8.dp
                            ) {
                                categories.forEachIndexed { index, category ->
                                    Tab(
                                        selected = selectedTabIndex == index,
                                        onClick = { selectedTabIndex = index },
                                        text = { Text(category.title) }
                                    )
                                }
                            }
                        }
                    }
                },
                showTopBar = showTopBar,
                showBanner = showTopBar
            )
        },
        bottomBar = {
            NavigationMenu(
                navController = navController
            )
        }
    ) { innerPadding ->
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Banner
            item {
                Spacer(modifier = Modifier.height(TSizes.sm))
            }
            // HorizontalPager cho ảnh từng category
            item {
                Spacer(modifier = Modifier.height(TSizes.sm))
                if (categories.isNotEmpty()) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding())
                    ) { page ->
                        val category = categories[page]
                        val images = imagesByCategory[category.id_cate] ?: emptyList()
                        if (images.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                                    .clip(RoundedCornerShape(26.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Không có ảnh cho category này", color = Color.Gray)
                            }
                        } else {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)
                            ) {
                                images.forEach { image ->
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(180.dp)
                                            .padding(vertical = 4.dp)
                                            .clip(RoundedCornerShape(16.dp))
                                    ) {
                                        AsyncImage(
                                            model = image.url,
                                            contentDescription = image.title,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .fillMaxSize()
                                        )
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(
                                                    Brush.verticalGradient(
                                                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.4f))
                                                    )
                                                )
                                        )
                                        Text(
                                            text = image.title,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .align(Alignment.BottomStart)
                                                .padding(12.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}