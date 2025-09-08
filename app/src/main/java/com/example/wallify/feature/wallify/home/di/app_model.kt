package com.example.wallify.feature.wallify.home.di

import com.example.wallify.feature.wallify.home.repository.BannerApi
import com.example.wallify.feature.wallify.home.repository.BannerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun provideBannerApi(): BannerApi {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.5:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BannerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBannerRepository(bannerApi: BannerApi): BannerRepository {
        return BannerRepository(bannerApi)
    }
}