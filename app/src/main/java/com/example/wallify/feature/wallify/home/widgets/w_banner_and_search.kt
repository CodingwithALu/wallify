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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wallify.common.widgets.custom_shapes.container.TCircularContainer
import com.example.wallify.common.widgets.custom_shapes.container.TSearchContainer
import com.example.wallify.ui.theme.inversePrimaryDarkMediumContrast
import com.example.wallify.ui.theme.onTertiaryLight

@Composable
fun BannerCarousel(
    banners: List<Int>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onBannerClick: ((Int) -> Unit)? = null
) {
    Box(
        modifier = modifier
            .aspectRatio(1.6f)
            .fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(0.dp),
            pageSpacing = 0.dp
        ) { page ->
            Image(
                painter = painterResource(id = banners[page]),
                contentDescription = "Banner $page",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(36.dp))
                    .let {
                        if (onBannerClick != null) it.clickable { onBannerClick(page) } else it
                    }
            )
        }
        // Search Bar Overlay
        TSearchContainer(
            icon = {
                Image(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            text = "Search",
            modifier = Modifier.padding(top = 24.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        ) {
            repeat(banners.size) { index ->
                val isSelected = pagerState.currentPage == index
                TCircularContainer(
                    width = 20.dp,
                    height = 4.dp,
                    padding = 4.dp,
                    backgroundColor = if (isSelected) onTertiaryLight else inversePrimaryDarkMediumContrast
                )
            }
        }
    }
}