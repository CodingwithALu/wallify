package com.example.wallify.common.widgets.products

import TRoundedImage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.core_model.ProductModel
import com.example.wallify.common.widgets.custom_shapes.container.TRoundedContainer
import com.example.wallify.R

@Composable
fun WProductCardVertical(
    product: ProductModel,
    onclick: (Int) -> Unit = {}
){
    TRoundedContainer(
        height = 280.dp,
        width = 80.dp,
        content = {
            Box {
                TRoundedImage(
                    drawableResId = product.imageRes,
                    applyImageRadius = true,
                    fit = ContentScale.Crop
                )
                    IconButton(
                        onClick = { onclick(product.id!!.toInt()) },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(6.dp)
                            .size(28.dp)
                    ) {
//                        val isLiked = likedIds!!.contains(product.id!!)
                        Icon(
                            painter = painterResource(R.drawable.favorite),
                            contentDescription = "favorite",
                            tint = Color.White
                        )
                    }
                Text(
                    text = product.title!!,
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
    )
}