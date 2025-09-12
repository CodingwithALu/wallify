package com.example.wallify.common.widgets.products

import TRoundedImage
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.core_model.ProductModel
import com.example.wallify.common.widgets.custom_shapes.container.TRoundedContainer
import com.example.wallify.R
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen
import com.google.gson.Gson

@Composable
fun WProductCardVertical(
    item: Image,
    onclick: (Image) -> Unit = {},
){
    Box(modifier = Modifier.height(280.dp).width(80.dp)) {
        TRoundedImage(
            imageUrl = item.url,
            isNetworkImage = true,
            onPressed = {
                onclick(item)
            },
            padding = TSizes.xs/2,
            applyImageRadius = true,
            fit = ContentScale.Crop
        )
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
                    .size(28.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.favorite),
                    contentDescription = "favorite",
                    tint = Color.White
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