package com.example.wallify.feature.personalization.setting.reponsitory
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface SettingApi {
    @PUT("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/user/{userId}/background")
    suspend fun updateUserBackground(
        @Path ("userId") userId: String,
        @Header("Content-Type") contentType: String,
        @Header("x-filename") fileName: String,
        @Body image: RequestBody
    ): Response<String>
}