package com.example.wallify.feature.wallify.home.repository

import com.example.wallify.feature.wallify.home.model.Category
import retrofit2.http.GET

interface CategoryApi {
    @GET("categories")
    suspend fun getCategories(): List<Category>
}
class CategoryRepository(
    private val api: CategoryApi
) {
    suspend fun fetchCategories(): List<Category> {
        return api.getCategories()
    }
}