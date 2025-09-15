package com.example.core_viewmodel.controller.onboarding

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.utils.data_store.OnboardingDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val onboardingDataStore: OnboardingDataStore
): ViewModel() {
    var currentPageIndex by mutableIntStateOf(0)
        private set
    private val _isFirstTime = MutableStateFlow<Boolean?>(null)
    val isFirstTime: StateFlow<Boolean?> = _isFirstTime.asStateFlow()
    init {
        loadIsFirstTime()
    }
    fun loadIsFirstTime() {
        viewModelScope.launch {
            onboardingDataStore.getFirstTime().collect {
                _isFirstTime.value = it
            }
        }
    }
    fun updatePageIndicator(index: Int) {
        currentPageIndex = index
    }

    fun dotNavigationClick(index: Int) {
        currentPageIndex = index
    }

    fun nextPage(navigateToLogin: () -> Unit) {
        if (currentPageIndex == 2) {
            viewModelScope.launch {
                onboardingDataStore.setFirstTime(false)
                navigateToLogin()
            }
        } else {
            currentPageIndex += 1
        }
    }
    fun skipPage(navigateToLogin: () -> Unit) {
        viewModelScope.launch {
            currentPageIndex = 2
            onboardingDataStore.setFirstTime(false)
            navigateToLogin()
        }
    }
}