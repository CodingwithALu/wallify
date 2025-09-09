package com.example.wallify.feature.wallify.home
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallify.common.widgets.appbar.TAppBar
import com.example.wallify.common.widgets.shimmer.TShimmerEffect
import com.example.wallify.feature.wallify.home.viewmodel.BannerViewModel
import com.example.wallify.feature.wallify.home.widgets.BannerCarousel
import com.example.wallify.feature.wallify.home.widgets.TSubAppbarHome
import com.example.wallify.navigation.NavigationMenu
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen
import sampleCategories

@Composable
fun HomeScreen(
    navController: NavController,
){
    val bannerViewModel: BannerViewModel = hiltViewModel()
    val banners by bannerViewModel.banners.collectAsState()
    val isLoading = bannerViewModel.isLoading
    val errorMessage = bannerViewModel.errorMessage
    val categories = sampleCategories
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(pageCount = { categories.size })
    val pagerStateBanner = rememberPagerState(pageCount = { banners.size })

    // Sync pager and tab selection
    LaunchedEffect(selectedTabIndex) {
        pagerState.scrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    Scaffold (
        topBar = {
            TAppBar(
                title = {
                    TSubAppbarHome(
                      onAvatarClick = {
                            navController.navigate(Screen.Setting.route)
                      }
                    )
                },
                navController = navController
            )
        },
        bottomBar = {
            NavigationMenu(
                navController = navController
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding))
         {
            //banner and search
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
            Spacer(modifier = Modifier.height(TSizes.sm))
            // Category tabs
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
            Spacer(modifier = Modifier.height(TSizes.sm))
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) { page ->
                val category = categories[page]
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(26.dp))
                ) {
                    Image(
                        painter = painterResource(id = category.imageRes),
                        contentDescription = category.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f))
                                )
                            )
                    )
                    Text(
                        text = category.title,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(18.dp)
                    )
                }
            }
        }
    }
}