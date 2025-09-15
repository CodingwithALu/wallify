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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wallify.R
import com.example.wallify.common.widgets.custom_shapes.container.TSearchContainer
import com.example.wallify.common.widgets.images.TRoundedImage
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.utlis.constants.TSizes

@SuppressLint("FrequentlyChangingValue")
@Composable
fun CenterFocusedCarousel(images: List<Image>) {
    val listState = rememberLazyListState()

    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(TSizes.md),
        modifier = Modifier.height(260.dp)
    ) {
        itemsIndexed(images) { index, item ->
            // Tính toán vị trí tâm thực tế (dựa trên scroll offset)
            val itemWidth = 240f // width của item lớn nhất
            val minWidth = 50f   // width của item nhỏ nhất
            val offsetPx = listState.firstVisibleItemScrollOffset.toFloat()
            val center = listState.firstVisibleItemIndex + (offsetPx / itemWidth)

            // Khoảng cách từ item đến vị trí trung tâm
            val distance = kotlin.math.abs(center - index)
            // Tỷ lệ scale/chiều rộng: càng gần trung tâm càng lớn
            val widthPx = lerp(minWidth, itemWidth, 1f - distance.coerceIn(0f, 1f))
            val widthDp = widthPx.dp

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
                    imageUrl = item.url,
                    isNetworkImage = true,
                    modifier = Modifier.fillMaxSize()
                )
                if (distance < 0.5f) { // gần với tâm thì hiện button
                    TSearchContainer(
                        modifier = Modifier.height(56.dp),
                        icon = {
                            Image(
                                painter = painterResource(R.drawable.electric_bolt_54dp),
                                contentDescription = "public_01"
                            )
                        },
                        text = "Watch Ad & Claim 1 Coin",
                    )
                }
            }
        }
    }
}
// Hàm nội suy tuyến tính (linear interpolation)
fun lerp(start: Float, end: Float, fraction: Float): Float {
    return start + (end - start) * fraction
}