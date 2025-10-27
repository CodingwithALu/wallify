package com.example.core_viewmodel.repository

import com.example.core_model.UserModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @POST("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/user")
    suspend fun createUser(
        @Body request: UserModel
    )
    @GET("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/user/{userId}")
    suspend fun getUserByIdToken(
        @Path("userId") idToken: String
    ): UserModel
}
class UserRepository(
    private val userApi: UserApi
) {
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
    // Fetch user by ID token
    suspend fun fetchUserByIdToken(idToken: String): UserModel {
        return userApi.getUserByIdToken(idToken)
    }
}