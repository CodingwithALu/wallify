package com.example.core_viewmodel.repository

import android.net.Uri
import android.util.Base64
import com.example.core_model.UserModel
import com.example.core_viewmodel.utils.exceptions.TFirebaseException
import com.example.core_viewmodel.utils.exceptions.TFormatException
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.checkerframework.common.reflection.qual.GetClass
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    @POST("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/user")
    suspend fun createUser(
        @Body request: UserModel
    )
}

class UserRepository(
    private val userApi: UserApi
) {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    // Remove user data from Firestore
    suspend fun removeUserRecord(userId: String) {
        try {
            firestore.collection("Users")
                .document(userId)
                .delete()
                .await()
        } catch (e: FirebaseException) {
            throw Exception(TFirebaseException(e.message ?: "unknown").message)
        } catch (e: IllegalArgumentException) {
            throw Exception(TFormatException().message)
        } catch (e: Exception) {
            throw Exception("Something went wrong. Please try again")
        }
    }

    // Save user data to backend server via API
    suspend fun createNewUser(
        idToken: String,
        firstName: String,
        lastName: String,
        email: String,
        urlProfile: String,
        urlBackground: String? = null
    ) {
        val request = UserModel(
            idToken = idToken,
            firstName = firstName,
            lastName = lastName,
            email = email,
            urlProfile = urlProfile,
            urlBackground = urlBackground
        )
        return userApi.createUser(request)
    }
}