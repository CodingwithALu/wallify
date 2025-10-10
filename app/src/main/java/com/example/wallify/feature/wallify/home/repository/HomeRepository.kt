package com.example.wallify.feature.wallify.home.repository

import com.example.wallify.feature.wallify.home.model.Banner
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.feature.wallify.home.model.Photos
import com.example.wallify.feature.wallify.home.model.Topics
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {
    @GET("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/topics")
    suspend fun getTopics(): List<Topics>
    @GET("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/topics/{id_or_slug}/photos")
    suspend fun getPhotosByTopics(@Path("id_or_slug") idTopic: String): List<Photos>
}

class HomeRepository(
    private val api: HomeApi
) {
    suspend fun fetchCategories(): List<Topics> {
        return api.getTopics()
    }

    suspend fun fetchPhotosByTopics(id: String): List<Photos> {
        return api.getPhotosByTopics(id)
    }
}