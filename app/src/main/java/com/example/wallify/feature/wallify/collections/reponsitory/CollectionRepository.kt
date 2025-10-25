package com.example.wallify.feature.wallify.collections.reponsitory

import com.example.wallify.feature.wallify.home.model.Collections
import com.example.wallify.feature.wallify.home.model.Photos
import com.example.wallify.feature.wallify.home.model.Topics
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectionApi{
    @GET("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/categories")
    suspend fun getCollection(): List<Collections>
    @GET("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/categories/{id}/photos")
    suspend fun fetchPhotosByCollectionId(@Path("id") idCollections: String): List<Photos>
}
class CollectionRepository (
    private val api: CollectionApi
) {
    // Fetch Image From collections
    suspend fun fetchCollections(): List<Collections> {
        return api.getCollection()
    }
    // Fetch Photos By Collection Id
    suspend fun fetchPhotosByCollectionId(idCollections: String): List<Photos> {
        return api.fetchPhotosByCollectionId(idCollections)
    }
}