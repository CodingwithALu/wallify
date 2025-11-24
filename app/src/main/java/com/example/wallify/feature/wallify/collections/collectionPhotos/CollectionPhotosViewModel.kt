package com.example.wallify.feature.wallify.collections.collectionPhotos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.utils.helper.NetworkManager
import com.example.wallify.feature.wallify.collections.reponsitory.CollectionRepository
import com.example.wallify.feature.wallify.home.model.Collections
import com.example.wallify.feature.wallify.home.model.Photos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionPhotosViewModel @Inject constructor(
    private val networkManager: NetworkManager,
    private val collectionRepository: CollectionRepository
) : ViewModel() {
    private val _collections = MutableStateFlow(Collections.empty())
    val collections: StateFlow<Collections> = _collections
    private val _photosByCollections = MutableStateFlow<List<Photos>>(emptyList())
    val photosByCollections: StateFlow<List<Photos>> = _photosByCollections
    var isLoading by mutableStateOf(false)
        private set
    // all collections
    fun fetchCollectionById(id: String) {
        viewModelScope.launch {
            try {
                val result = async { collectionRepository.fetchCollectionById(id) }
                _collections.value = result.await()
                val photos = collectionRepository.fetchPhotosByCollectionId(id)
                _photosByCollections.value = photos
            } catch (e: Exception) {
                _collections.value = Collections.empty()
            }
        }
    }
}