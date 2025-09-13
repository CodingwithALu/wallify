package com.example.wallify.feature.wallify.product.product_set_wallpaper

import WAppBarCenter
import android.app.WallpaperManager
import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallify.R
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.utlis.constants.getBitmapFromUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

@Composable
fun ProductSetsScreen(
    items: Image,
    navController: NavController,
) {
    val context = LocalContext.current
    var showBox by remember { mutableStateOf(true) }
    val wallpaperManager = WallpaperManager.getInstance(context)
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Load bitmap asynchronously
    LaunchedEffect(items.url) {
        bitmap = withContext(Dispatchers.IO) {
            getBitmapFromUrl(context, items.url)
        }
    }

    Scaffold(
        topBar = {
            if (showBox){
                WAppBarCenter(
                    showBackArrow = true,
                    title = {
                        Text(items.title)
                    },
                    leadingOnPressed = {
                        navController.popBackStack()
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = items.url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        showBox = !showBox
                    }
            )
            if (showBox) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .align(Alignment.BottomCenter)
                        .padding(bottom = innerPadding.calculateBottomPadding())
                        .background(Color.Transparent)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    isLoading = true
                                    val result = withContext(Dispatchers.IO) {
                                        bitmap?.let {
                                            try {
                                                wallpaperManager.setBitmap(
                                                    it,
                                                    null,
                                                    true,
                                                    WallpaperManager.FLAG_LOCK
                                                )
                                                true
                                            } catch (e: Exception) { false }
                                        } ?: false
                                    }
                                    isLoading = false
                                    Toast.makeText(
                                        context,
                                        if (result) "Áp dụng ảnh nền màn hình khóa thành công!" else "Lỗi áp dụng ảnh nền",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.lock_56dp),
                                contentDescription = null
                            )
                        }
                        IconButton(
                            onClick = {
                                scope.launch {
                                    isLoading = true
                                    val result = withContext(Dispatchers.IO) {
                                        bitmap?.let {
                                            try {
                                                wallpaperManager.setBitmap(
                                                    it,
                                                    null,
                                                    true,
                                                    WallpaperManager.FLAG_SYSTEM
                                                )
                                                true
                                            } catch (e: Exception) { false }
                                        } ?: false
                                    }
                                    isLoading = false
                                    Toast.makeText(
                                        context,
                                        if (result) "Áp dụng ảnh nền màn hình chính thành công!" else "Lỗi áp dụng ảnh nền",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.home),
                                contentDescription = null,
                            )
                        }
                        Box {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        isLoading = true
                                        val result = withContext(Dispatchers.IO) {
                                            bitmap?.let {
                                                try {
                                                    wallpaperManager.setBitmap(
                                                        it,
                                                        null,
                                                        true,
                                                        WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK
                                                    )
                                                    true
                                                } catch (e: Exception) { false }
                                            } ?: false
                                        }
                                        isLoading = false
                                        Toast.makeText(
                                            context,
                                            if (result) "Áp dụng ảnh nền cho cả hai màn hình thành công!" else "Lỗi áp dụng ảnh nền",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.lock_56dp),
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
            if (isLoading){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
