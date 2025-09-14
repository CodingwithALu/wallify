package com.example.wallify.common.widgets.images

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.wallify.common.widgets.shimmer.TShimmerEffect

@Composable
fun TRoundedImage(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    fit: ContentScale = ContentScale.Fit,
    isNetworkImage: Boolean = false,
    onPressed: (() -> Unit)? = null,
    drawableResId: Int? = null
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .clickable(enabled = onPressed != null) { onPressed?.invoke() },
        contentAlignment = Alignment.Center
    ) {
        if (isNetworkImage && imageUrl.isNotEmpty()) {
            val painter = rememberAsyncImagePainter(imageUrl)
            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    TShimmerEffect(
                    )
                }
                is AsyncImagePainter.State.Error -> {
                    Log.e("CoilError", "Lỗi load ảnh: $imageUrl")
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Image error"
                    )
                }
                else -> {
                }
            }
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = fit,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            val localPainter: Painter =
                if (drawableResId != null) {
                    painterResource(id = drawableResId)
                } else {
                    rememberAsyncImagePainter(
                        model = ImageRequest.Builder(context)
                            .data("file:///android_asset/$imageUrl")
                            .build()
                    )
                }
            Image(
                painter = localPainter,
                contentDescription = null,
                contentScale = fit,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}