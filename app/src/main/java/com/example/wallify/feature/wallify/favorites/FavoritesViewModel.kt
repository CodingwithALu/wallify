package com.example.wallify.feature.wallify.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.feature.wallify.home.model.Photos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: FavoritesRepository
) : ViewModel() {
    private val _favorites = MutableStateFlow<List<Photos>>(emptyList())
    val favorites: StateFlow<List<Photos>> = _favorites.asStateFlow()

    init {
        loadFavorites()
    }

    fun saveFavorite(photo: Photos) {
        viewModelScope.launch {
            repository.saveFavoriteImage(photo)
            loadFavorites()
        }
    }

    fun removeFavorite(image: Photos) {
        viewModelScope.launch {
            repository.removeFavoriteImage(image)
            loadFavorites()
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = repository.getFavoriteImages().first()
        }
    }
}
