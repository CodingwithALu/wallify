package com.example.wallify.feature.wallify.home.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wallify.common.widgets.custom_shapes.container.TCircularContainer
import com.example.wallify.common.widgets.custom_shapes.container.TSearchContainer
import com.example.wallify.feature.wallify.home.model.Banner
import com.example.wallify.ui.theme.inversePrimaryDarkMediumContrast
import com.example.wallify.ui.theme.onTertiaryLight
import com.example.wallify.utlis.constants.TSizes

@Composable
fun BannerCarousel(
    banners: List<Banner>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onBannerClick: ((Banner) -> Unit)? = null
) {
    Box(
        modifier = modifier
            .padding(horizontal = TSizes.defaultSpace)
            .aspectRatio(1.8f)
            .fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(0.dp),
            pageSpacing = 0.dp
        ) { page ->
            AsyncImage(
                model = banners[page].url,
                contentDescription = "Banner",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(24.dp))
                    .let {
                        if (onBannerClick != null) it.clickable { onBannerClick(banners[page]) } else it
                    },
                contentScale = ContentScale.Crop
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        ) {
            repeat(banners.size) { index ->
                val isSelected = pagerState.currentPage == index
                Box(modifier = Modifier.padding(horizontal = TSizes.xs)) {
                    TCircularContainer(
                        width = if (isSelected) TSizes.appBarHeight else TSizes.xs,
                        height = 4.dp,
                        padding = 4.dp,
                        backgroundColor = if (isSelected) onTertiaryLight else inversePrimaryDarkMediumContrast
                    )
                }
            }
        }
    }
}