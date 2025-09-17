package com.example.wallify.feature.wallify.streak.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wallify.R
import com.example.wallify.common.widgets.custom_shapes.container.TSearchContainer
import com.example.wallify.common.widgets.images.TRoundedImage
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.utlis.constants.TSizes
import kotlin.math.abs

@SuppressLint("FrequentlyChangingValue")
@Composable
fun CenterFocusedCarousel(
    images: List<Image>,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val bigItemWidth = 264.dp
    val smallItemWidth = 50.dp
    val spacing = 12.dp

    // Tính toán width carousel vừa đủ hiển thị 3 item
    val carouselWidth = bigItemWidth + smallItemWidth * 2 + spacing * 2

    // Tìm index giữa
    val middleIndex = images.size / 2
    val density = LocalDensity.current

    LaunchedEffect(images.size) {
        if (images.size > 2) {
            val offsetPx = with(density) { ((bigItemWidth/2).toPx() - carouselWidth.toPx()/2 + spacing.toPx()) }
            listState.scrollToItem(middleIndex, offsetPx.toInt())
        }
    }
    Box(modifier = modifier.height(260.dp)
        .width(carouselWidth)) {
        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(TSizes.sm),
            modifier = Modifier.height(260.dp)
        ) {
            itemsIndexed(images) { index, item ->
                val itemWidthPx = with(LocalDensity.current) { bigItemWidth.toPx() }
                val smallItemWidthPx = with(LocalDensity.current) { smallItemWidth.toPx() }
                val spacingPx = with(LocalDensity.current) { spacing.toPx() }

                // Tính offset của item theo index
                val itemOffsetPx = index * (itemWidthPx + spacingPx) - listState.firstVisibleItemIndex * (itemWidthPx + spacingPx) - listState.firstVisibleItemScrollOffset

                val viewportCenterPx = (listState.layoutInfo.viewportEndOffset - listState.layoutInfo.viewportStartOffset) / 2f
                val itemCenterPx = itemOffsetPx + itemWidthPx / 2f
                val distanceFromCenter = abs(viewportCenterPx - itemCenterPx)
                val maxDistancePx = itemWidthPx * 2
                val fraction = (1f - (distanceFromCenter / maxDistancePx)).coerceIn(0f, 1f)
                val widthDp = lerp(smallItemWidth.value, bigItemWidth.value, fraction).dp

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0xFF1B2223))
                        .shadow(8.dp, RoundedCornerShape(24.dp))
                        .width(widthDp),
                    contentAlignment = Alignment.Center
                ) {
                    TRoundedImage(
                        imageUrl = item.subImage.first().url,
                        isNetworkImage = true,
                        modifier = Modifier.fillMaxSize()
                    )
                    if (fraction > 0.6f) { // gần với tâm thì hiện button
                        TSearchContainer(
                            modifier = Modifier.height(56.dp),
                            icon = {
                                Image(
                                    painter = painterResource(R.drawable.electric_bolt_54dp),
                                    contentDescription = "public_01"
                                )
                            },
                            text = "Watch Ad ",
                        )
                    }
                }
            }
        }
    }
}
// Hàm nội suy tuyến tính (linear interpolation)
fun lerp(start: Float, end: Float, fraction: Float): Float {
    return start + (end - start) * fraction
}