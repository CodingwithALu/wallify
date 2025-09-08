package com.example.wallify.feature.wallify.home
import CategoryMasonryList
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallify.R
import com.example.core_viewmodel.controller.onboarding.OnBoardingViewModel
import com.example.wallify.common.widgets.appbar.TAppBar
import com.example.wallify.feature.wallify.home.widgets.BannerCarousel
import com.example.wallify.feature.wallify.home.widgets.BrandHorizontalScroll
import com.example.wallify.feature.wallify.home.widgets.NotificationIcon
import com.example.wallify.feature.wallify.home.widgets.TSubAppbarHome
import com.example.wallify.feature.wallify.home.widgets.brandList
import com.example.wallify.navigation.NavigationMenu
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen
import com.example.wallify.feature.wallify.home.model.Banner
import com.example.wallify.feature.wallify.home.viewmodel.BannerViewModel
import sampleCategories

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
){
    val bannerViewModel: BannerViewModel = hiltViewModel()
    val banners by bannerViewModel.banners.collectAsState()
    val isLoading = bannerViewModel.isLoading
    val errorMessage = bannerViewModel.errorMessage
    val pagerState = rememberPagerState(pageCount = { banners.size })

    LaunchedEffect(banners) {
        Log.d("BannerList", "Banners: $banners")
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
                    Text("Loading banners...")
                }
                errorMessage != null -> {
                    Log.d("BannerList", "Banners: $errorMessage")
                    Text("Error: $errorMessage")
                }
                banners.isEmpty() -> {
                    Text("Không có banner nào")
                }
                else -> {
                    BannerCarousel(
                        banners = banners,
                        pagerState = pagerState
                    )
                }
            }
            Spacer(modifier = Modifier.height(TSizes.xs))
            // brand
            BrandHorizontalScroll(items = brandList, navController = navController)
            Spacer(modifier = Modifier.height(TSizes.sm))
            // category
            CategoryMasonryList(sampleCategories, navController)
        }
    }
}