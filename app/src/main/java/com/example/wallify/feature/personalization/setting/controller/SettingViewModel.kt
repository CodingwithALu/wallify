package com.example.wallify.feature.personalization.setting.controller

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.wallify.feature.personalization.setting.reponsitory.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repository: SettingRepository
) : ViewModel() {
    fun sendFeedback(userEmail: String) {
        repository.sendFeedback(userEmail)
    }

}