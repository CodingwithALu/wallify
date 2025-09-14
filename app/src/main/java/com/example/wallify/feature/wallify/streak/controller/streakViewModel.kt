package com.example.wallify.feature.wallify.streak.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.utils.helper.NetworkManager
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.feature.wallify.streak.reponsitory.StreakRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreakViewModel @Inject constructor(
    private val streakRepository: StreakRepository,
    private val networkManager: NetworkManager
): ViewModel() {
    private val _streak = MutableStateFlow<List<Image>>(emptyList())
    val streak: StateFlow<List<Image>> = _streak
    private val _streakByPrice = MutableStateFlow<List<Image>>(emptyList())
    val streakByPrice: StateFlow<List<Image>> = _streakByPrice
    var isLoading by mutableStateOf(false)
        private set

    init {
        fetchStreak()
    }
    fun fetchStreak() {
        viewModelScope.launch {
            isLoading = true
            if (networkManager.checkConnection()) {
                try {
                    val images = streakRepository.fetchStreakPointPrice()
                    _streak.value = images
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    isLoading = false
                }
            } else {
                isLoading = false
            }
        }
    }
    fun fetchStreakByPrice(price: Int) {
        viewModelScope.launch {
            isLoading = true
            if (networkManager.checkConnection()) {
                try {
                    val images = streakRepository.fetchStreakPointByPrice(price)
                    _streakByPrice.value = images
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    isLoading = false
                }
            } else {
                isLoading = false
            }
        }
    }
}