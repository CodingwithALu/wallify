package com.example.wallify.feature.wallify.search.repository

import com.example.wallify.feature.wallify.home.model.Photos
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    // search photo
    @GET("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/search/photos")
    suspend fun getSearchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): List<Photos>
}
class SearchRepository(
    private  val api: SearchApi
) {
    // search photos
    suspend fun searchPhotos(query: String, page: Int = 1) : List<Photos>{
        return api.getSearchPhotos(query, page)
    }
}