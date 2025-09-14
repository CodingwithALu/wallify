package com.example.wallify.feature.wallify.streak.reponsitory

import com.example.wallify.feature.wallify.home.model.Image
import retrofit2.http.GET
import retrofit2.http.Path

interface StreakApi {
    @GET("/images/streak-point-price/first-by-price")
    suspend fun getStreakPointPrice(): List<Image>
    @GET("/images/streak-point/{price}")
    suspend fun getStreakPointByPrice(@Path("price") price: Int): List<Image>
}
class StreakRepository(
    private val api: StreakApi
) {
    suspend fun fetchStreakPointPrice(): List<Image> {
        return api.getStreakPointPrice()
    }
    suspend fun fetchStreakPointByPrice(price: Int): List<Image> {
        return api.getStreakPointByPrice(price)
    }
}