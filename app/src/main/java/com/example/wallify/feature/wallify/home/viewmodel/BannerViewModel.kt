package com.example.wallify.feature.wallify.home.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.utils.helper.NetworkManager
import com.example.wallify.feature.wallify.home.model.Banner
import com.example.wallify.feature.wallify.home.repository.BannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BannerViewModel @Inject constructor(
    private val bannerRepository: BannerRepository,
    private val networkManager: NetworkManager
) : ViewModel() {
    private val _banners = MutableStateFlow<List<Banner>>(emptyList())
    val banners: StateFlow<List<Banner>> = _banners

    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

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
                val result = bannerRepository.fetchBanners()
                _banners.value = result
                isLoading = false
            } catch (e: Exception) {
                errorMessage = e.message
                _banners.value = emptyList()
                isLoading = false
            }
        }
    }

    init {
        fetchBanners()
        viewModelScope.launch {
            networkManager.isConnected.collect { isConnected ->
                if (isConnected && errorMessage == "No Internet Connection") {
                    fetchBanners()
                }
            }
        }
    }
}
