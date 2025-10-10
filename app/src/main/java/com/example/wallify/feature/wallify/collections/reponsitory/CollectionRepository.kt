package com.example.wallify.feature.wallify.collections.reponsitory

import com.example.wallify.feature.wallify.home.model.Collections
import com.example.wallify.feature.wallify.home.model.Topics
import retrofit2.http.GET

interface CollectionApi{
    @GET("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/topics")
    suspend fun getImageFromCollection(): List<Topics>
}
class CollectionRepository (
    private val api: CollectionApi
) {
    // Fetch Image From collections
    suspend fun fetchImage(): List<Topics> {
        return api.getImageFromCollection()
    }
}