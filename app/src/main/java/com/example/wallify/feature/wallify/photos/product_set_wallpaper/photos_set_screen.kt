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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallify.R
import com.example.wallify.common.widgets.shimmer.FastCircularProgressIndicator
import com.example.wallify.feature.wallify.photos.viewmodel.PhotosViewModel
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
        if (isLoading){
            FastCircularProgressIndicator(
                modifier = Modifier.fillMaxSize()
                    .background(Color.Transparent)
            )
        }
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
                                viewModel.setWallpaperWithNotification(
                                    bitmap,
                                    WallpaperManager.FLAG_LOCK,
                                    "Lock screen wallpaper applied successfully!",
                                    "Failed to apply lock screen wallpaper."
                                )
                            },
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.elements_lock),
                                contentDescription = null,
                            )
                        }
                        IconButton(
                            onClick = {
                                viewModel.setWallpaperWithNotification(
                                    bitmap,
                                    WallpaperManager.FLAG_SYSTEM,
                                    "Wallpaper applied to home successfully!",
                                    "Failed to apply wallpaper home."
                                )
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.home),
                                contentDescription = "Apply to both screens",
                            )
                        }
                        IconButton(
                            onClick = {
                                viewModel.setWallpaperWithNotification(
                                    bitmap,
                                    WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK,
                                    "Wallpaper applied to both screens screen!",
                                    "Failed to apply both screens screen wallpaper."
                                )
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.splitscreen_left_56dp),
                                contentDescription = "Apply to home screen",
                            )
                        }
                    }
                }
            }
            if (isLoading) {
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
