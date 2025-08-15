package com.example.wallify.feature.wallify.product.product_details

import ProductInformation
import TRoundedImage
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.Toast
import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.core_model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.wallify.R

@Composable
fun ProductDetailsScreen(
    items: ProductModel,
    navController: NavController,
    onSave: () -> Unit = {},
){
    val context = LocalContext.current
    var isSetting by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold  { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TRoundedImage(
                drawableResId = items.imageRes!!,
                fit = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
                    .fillMaxHeight(0.73f)
                    .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
            )

            // close
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
            // information
            ProductInformation(
                items = items,
                onSave = onSave,
                onSet = {
                    if (!isSetting) {
                        scope.launch(Dispatchers.IO) {
                            isSetting = true
                            val bm = getBitmapFromUrl(context, items.url)
                            if (bm != null) {
                                WallpaperManager.getInstance(context).setBitmap(bm)
                                Toast.makeText(context, "Đặt hình nền thành công!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Lỗi tải ảnh", Toast.LENGTH_SHORT).show()
                            }
                            isSetting = false
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomStart)
            )
        }
    }
}
suspend fun getBitmapFromUrl(context: Context, url: String): Bitmap? {
    val request = ImageRequest.Builder(context)
        .data(url)
        .allowHardware(false)
        .build()
    val result = context.imageLoader.execute(request)
    return if (result is SuccessResult) {
        (result.drawable as? BitmapDrawable)?.bitmap
    } else null
}