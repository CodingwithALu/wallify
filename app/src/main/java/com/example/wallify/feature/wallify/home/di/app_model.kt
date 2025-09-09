package com.example.wallify.feature.wallify.home.di

import com.example.wallify.feature.wallify.home.repository.BannerApi
import com.example.wallify.feature.wallify.home.repository.BannerRepository
import com.example.wallify.feature.wallify.home.repository.CategoryApi
import com.example.wallify.feature.wallify.home.repository.CategoryRepository
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
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.5:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // category
    @Provides
    @Singleton
    fun provideCategoryApi(retrofit: Retrofit): CategoryApi {
        return retrofit.create(CategoryApi::class.java)
    }
    @Provides
    @Singleton
    fun provideBannerApi(retrofit: Retrofit): BannerApi {
        return retrofit.create(BannerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBannerRepository(bannerApi: BannerApi): BannerRepository {
        return BannerRepository(bannerApi)
    }
    @Provides
    @Singleton
    fun provideCategoryRepository(categoryApi: CategoryApi): CategoryRepository {
        return CategoryRepository(categoryApi)
    }
    // Khi cần thêm API khác, chỉ cần thêm:
    // fun provideOtherApi(retrofit: Retrofit): OtherApi = retrofit.create(OtherApi::class.java)
}