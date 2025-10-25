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
        private set
    var userNames by mutableStateOf("")
        private set
    var firstName by mutableStateOf("")
        private set
    var lastName by mutableStateOf("")
        private set
    var isLoading by mutableStateOf(false)
        private set
    private val _googleLoginInfo = MutableStateFlow(GoogleLoginInfo(false))
    val googleLoginInfo: StateFlow<GoogleLoginInfo> = _googleLoginInfo

    init {
        restoreGoogleLoginInfo()
    }

    fun restoreGoogleLoginInfo() {
        viewModelScope.launch {
            dataStoreUser.getGoogleLoginInfo().collectLatest { info ->
                _googleLoginInfo.value = info
            }
        }
    }

    fun loginWithGoogle(
        idToken: String,
        accessToken: String,
        userName: String,
        email: String,
        avatarUrl: String
    ) {
        viewModelScope.launch {
            isLoading = true
            try {
                if (!networkManager.checkConnection()) {
                    isLoading = false
                    return@launch
                }
                val authResult = authenticationRepository.signInWithGoogle(idToken, accessToken)
                userNames = userName
                val parts = userName.trim().split("\\s+".toRegex()).filter { it.isNotEmpty() }
                firstName = if (parts.isNotEmpty()) parts.first() else ""
                lastName = if (parts.size > 1) parts.drop(1).joinToString(" ") else ""
                emails = email
                userAvatars = avatarUrl
                dataStoreUser.saveGoogleLoginInfo(true)
                val firebaseUser = authResult.user
                userRepository.createNewUser(
                    idToken = firebaseUser?.uid ?: "",
                    firstName = firstName,
                    lastName = lastName,
                    email = emails,
                    urlProfile = userAvatars
                )
                isLoading = false
            } catch (e: Exception) {
                isLoading = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            isLoading = true
            emails = ""
            userAvatars = ""
            userNames = ""
            // clear splitted names on logout
            firstName = ""
            lastName = ""
            try {
                authenticationRepository.logout()
                dataStoreUser.clearGoogleLoginInfo()
                isLoading = false
            } catch (e: Exception) {
                isLoading = false
            }
        }
    }
}