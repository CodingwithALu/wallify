package com.example.wallify.feature.wallify.search.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.utils.helper.NetworkManager
import com.example.wallify.feature.wallify.home.model.Photos
import com.example.wallify.feature.wallify.search.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val networkManager: NetworkManager,
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _searchPhotos = MutableStateFlow<List<Photos>>(emptyList())
    val searchPhotos : StateFlow<List<Photos>> = _searchPhotos
    var isLoading by mutableStateOf(false)
        private set
    // search photos
    fun searchPhotos(query: String){
        viewModelScope.launch {
            isLoading = true
            if (!networkManager.checkConnection()){
                isLoading = false
                return@launch
            }
            try {
                val result = searchRepository.searchPhotos(query)
                _searchPhotos.value = result
                isLoading = false
            } catch (e: Exception){
                _searchPhotos.value = emptyList()
                isLoading = false
            }
        }
    }
}