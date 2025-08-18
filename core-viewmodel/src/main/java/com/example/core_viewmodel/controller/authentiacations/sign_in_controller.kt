package com.example.core_viewmodel.controller.authentiacations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.reponsitory.AuthenticationRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    application: Application,
    private val authRepo: AuthenticationRepository,
    private val userController: UserController,
    private val networkManager: NetworkManager
) : AndroidViewModel(application) {

    val rememberMe = mutableStateOf(false)
    val hidePassword = mutableStateOf(true)
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    // Form validation logic should be handled at Compose layer

    // Email & Password Sign In
    fun emailAndPasswordSignIn(onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        viewModelScope.launch {
            try {
                FullScreenLoader.openLoadingDialog(
                    "Logging you in...",
                    ImageStrings.docerAnimation
                )

                val isConnected = networkManager.isConnected()
                if (!isConnected) {
                    FullScreenLoader.stopLoading()
                    onError("No internet connection.")
                    return@launch
                }
                // Save Remember Me (implement using DataStore/SharedPreferences if needed)
                // ...

                authRepo.loginWithEmailAndPassword(email.value.trim(), password.value.trim())
                FullScreenLoader.stopLoading()
                onSuccess() // Navigate or update UI state
            } catch (e: Exception) {
                FullScreenLoader.stopLoading()
                Loaders.errorSnackBar("Oh Snap", e.message ?: "Unknown error")
                onError(e.message ?: "Unknown error")
            }
        }
    }

    // Google Sign-In (token needs to be passed in from UI after Google flow)
    fun googleSignIn(
        idToken: String,
        accessToken: String,
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                FullScreenLoader.openLoadingDialog(
                    "Logging you in...",
                    ImageStrings.docerAnimation
                )

                val isConnected = networkManager.isConnected()
                if (!isConnected) {
                    FullScreenLoader.stopLoading()
                    onError("No internet connection.")
                    return@launch
                }

                val userCredentials = authRepo.signInWithGoogle(idToken, accessToken)
                userController.saveUserRecord(userCredentials.user)
                FullScreenLoader.stopLoading()
                onSuccess()
            } catch (e: Exception) {
                FullScreenLoader.stopLoading()
                Loaders.errorSnackBar("Oh Snap", e.message ?: "Unknown error")
                onError(e.message ?: "Unknown error")
            }
        }
    }

    // Đăng xuất tài khoản
    fun logout(onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        viewModelScope.launch {
            try {
                FullScreenLoader.openLoadingDialog(
                    "Đang đăng xuất...",
                    ImageStrings.docerAnimation
                )
                authRepo.logout()
                // Xóa thông tin Remember Me nếu có
                // ...
                FullScreenLoader.stopLoading()
                Loaders.successSnackBar(
                    title = "Thành công",
                    message = "Bạn đã đăng xuất thành công."
                )
                onSuccess()
            } catch (e: Exception) {
                FullScreenLoader.stopLoading()
                Loaders.errorSnackBar("Lỗi", e.message ?: "Unknown error")
                onError(e.message ?: "Unknown error")
            }
        }
    }
}