package com.example.wallify.feature.wallify.photos.product_set_wallpaper

import WAppBarCenter
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.transform.Transformation
import com.example.wallify.R
import com.example.wallify.common.widgets.shimmer.FastCircularProgressIndicator
import com.example.wallify.feature.wallify.photos.viewmodel.PhotosViewModel
import com.example.wallify.utlis.constants.TSizes

@SuppressLint("ObsoleteSdkInt")
@Composable
fun PhotosSetsScreen(
    url: String?,
    navController: NavController,
) {
    val viewModel: PhotosViewModel = hiltViewModel()
    var showBox by remember { mutableStateOf(true) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val isLoading = viewModel.isLoading
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        val channelId = "wallpaper_channel"
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Wallpaper Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
    LaunchedEffect(url) {
        if (url != null) {
            viewModel.getBitmapFromUrl(url) { it ->
                bitmap = it
            }
        } else {
            bitmap = null
        }
    }
    Scaffold(
        topBar = {
            if (showBox) {
                WAppBarCenter(
                    showBackArrow = true,
                    leadingOnPressed = {
                        navController.popBackStack()
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        showBox = !showBox
                    }
            )
            if (showBox) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = innerPadding.calculateBottomPadding())
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(horizontal = TSizes.defaultSpace),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.5f))
                                .clickable(
                                    onClick = {
                                        viewModel.setWallpaperWithNotification(
                                            bitmap,
                                            WallpaperManager.FLAG_LOCK,
                                            "Lock screen wallpaper applied successfully!",
                                            "Failed to apply lock screen wallpaper."
                                        )
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.elements_lock),
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.5f))
                                .clickable(
                                    onClick = {
                                        viewModel.setWallpaperWithNotification(
                                            bitmap,
                                            WallpaperManager.FLAG_SYSTEM,
                                            "Wallpaper applied to home successfully!",
                                            "Failed to apply wallpaper home."
                                        )
                                    }
                                ),
                            contentAlignment = Alignment.Center) {
                            Icon(
                                painter = painterResource(R.drawable.home),
                                contentDescription = "Apply to both screens",
                                tint = Color.Black,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.5f))
                                .clickable(
                                    onClick = {
                                        viewModel.setWallpaperWithNotification(
                                            bitmap,
                                            WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK,
                                            "Wallpaper applied to both screens screen!",
                                            "Failed to apply both screens screen wallpaper."
                                        )
                                    }
                                ),
                            contentAlignment = Alignment.Center) {
                            Icon(
                                painter = painterResource(R.drawable.splitscreen_left_56dp),
                                contentDescription = "Apply to home screen",
                                tint = Color.Black,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                    Box(modifier = Modifier.height(16.dp)
                        .fillMaxWidth()
                        .background(Color.Transparent))
                }
            }
            if (isLoading) {
                FastCircularProgress()
            }
        }
    }
}
@Composable
fun FastCircularProgress() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
