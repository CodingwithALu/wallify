package com.example.core_viewmodel.controller.authentiacations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.repository.AuthenticationRepository
import com.example.core_viewmodel.repository.UserRepository
import com.example.core_viewmodel.utils.data_store.DataStoreUser
import com.example.core_viewmodel.utils.data_store.GoogleLoginInfo
import com.example.core_viewmodel.utils.helper.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository,
    private val dataStoreUser: DataStoreUser,
    private val networkManager: NetworkManager
) : ViewModel() {
    var emails by mutableStateOf("")
        private set
    var userAvatars by mutableStateOf("")
    var userNames by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var successMessage by mutableStateOf<String?>(null)
    private val _googleLoginInfo = MutableStateFlow(GoogleLoginInfo(false, "", "", ""))
    val googleLoginInfo: StateFlow<GoogleLoginInfo> = _googleLoginInfo
    init {
        restoreGoogleLoginInfo()
    }
    fun restoreGoogleLoginInfo() {
        viewModelScope.launch {
            dataStoreUser.getGoogleLoginInfo().collectLatest { info ->
                _googleLoginInfo.value = info
                emails = info.email
                userNames = info.userName
                userAvatars = info.avatar
            }
        }
    }

    fun loginWithGoogle(idToken: String, accessToken: String, userName: String, email: String, avatarUrl: String, onNavigate: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                if (!networkManager.checkConnection()) {
                    errorMessage = "No Internet Connection"
                    isLoading = false
                    return@launch
                }
                val authResult = authenticationRepository.signInWithGoogle(idToken, accessToken)
                userNames = userName
                emails = email
                userAvatars = avatarUrl
                dataStoreUser.saveGoogleLoginInfo( true, email, userName, avatarUrl)
                val firebaseUser = authResult.user
                userRepository.saveUserToServer(
                    googleId = firebaseUser?.uid ?: "",
                    name = userName,
                    email = email,
                    url = avatarUrl
                )
                isLoading = false
                onNavigate()
            } catch (e: Exception) {
                isLoading = false
                errorMessage = e.message
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            isLoading = true
            emails = ""
            userAvatars = ""
            userNames = ""
            successMessage = null
            errorMessage = null
            try {
                authenticationRepository.logout()
                dataStoreUser.clearGoogleLoginInfo()
                isLoading = false
                successMessage = "Bạn đã đăng xuất thành công."
            } catch (e: Exception) {
                isLoading = false
                errorMessage = e.message
            }
        }
    }
}