package com.example.wallify.feature.wallify.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.utils.helper.NetworkManager
import com.example.wallify.feature.wallify.home.model.Photos
import com.example.wallify.feature.wallify.home.model.Topics
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
) : ViewModel() {
    private val _topics = MutableStateFlow<List<Topics>>(emptyList())
    val topics: StateFlow<List<Topics>> = _topics
    private val _photosByTopics = MutableStateFlow<List<Photos>>(emptyList())
    val photosByTopics: StateFlow<List<Photos>> = _photosByTopics
    var isLoading by mutableStateOf(false)
        private set
    init {
        fetchTopics()
    }

    fun fetchTopics() {
        viewModelScope.launch {
            isLoading = true
            if (!networkManager.checkConnection()) {
                isLoading = false
                return@launch
            }
            try {
                val result = homeRepository.fetchCategories()
                _topics.value = result
                isLoading = false
            } catch (e: Exception) {
                _topics.value = emptyList()
                isLoading = false
            }
        }
    }

    fun fetchPhotosForTopics(idCate: String) {
        viewModelScope.launch {
            isLoading = true
            if (!networkManager.checkConnection()) {
                isLoading = false
                return@launch
            }
            try {
                val photos = homeRepository.fetchPhotosByTopics(idCate)
                _photosByTopics.value = photos
                isLoading = false
            } catch (e: Exception) {
                _photosByTopics.value = emptyList()
                isLoading = false
            }
        }
    }
}