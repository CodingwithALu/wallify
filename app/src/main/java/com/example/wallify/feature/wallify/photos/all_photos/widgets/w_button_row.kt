package com.example.wallify.feature.wallify.photos.all_photos.widgets

import BottomSheetSet
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallify.R
import com.example.wallify.feature.wallify.favorites.FavoritesViewModel
import com.example.wallify.feature.wallify.home.model.Photos
import com.example.wallify.feature.wallify.photos.viewmodel.PhotosViewModel

@SuppressLint("ObsoleteSdkInt")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonRow(
    item: Photos,
    navController: NavController,
    animatedAlpha: Float = 1f,
    context: Context
) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val photosViewModel: PhotosViewModel = hiltViewModel()
    var showBottomSheet by remember { mutableStateOf(false) }
    val favorites by viewModel.favorites.collectAsState()
    val isFavorite = favorites.any { it.id == item.id }
    LaunchedEffect(Unit) {
        val channelId = "download_channel"
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Download Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
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
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.4f))
            ) {
                IconButton(onClick = {
                    photosViewModel.downloadImageWithNotification(item.urls.raw,
                        successMsg = "Image Downloaded Successfully",
                        errorMsg = "Error Downloading Image")
                }) {
                    Icon(
                        painter = painterResource(R.drawable.elements_down),
                        contentDescription = "Download",
                        tint = Color.White.copy(alpha = animatedAlpha)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.4f))
            ) {
                IconButton(onClick = {
                    // Share functionality
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, item.urls.raw)
                    }
                    val chooser = Intent.createChooser(shareIntent, "Share Wallpaper")
                    context.startActivity(chooser)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.share_54dp),
                        contentDescription = "Share",
                        tint = Color.White.copy(alpha = animatedAlpha)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(if(isFavorite)Color.White.copy(alpha = 0.9f) else Color.White.copy(alpha = 0.4f))
            ) {
                IconButton(onClick = {
                    if (isFavorite) viewModel.removeFavorite(item)
                    else viewModel.saveFavorite(item)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.heart),
                        contentDescription = "Dislike",
                        tint =  Color.White.copy(alpha = animatedAlpha)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.4f))
            ) {
                IconButton(onClick = { showBottomSheet = true }) {
                    Icon(
                        painter = painterResource(R.drawable.wallpaper_slideshow_54dp),
                        contentDescription = "Set Wallpaper",
                        tint = Color.White.copy(alpha = animatedAlpha)
                    )
                }
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
