package com.example.wallify.feature.wallify.product.all_product.widgets

import BottomSheetSet
import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallify.R
import com.example.wallify.feature.wallify.home.model.Image
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonRow(
    item: Image,
    navController: NavController,
    animatedAlpha: Float = 1f,
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .graphicsLayer {
                alpha = animatedAlpha
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                downloadImage(item.url, navController)
            }) {
                Icon(
                    painter = painterResource(R.drawable.download_24dp),
                    contentDescription = "Download",
                    tint = Color.White.copy(alpha = animatedAlpha)
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.share_24dp),
                    contentDescription = "Share",
                    tint = Color.White.copy(alpha = animatedAlpha)
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.thumb_down_24dp),
                    contentDescription = "Dislike",
                    tint = Color.White.copy(alpha = animatedAlpha)
                )
            }
            IconButton(onClick = { showBottomSheet = true }) {
                Icon(
                    painter = painterResource(R.drawable.wallpaper_24dp),
                    contentDescription = "Set Wallpaper",
                    tint = Color.White.copy(alpha = animatedAlpha)
                )
            }
        }
    }
    AnimatedVisibility(
            visible = false,
    enter = slideInVertically(initialOffsetY = { -it }),
    exit = slideOutVertically(targetOffsetY = { -it }),
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            // Nội dung TopSheet ở đây
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Đây là TopSheet")
            }
        }
    }
    if (showBottomSheet){
        BottomSheetSet(
            item = item,
            navController = navController,
            onDismiss = { it ->
                showBottomSheet = it
            }
        )
    }
}
@SuppressLint("ObsoleteSdkInt")
fun downloadImage(url: String, navController: NavController) {
    val context = navController.context
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val inputStream = URL(url).openStream()
            val filename = "wallify_${System.currentTimeMillis()}.jpg"

            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, filename)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Wallify")
                }
            }
            val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            uri?.let {
                resolver.openOutputStream(it)?.use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
                launch(Dispatchers.Main) {
                    Toast.makeText(context, "Tải ảnh thành công!", Toast.LENGTH_SHORT).show()
                }
            } ?: launch(Dispatchers.Main) {
                Toast.makeText(context, "Lỗi khi lưu ảnh!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            launch(Dispatchers.Main) {
                Toast.makeText(context, "Lỗi tải ảnh!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}