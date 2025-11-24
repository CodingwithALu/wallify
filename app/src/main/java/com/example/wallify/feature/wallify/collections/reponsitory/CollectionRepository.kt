package com.example.wallify.feature.wallify.collections.reponsitory

import com.example.wallify.feature.wallify.home.model.Collections
import com.example.wallify.feature.wallify.home.model.Photos
import com.example.wallify.feature.wallify.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectionApi{
    @GET("categories")
    suspend fun getCollection(): List<Collections>
    @GET("categories/{id}")
    suspend fun getCollectionById(@Path("id") idCollections: String): Collections
    @GET("categories/{id}/photos")
    suspend fun fetchPhotosByCollectionId(@Path("id") idCollections: String): List<Photos>
}
class CollectionRepository (
    private val api: CollectionApi = ApiClient.collectionApi
) {
    suspend fun fetchCollections(): List<Collections> {
        return withContext(Dispatchers.IO){
            api.getCollection()
        }
    }
    suspend fun fetchCollectionById(idCollections: String): Collections {
        return withContext(Dispatchers.IO){
            api.getCollectionById(idCollections)
        }
    }
    suspend fun fetchPhotosByCollectionId(idCollections: String): List<Photos> {
        return withContext(Dispatchers.IO){
            api.fetchPhotosByCollectionId(idCollections = idCollections)
        }
    }
}