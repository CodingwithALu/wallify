package com.example.wallify.feature.wallify.product.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.utils.helper.NetworkManager
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.feature.wallify.home.repository.HomeRepository
import com.example.wallify.feature.wallify.product.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val networkManager: NetworkManager
) : ViewModel() {
    private val _allImages = MutableStateFlow<List<Image>>(emptyList())
    val allImages: StateFlow<List<Image>> = _allImages
    var isLoading by mutableStateOf(false)
        private set

    fun fetchRelatedImages(imageId: Int) {
        viewModelScope.launch {
            isLoading = true
            if (networkManager.checkConnection()) {
                try {
                    val images = productRepository.fetchRelatedImages(imageId)
                    _allImages.value = images
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    isLoading = false
                }
            } else {
                // Handle no internet connection scenario
                isLoading = false
            }
        }
    }
}