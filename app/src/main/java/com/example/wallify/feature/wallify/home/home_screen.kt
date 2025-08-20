package com.example.wallify.feature.wallify.home
import CategoryMasonryList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wallify.R
import com.example.core_viewmodel.controller.onboarding.OnBoardingViewModel
import com.example.wallify.feature.wallify.home.widgets.BannerCarousel
import com.example.wallify.feature.wallify.home.widgets.BrandHorizontalScroll
import com.example.wallify.feature.wallify.home.widgets.brandList
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen
import sampleCategories

@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               navController: NavController,
){
    Scaffold  { innerPadding ->
        Column (
            modifier = modifier)
         {
            //banner and search
            val banners = listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)
            val pagerState = rememberPagerState(pageCount = { banners.size })
            BannerCarousel(
                banners = banners,
                pagerState = pagerState
            )
            Spacer(modifier = Modifier.height(TSizes.xs))
            // brand
            BrandHorizontalScroll(items = brandList, navController = navController)
            Spacer(modifier = Modifier.height(TSizes.sm))
            // category
            CategoryMasonryList(sampleCategories, navController)
        }
    }

}