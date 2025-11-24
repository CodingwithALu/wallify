package com.example.wallify.feature.wallify.collections.collectionPhotos.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallify.common.widgets.images.TRoundedImage
import com.example.wallify.feature.wallify.home.model.Photos
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.R
import com.example.wallify.feature.wallify.home.model.Urls
import com.example.wallify.utlis.constants.TextString

@SuppressLint("FrequentlyChangingValue")
@Composable
fun PhotosCollections(
    photos: List<Photos>,
    onScroll: (isScrollingUp: Boolean) -> Unit = {},
    onClickDownloads: (Urls) -> Unit = {},
    onClickAdd: () -> Unit = {},
    onClickFavorite: () -> Unit = {},
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
            .fillMaxSize()
            .padding(horizontal = TSizes.sm),
        verticalArrangement = Arrangement.spacedBy(TSizes.xs)
    ) {
        items(photos) { photo ->
            PhotosCollectionCard(photo = photo,
                onClickDownloads = {
                    onClickDownloads(it)
                },
                onClickAdd = {
                    onClickAdd()
                },
                onClickFavorite = {
                    onClickFavorite()
                }
            )
        }
    }
}
@Composable
fun PhotosCollectionCard(
    photo: Photos,
    onClickDownloads: (Urls) -> Unit = {},
    onClickAdd: () -> Unit = {},
    onClickFavorite: () -> Unit = {},
) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.7f) // Tỉ lệ gần giống ảnh mẫu
                .clip(RoundedCornerShape(12.dp))
        ) {
            // Ảnh chính
            TRoundedImage(
                imageUrl = photo.urls.regular,
                isNetworkImage = true,
                modifier = Modifier.matchParentSize()
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp, end = 16.dp)
            ) {
                IconButton(
                    onClick = {
                        onClickFavorite()
                    },
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.9f), shape = RoundedCornerShape(8.dp))
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder, // hoặc icon trái tim bạn chọn
                        contentDescription = "Favorite",
                        tint = Color.Black
                    )
                }
                IconButton(
                    onClick = {
                        onClickAdd()
                    },
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.9f), shape = RoundedCornerShape(8.dp))
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add, // hoặc icon dấu cộng bạn chọn
                        contentDescription = "Add",
                        tint = Color.Black
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f))
                        )
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp, bottom = 16.dp)
                ) {
                    // Avatar
                    TRoundedImage(
                        imageUrl = photo.user?.profileImage?.small ?: TextString.urlDefaultAvatar,
                        isNetworkImage = true,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // Tên người dùng
                    Text(
                        text = photo.user?.name ?: "Unknown",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    // Nút download
                    IconButton(
                        onClick = {
                            onClickDownloads(photo.urls)
                        },
                        modifier = Modifier
                            .background(
                                Color.White.copy(alpha = 0.8f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .size(36.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.download),
                            contentDescription = "Download",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }