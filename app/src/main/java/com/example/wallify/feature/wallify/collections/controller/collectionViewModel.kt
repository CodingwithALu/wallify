package com.example.wallify.feature.wallify.collections.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.utils.helper.NetworkManager
import com.example.wallify.feature.wallify.collections.reponsitory.CollectionRepository
import com.example.wallify.feature.wallify.home.model.Collections
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.feature.wallify.home.model.Photos
import com.example.wallify.feature.wallify.home.model.Topics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val networkManager: NetworkManager,
    private val collectionRepository: CollectionRepository
) : ViewModel() {
    private val _collections = MutableStateFlow<List<Collections>>(emptyList())
    val collections: StateFlow<List<Collections>> = _collections
    private val _photosByCollections = MutableStateFlow<List<Photos>>(emptyList())
    val photosByCollections: StateFlow<List<Photos>> = _photosByCollections
    var isLoading by mutableStateOf(false)
        private set

    init {
        fetchCollections()
    }

    // all collections
    fun fetchCollections() {
        viewModelScope.launch {
            isLoading = true
            if (!networkManager.checkConnection()) {
                isLoading = false
                return@launch
            }
            try {
                val result = collectionRepository.fetchCollections()
                _collections.value = result
                isLoading = false
            } catch (e: Exception) {
                _collections.value = emptyList()
                isLoading = false
            }
        }
    }

    // fetch photos by collection id
    fun fetchPhotosByCollectionId(idCollections: String) {
        viewModelScope.launch {
            isLoading = true
            if (!networkManager.checkConnection()) {
                isLoading = false
                return@launch
            }
            try {
                val result = collectionRepository.fetchPhotosByCollectionId(idCollections)
                _photosByCollections.value = result
                isLoading = false
            } catch (e: Exception) {
                _photosByCollections.value = emptyList()
                isLoading = false
            }
        }
    }

}