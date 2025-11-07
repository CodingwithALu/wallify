package com.example.wallify.feature.personalization.setting.controller

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.utils.helper.NetworkManager
import com.example.wallify.feature.personalization.setting.reponsitory.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repository: SettingRepository,
    private val networkManager: NetworkManager
) : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set
    var uploadResult by mutableStateOf<String?>(null)
    fun sendFeedback(userEmail: String) {
        repository.sendFeedback(userEmail)
    }

    // Changed: accept Uri and pass to repository
    fun updateUrlBackGround(userId: String, imageUri: Uri?) {
        if (imageUri == null) return
        viewModelScope.launch {
            isLoading = true
            try {
                val response = repository.updateUrlBackGround(userId, imageUri)
                uploadResult = if (response.isSuccessful) response.body() else "Upload thất bại"
            } catch (e: Exception) {
                uploadResult = e.message
            } finally {
                isLoading = false
            }
        }
    }
}