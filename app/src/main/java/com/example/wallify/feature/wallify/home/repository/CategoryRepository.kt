package com.example.wallify.feature.wallify.home.repository

import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.feature.wallify.home.model.Category
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryApi {
    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("images/by-category/{id_cate}")
    suspend fun getImagesByCategory(@Path("id_cate") idCate: Int): List<Image>

    @GET("images")
    suspend fun getAllImages(): List<Image>
}

class CategoryRepository(
    private val api: CategoryApi
) {
    suspend fun fetchCategories(): List<Category> {
        return api.getCategories()
    }

    suspend fun fetchImagesByCategory(idCate: Int): List<Image> {
        return api.getImagesByCategory(idCate)
    }

    suspend fun fetchAllImages(): List<Image> {
        return api.getAllImages()
    }
}