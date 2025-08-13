package com.example.wallify.feature.wallify.home
import CategoryMasonryList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.wallify.common.widgets.appbar.TAppBar
import com.example.wallify.R
import com.example.wallify.feature.wallify.home.widgets.BannerCarousel
import com.example.wallify.feature.wallify.home.widgets.BellIcon
import com.example.wallify.feature.wallify.home.widgets.BrandHorizontalScroll
import com.example.wallify.feature.wallify.home.widgets.NotificationCount
import com.example.wallify.feature.wallify.home.widgets.TSubAppbarHome
import com.example.wallify.feature.wallify.home.widgets.brandList
import com.example.wallify.utlis.constants.TSizes
import sampleCategories

@Composable
fun HomeScreen(navController: NavController){
    Scaffold (
        topBar = {
            TAppBar(
                title = { TSubAppbarHome() },
                actions = listOf(
                    { NotificationCount(count = 0) },
                    { BellIcon() }
                )

            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
        ) {
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