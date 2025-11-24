package com.example.wallify.feature.wallify.photos.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.utils.helper.NetworkManager
import com.example.wallify.feature.wallify.home.model.Photos
import com.example.wallify.feature.wallify.photos.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val networkManager: NetworkManager
) : ViewModel() {
    //fetch a photo by id
    private val _photo = MutableStateFlow(Photos.empty())
    val photo: StateFlow<Photos> = _photo
    // related image
    private val _allPhotos = MutableStateFlow<List<Photos>>(emptyList())
    val allPhotos: StateFlow<List<Photos>> = _allPhotos
    var isLoading by mutableStateOf(false)
        private set
    // set wallpaper with notification
    @SuppressLint("MissingPermission")
    fun setWallpaperWithNotification(bitmap: Bitmap?, flag: Int, successMsg: String, errorMsg: String) {
        viewModelScope.launch @androidx.annotation.RequiresPermission(android.Manifest.permission.POST_NOTIFICATIONS) {
            isLoading = true
            val result = productRepository.setWallpaper(bitmap, flag)
            isLoading = false
            productRepository.showWallpaperNotification(
                if (result) successMsg else errorMsg
            )
        }
    }
    // get wallpaper
    fun getBitmapFromUrl(url: String?, onResult: (Bitmap?) -> Unit) {
        if (url == null) {
            onResult(null)
            return
        }
        viewModelScope.launch {
            isLoading = true
            val bitmap = productRepository.getBitmapFromUrl(url)
            isLoading = false
            onResult(bitmap)
        }
    }
    fun fetchPhotoById(id: String) {
        viewModelScope.launch {
            isLoading = true
            if (networkManager.checkConnection()) {
                try {
                    val photo = productRepository.fetchPhotoById(id)
                    _photo.value = photo
                    val images = productRepository.fetchRelatedPhotosForQuery(photo.tags.mapNotNull { it.title })
                    _allPhotos.value = images
                } catch (e: HttpException) {
                    e.printStackTrace()
                    _photo.value = Photos.empty()
                } catch (e: Exception) {
                    e.printStackTrace()
                    _photo.value = Photos.empty()
                } finally {
                    isLoading = false
                }
            } else {
                isLoading = false
            }
        }
    }
    fun fetchRelatedPhotosForQuery(id: List<String>) {
        viewModelScope.launch {
            isLoading = true
            if (networkManager.checkConnection()) {
                try {
                    val images = productRepository.fetchRelatedPhotosForQuery(id)
                    _allPhotos.value = images
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    isLoading = false
                }
            } else {
                isLoading = false
            }
        }
    }
    @SuppressLint("InlinedApi")
    fun downloadImageWithNotification(url: String, successMsg: String, errorMsg: String) {
        viewModelScope.launch {
            isLoading = true
            delay(1200)
            val result = productRepository.downloadImage(url)
            isLoading = false

            val hasPermission = ContextCompat.checkSelfPermission(
                productRepository.context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED ||
                Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
            if (hasPermission) {
                productRepository.showDownloadNotification(
                    if (result) successMsg else errorMsg
                )
            } else {
                Log.w("PhotosViewModel", "POST_NOTIFICATIONS permission not granted.")
            }
        }
    }
}