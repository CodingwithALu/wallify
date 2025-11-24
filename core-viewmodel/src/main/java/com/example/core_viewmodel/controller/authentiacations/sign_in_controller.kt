package com.example.core_viewmodel.controller.authentiacations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_model.UserModel
import com.example.core_viewmodel.repository.AuthenticationRepository
import com.example.core_viewmodel.repository.UserRepository
import com.example.core_viewmodel.utils.data_store.DataStoreUser
import com.example.core_viewmodel.utils.data_store.GoogleLoginInfo
import com.example.core_viewmodel.utils.helper.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository,
    private val dataStoreUser: DataStoreUser,
    private val networkManager: NetworkManager
) : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set
    private val _user = MutableStateFlow(UserModel.empty())
    val user: StateFlow<UserModel> = _user
    private val _googleLoginInfo = MutableStateFlow(GoogleLoginInfo(false, ""))
    val googleLoginInfo: StateFlow<GoogleLoginInfo> = _googleLoginInfo
    init {
        restoreGoogleLoginInfo()
    }
    fun restoreGoogleLoginInfo() {
        viewModelScope.launch {
            dataStoreUser.getGoogleLoginInfo().collectLatest { info ->
                _googleLoginInfo.value = info
                if (_googleLoginInfo.value.isLoggedIn) {
                    fetchUserByIdToken(_googleLoginInfo.value.userId)
                } else{
                    _user.value = UserModel.empty()
                }
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
                val parts = userName.trim().split("\\s+".toRegex()).filter { it.isNotEmpty() }
                val firstName = if (parts.isNotEmpty()) parts.first() else ""
                val lastName = if (parts.size > 1) parts.drop(1).joinToString(" ") else ""
                val firebaseUser = authResult.user
                dataStoreUser.saveGoogleLoginInfo(true, firebaseUser?.uid ?: "", accessToken)
                userRepository.createNewUser(
                    idToken = firebaseUser?.uid ?: "",
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    urlProfile = avatarUrl
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
            try {
                authenticationRepository.logout()
                dataStoreUser.clearGoogleLoginInfo()
                isLoading = false
            } catch (e: Exception) {
                isLoading = false
            }
        }
    }

    // fetch user by id token
    fun fetchUserByIdToken(idToken: String) {
        viewModelScope.launch {
            isLoading = true
            try {
                if (!networkManager.checkConnection()) {
                    isLoading = false
                    return@launch
                }
                withContext(NonCancellable){
                    val fetchedUser = userRepository.fetchUserByIdToken(idToken)
                    _user.value = fetchedUser
                    isLoading = false
                }
            } catch (e: Exception) {
                isLoading = false
            }
        }
    }
}
