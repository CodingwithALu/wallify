package com.example.wallify.feature.wallify.home.repository

import com.example.wallify.feature.wallify.home.model.Banner
import retrofit2.http.GET

interface BannerApi {
    @GET("banners")
    suspend fun getBanners(): List<Banner>
}

class BannerRepository(
    private val api: BannerApi
) {
    suspend fun fetchBanners(): List<Banner> {
        return api.getBanners()
    }
}
