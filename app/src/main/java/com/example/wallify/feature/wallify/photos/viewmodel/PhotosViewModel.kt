package com.example.wallify.feature.wallify.photos.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.utils.helper.NetworkManager
import com.example.wallify.feature.wallify.home.model.Photos
import com.example.wallify.feature.wallify.photos.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    // add error message state to surface HTTP errors to UI
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

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
            _errorMessage.value = null // reset
            if (networkManager.checkConnection()) {
                try {
                    val photo = productRepository.fetchPhotoById(id)
                    _photo.value = photo
                } catch (e: HttpException) {
                    // log HTTP status code (e.g. 403) and expose message
                    Log.w("PhotosViewModel", "HTTP ${e.code()} when fetching photo id=$id: ${e.message()}")
                    e.printStackTrace()
                    _errorMessage.value = when (e.code()) {
                        403 -> "Access denied (403). Resource may be restricted or API key invalid."
                        401 -> "Unauthorized (401). Check API credentials."
                        429 -> "Rate limit exceeded (429). Try later."
                        else -> "Server error ${e.code()}"
                    }
                    _photo.value = Photos.empty()
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Unexpected error: ${e.localizedMessage}"
                    _photo.value = Photos.empty()
                } finally {
                    isLoading = false
                }
            } else {
                isLoading = false
                _errorMessage.value = "No network connection"
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
            val result = productRepository.downloadImage(url)
            isLoading = false
            val hasPermission = androidx.core.content.ContextCompat.checkSelfPermission(
                productRepository.context,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED ||
                android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU
            if (hasPermission) {
                productRepository.showDownloadNotification(
                    if (result) successMsg else errorMsg
                )
            }
        }
    }
}