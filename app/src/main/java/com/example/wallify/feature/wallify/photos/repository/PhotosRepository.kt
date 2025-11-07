package com.example.wallify.feature.wallify.photos.repository

import android.Manifest
import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.wallify.R
import com.example.wallify.feature.wallify.home.model.Photos
import java.net.URL
import android.os.Build
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    //fetch Photo
    @GET("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/photos/{id}")
    suspend fun getPhotoById(@Path("id") id: String): Photos
    // related image for collections
    @GET("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/photos/random")
    suspend fun getRelatedPhotosForCollections(
        @Query("collections") topics: String,
        @Query("count") count: Int
    ): List<Photos>
    // related image for topics
    @GET("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/photos/random")
    suspend fun getRelatedPhotosForTopics(
        @Query("topics") topics: String,
        @Query("count") count: Int
    ): List<Photos>
    // related image for query
    @GET("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/photos/random")
    suspend fun getRelatedPhotosForQuery(
        @Query("query") topics: String,
        @Query("count") count: Int
    ): List<Photos>
}
class ProductRepository(
    private val api: ProductApi,
    val context: Context
) {
    // fetch photo by id
    suspend fun fetchPhotoById(id: String): Photos {
        return api.getPhotoById(id)
    }

    // related image from collections
    suspend fun fetchRelatedPhotosForCollections(
        imageId: List<String>,
        count: Int = 30
    ): List<Photos> {
        val topicsParam = imageId.joinToString(",")
        return api.getRelatedPhotosForCollections(topicsParam, count)
    }
    suspend fun fetchRelatedPhotosForQuery(queryId: List<String>, count: Int = 30): List<Photos> {
        val query = queryId.joinToString(",")
        return api.getRelatedPhotosForQuery(query, count)
    }

    // set wallpaper for home, lock, or both screens
    fun setWallpaper(bitmap: Bitmap?, flag: Int): Boolean {
        return try {
            bitmap?.let {
                WallpaperManager.getInstance(context).setBitmap(
                    it,
                    null,
                    true,
                    flag
                )
                true
            } ?: false
        } catch (_: Exception) {
            false
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showWallpaperNotification(message: String) {
        val channelId = "wallpaper_channel"
        val notificationId = 1001
        try {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
                || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU // Trước Android 13 không cần quyền này
            ) {
                val builder = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.drawable.logo_app)
                    .setContentTitle("Wallpaper")
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val notificationManager =
                        context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
                    val channel = android.app.NotificationChannel(
                        channelId,
                        "Wallpaper Notifications",
                        android.app.NotificationManager.IMPORTANCE_DEFAULT
                    )
                    notificationManager.createNotificationChannel(channel)
                }
                NotificationManagerCompat.from(context).notify(notificationId, builder.build())
            }
        } catch (e: SecurityException) {
            // Handle permission denied gracefully
        }
    }
    @SuppressLint("ObsoleteSdkInt")
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showDownloadNotification(message: String) {
        val channelId = "download_channel"
        val notificationId = 2001
        try {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
                || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
            ) {
                val builder = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.drawable.logo_app)
                    .setContentTitle("Download")
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val notificationManager =
                        context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
                    val channel = android.app.NotificationChannel(
                        channelId,
                        "Download Notifications",
                        android.app.NotificationManager.IMPORTANCE_DEFAULT
                    )
                    notificationManager.createNotificationChannel(channel)
                }
                NotificationManagerCompat.from(context).notify(notificationId, builder.build())
            }
        } catch (e: SecurityException) {
            // Handle permission denied gracefully
        }
    }
    suspend fun getBitmapFromUrl(url: String): Bitmap? {
        val request = ImageRequest.Builder(context)
            .data(url)
            .allowHardware(false)
            .build()
        val result = context.imageLoader.execute(request)
        return if (result is SuccessResult) {
            (result.drawable as? BitmapDrawable)?.bitmap
        } else null
    }
    @SuppressLint("ObsoleteSdkInt")
    suspend fun downloadImage(url: String): Boolean = withContext(Dispatchers.IO) {
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                val writePermission = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                if (writePermission != PackageManager.PERMISSION_GRANTED) {
                    return@withContext false
                }
            }
            val inputStream = try {
                URL(url).openStream()
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext false
            }
            val filename = "wallify_${System.currentTimeMillis()}.jpg"
            val resolver = context.contentResolver
            val contentValues = android.content.ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, filename)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Wallify")
                }
            }
            val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            if (uri == null) {
                return@withContext false
            }
            val outputStream = resolver.openOutputStream(uri)
            if (outputStream == null) {
                return@withContext false
            }
            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            return@withContext true
        } catch (e: SecurityException) {
            e.printStackTrace()
            return@withContext false
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext false
        }
    }
}