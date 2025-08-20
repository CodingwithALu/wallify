// kotlin
package com.example.core_viewmodel.controller.personalization

import android.app.Application
import android.net.wifi.hotspot2.pps.Credential
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.core_model.UserModel
import com.example.core_viewmodel.reponsitory.AuthenticationRepository
import com.example.core_viewmodel.reponsitory.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthenticationRepository
) : ViewModel() {

    var user by mutableStateOf(UserModel.empty())
        private set
    var profileLoading by mutableStateOf(false)
        private set

    init {
        fetchUserRecord()
    }

    fun fetchUserRecord() {
        viewModelScope.launch {
            profileLoading = true
            try {
                val userId = authRepository.authUser?.uid ?: ""
                user = if (userId.isNotEmpty()) {
                    userRepository.fetchUserDetails(userId)
                } else {
                    UserModel.empty()
                }
            } catch (e: Exception) {
                user = UserModel.empty()
            } finally {
                profileLoading = false
            }
        }
    }

    fun saveUserRecord(userCredentials: Credential.UserCredential?) {
        viewModelScope.launch {
            try {
                fetchUserRecord()
//                if (user.id.isNotEmpty()) {
//                    userCredentials?.let { creds ->
//                        val nameParts = UserModel.nameParts(creds.user?.displayName ?: "")
//                        val username = UserModel.generateUsername(creds.user?.displayName ?: "")
//                        val newUser = UserModel(
//                            id = creds.user?.uid ?: "",
//                            username = username,
//                            email = creds.user?.email ?: "",
//                            firstName = nameParts.getOrNull(0) ?: "",
//                            lastName = nameParts.drop(1).joinToString(" ").takeIf { it.isNotBlank() } ?: "",
//                            phoneNumber = creds.user?.phoneNumber ?: "",
//                            profilePicture = creds.user?.photoUrl?.toString() ?: ""
//                        )
//                        userRepository.saveUserRecord(newUser)
//                        user = newUser
//                    }
//                }
            } catch (e: Exception) {
//                TLoaders.warningSnackBar(
//                    title = "Data not saved",
//                    message = "Failed to save user data"
//                )
            }
        }
    }

    var showDeleteDialog by mutableStateOf(false)
        private set

    fun openDeleteAccountDialog() {
        showDeleteDialog = true
    }

    fun dismissDeleteAccountDialog() {
        showDeleteDialog = false
    }

//    fun uploadUserProfilePicture(onSuccess: () -> Unit = {}) {
//        viewModelScope.launch {
//            try {
//                // val image = pickImage() // Implement in your Composable/UI
//                val image = null // Replace with actual image file
//                image?.let {
//                    imageUploading = true
//                    val imageUrl = userRepository.uploadImage("Users/Images/Profile", it)
//                    val userId = user.id
//                    if (userId.isNotEmpty()) {
//                        userRepository.updateSingleField(userId, mapOf("profilePicture" to imageUrl))
//                        user = user.copy(profilePicture = imageUrl)
////                        TLoaders.successSnackBar(
////                            title = "Congratulations",
////                            message = "Profile image updated"
////                        )
//                        onSuccess()
//                    }
//                }
//            } catch (e: Exception) {
////                TLoaders.errorSnackBar(
////                    title = "Oh snap",
////                    message = "Something went wrong: $e"
////                )
//            } finally {
//                imageUploading = false
//            }
//        }
//    }
}