package com.example.wallify.feature.wallify.product.repository

import com.example.wallify.feature.wallify.home.model.Image
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    // related image
    @GET("images/related/{image_id}")
    suspend fun getRelatedImages(@Path("image_id") imageId: Int): List<Image>
}
class ProductRepository(
    private val api: ProductApi
) {
    // related image
    suspend fun fetchRelatedImages(imageId: Int): List<Image> {
        return api.getRelatedImages(imageId)
    }
}