package com.example.wallify.common.widgets.products

import androidx.compose.foundation.background
import com.example.wallify.common.widgets.images.TRoundedImage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallify.R
import com.example.wallify.feature.wallify.favorites.FavoritesViewModel
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.utlis.constants.TSizes
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WProductCardVertical(
    item: Image,
    onclick: (Image) -> Unit = {},
    viewModel: FavoritesViewModel = hiltViewModel()
){
    val favorites by viewModel.favorites.collectAsState()
    val isFavorite = favorites.any { it.id_image == item.id_image }

    Box(modifier = Modifier
        .height(280.dp)
        .width(80.dp)
        .background(Color.Black)) {
        TRoundedImage(
            imageUrl = item.subImage.first().url,
            isNetworkImage = true,
            onPressed = { onclick(item) },
            fit = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(TSizes.xs / 2)
        )
        IconButton(
            onClick = {
                if (isFavorite) viewModel.removeFavorite(item)
                else viewModel.saveFavorite(item)
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(6.dp)
                .size(28.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.heart),
                contentDescription = "favorite",
                tint = if (isFavorite) Color.White else Color.Gray,
            )
        }
        Text(
            text = item.title,
            color = Color.White,
            fontSize = 13.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(start = 6.dp, bottom = 12.dp, end = 6.dp)
        )
    }

}