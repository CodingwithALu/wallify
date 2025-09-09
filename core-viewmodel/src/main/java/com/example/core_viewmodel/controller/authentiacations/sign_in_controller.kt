package com.example.core_viewmodel.controller.authentiacations

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_model.UserModel
import com.example.core_viewmodel.repository.AuthenticationRepository
import com.example.core_viewmodel.repository.UserRepository
import com.example.core_viewmodel.utils.data_store.GoogleLoginInfo
import com.example.core_viewmodel.utils.data_store.clearGoogleLoginInfo
import com.example.core_viewmodel.utils.data_store.getGoogleLoginInfo
import com.example.core_viewmodel.utils.data_store.saveGoogleLoginInfo
import com.example.core_viewmodel.utils.helper.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository,
    private val networkManager: NetworkManager
) : ViewModel() {
    var emails by mutableStateOf("")
        private set
    var userAvatars by mutableStateOf("")
    var password by mutableStateOf("")
    var userNames by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var successMessage by mutableStateOf<String?>(null)
    // Sử dụng StateFlow để cập nhật tự động
    private val _googleLoginInfo = MutableStateFlow(GoogleLoginInfo(false, "", "", ""))
    val googleLoginInfo: StateFlow<GoogleLoginInfo> = _googleLoginInfo

    fun restoreGoogleLoginInfo(context: Context) {
        viewModelScope.launch {
            getGoogleLoginInfo(context).collectLatest { info ->
                _googleLoginInfo.value = info
                emails = info.email
                userNames = info.userName
                userAvatars = info.avatar
            }
        }
    }
    fun loginWithEmailAndPassword(onNavigate: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                if (!networkManager.checkConnection()) {
                    errorMessage = "No Internet Connection"
                    isLoading = false
                    return@launch
                }
                authenticationRepository.loginWithEmailAndPassword(emails.trim(), password.trim())
                isLoading = false
                onNavigate()
            } catch (e: Exception) {
                isLoading = false
                errorMessage = e.message
            }
        }
    }

    fun loginWithGoogle(context: Context, idToken: String, accessToken: String, userName: String, email: String, avatarUrl: String, onNavigate: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            Log.d("SignInGoogle", "ID Token: $idToken")
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
                // Save DataStore để duy trì trạng thái đăng nhập Google
                saveGoogleLoginInfo(context, true, email, userName, avatarUrl)
                val firebaseUser = authResult.user
                // Lưu thông tin người dùng qua API thay vì Firestore
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

    fun logout(context: Context, onAfterLogout: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            emails = ""
            userAvatars = ""
            userNames = ""
            successMessage = null
            errorMessage = null
            try {
                authenticationRepository.logout()
                clearGoogleLoginInfo(context)
                isLoading = false
                successMessage = "Bạn đã đăng xuất thành công."
                onAfterLogout()
            } catch (e: Exception) {
                isLoading = false
                errorMessage = e.message
            }
        }
    }
}