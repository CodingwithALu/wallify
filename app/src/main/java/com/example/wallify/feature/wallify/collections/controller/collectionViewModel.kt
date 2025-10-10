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
): ViewModel() {
    private val _collections = MutableStateFlow<List<Topics>>(emptyList())
    val collections: StateFlow<List<Topics>> = _collections
    var isLoading by mutableStateOf(false)
        private set
    init {
        fetchCollections()
    }
    // all collections
    fun fetchCollections(){
        viewModelScope.launch {
            isLoading = true
            if (!networkManager.checkConnection()){
                isLoading = false
                return@launch
            }
            try {
                val result = collectionRepository.fetchImage()
                _collections.value = result
                isLoading = false
            } catch (e: Exception){
                _collections.value = emptyList()
                isLoading = false
            }
        }
    }

}