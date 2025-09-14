package com.example.wallify.feature.wallify.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_model.ImageItem
import com.example.wallify.feature.wallify.home.model.Image
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
    private val _favorites = MutableStateFlow<List<Image>>(emptyList())
    val favorites: StateFlow<List<Image>> = _favorites.asStateFlow()

    init {
        loadFavorites()
    }

    fun saveFavorite(image: Image) {
        viewModelScope.launch {
            repository.saveFavoriteImage(image)
            loadFavorites()
        }
    }

    fun removeFavorite(image: Image) {
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
