package com.example.wallify.feature.wallify.home.repository
import com.example.wallify.feature.wallify.home.model.Photos
import com.example.wallify.feature.wallify.home.model.Topics
import com.example.wallify.feature.wallify.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {
    @GET("topics")
    suspend fun getTopics(): List<Topics>
    @GET("topics/{id_or_slug}/photos")
    suspend fun getPhotosByTopics(@Path("id_or_slug") idTopic: String): List<Photos>
}

class HomeRepository(
    private val api: HomeApi = ApiClient.homApi
) {
    suspend fun fetchCategories(): List<Topics> {
        return withContext(Dispatchers.IO){
            api.getTopics()
        }
    }

    suspend fun fetchPhotosByTopics(id: String): List<Photos> {
        return withContext(Dispatchers.IO){
            api.getPhotosByTopics(id)
        }
    }
}