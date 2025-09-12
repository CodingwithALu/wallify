package com.example.wallify.feature.wallify.home.viewmodel

import com.example.wallify.feature.wallify.home.model.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.utils.helper.NetworkManager
import com.example.wallify.feature.wallify.home.model.Banner
import com.example.wallify.feature.wallify.home.model.Category
import com.example.wallify.feature.wallify.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val networkManager: NetworkManager
): ViewModel(){
    private val _banners = MutableStateFlow<List<Banner>>(emptyList())
    val banners: StateFlow<List<Banner>> = _banners
    private val _category = MutableStateFlow<List<Category>>(emptyList())
    val category: StateFlow<List<Category>> = _category
    private val _imagesByCategory = MutableStateFlow<Map<Int, List<Image>>>(emptyMap())
    val imagesByCategory: StateFlow<Map<Int, List<Image>>> = _imagesByCategory
    private val _allImages = MutableStateFlow<List<Image>>(emptyList())
    val allImages: StateFlow<List<Image>> = _allImages
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchCategory()
        fetchBanners()
        viewModelScope.launch {
            networkManager.isConnected.collect { isConnected ->
                if (isConnected && errorMessage == "No Internet Connection") {
                    fetchBanners()
                }
            }
        }
    }
    // banner
    fun fetchBanners() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            if (!networkManager.checkConnection()) {
                errorMessage = "No Internet Connection"
                isLoading = false
                return@launch
            }
            try {
                val result = homeRepository.fetchBanners()
                _banners.value = result
                isLoading = false
            } catch (e: Exception) {
                errorMessage = e.message
                _banners.value = emptyList()
                isLoading = false
            }
        }
    }
    fun fetchCategory() {
        viewModelScope.launch {
            isLoading = true
            if (!networkManager.checkConnection()){
                isLoading = false
                return@launch
            }
            try {
                val result = homeRepository.fetchCategories()
                _category.value = result
                isLoading = false
            } catch (e: Exception){
                _category.value = emptyList()
                isLoading = false
            }
        }
    }

    fun fetchImagesForCategory(idCate: Int) {
        viewModelScope.launch {
            isLoading = true
            if (!networkManager.checkConnection()) {
                isLoading = false
                return@launch
            }
            try {
                val images = homeRepository.fetchImagesByCategory(idCate)
                _imagesByCategory.value = _imagesByCategory.value.toMutableMap().apply {
                    put(idCate, images)
                }
                isLoading = false
            } catch (e: Exception) {
                _imagesByCategory.value = _imagesByCategory.value.toMutableMap().apply {
                    put(idCate, emptyList())
                }
                isLoading = false
            }
        }
    }

    fun fetchAllImagesForCategory(idCate: Int) {
        viewModelScope.launch {
            if (!networkManager.checkConnection()) return@launch
            try {
                val images = homeRepository.fetchImagesByCategory(idCate)
                _allImages.value = images
            } catch (e: Exception) {
                _allImages.value = emptyList()
            }
        }
    }
}