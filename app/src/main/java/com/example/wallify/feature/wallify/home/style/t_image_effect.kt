package com.example.wallify.common.widgets.shimmer

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.wallify.utlis.constants.TSizes

@SuppressLint("FrequentlyChangingValue")
@Composable
fun TImageVerticalEffect(
    onScroll: (isScrollingUp: Boolean) -> Unit
) {
    val listState = rememberLazyListState()
    val lastPosition = remember { mutableStateOf(Pair(0, 0)) }
    LaunchedEffect(
        listState.isScrollInProgress,
        listState.firstVisibleItemIndex,
        listState.firstVisibleItemScrollOffset
    ) {
        val currentIndex = listState.firstVisibleItemIndex
        val currentOffset = listState.firstVisibleItemScrollOffset
        val lastIndex = lastPosition.value.first
        val lastOffset = lastPosition.value.second

        if (listState.isScrollInProgress) {
            val isScrollingUp = currentIndex < lastIndex ||
                    (currentIndex == lastIndex && currentOffset < lastOffset)
            val isScrollingDown = currentIndex > lastIndex ||
                    (currentIndex == lastIndex && currentOffset > lastOffset)
            if (isScrollingUp) onScroll(true)
            else if (isScrollingDown) onScroll(false)
            lastPosition.value = Pair(currentIndex, currentOffset)
        }
    }
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(TSizes.xs)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(TSizes.xs)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(TSizes.xs)
                ) {
                    TShimmerEffect(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3f),

                        )
                    TShimmerEffect(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.4f),
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(TSizes.xs)
                ) {
                    TShimmerEffect(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.4f),

                        )
                    TShimmerEffect(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3f),
                    )
                }
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(TSizes.xs)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(TSizes.xs)
                ) {
                    TShimmerEffect(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3f),

                        )
                    TShimmerEffect(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.4f),
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(TSizes.xs)
                ) {
                    TShimmerEffect(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.4f),

                        )
                    TShimmerEffect(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3f),
                    )
                }
            }
        }
    }
}