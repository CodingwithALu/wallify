package com.example.wallify.feature.wallify.home.di

import com.example.wallify.feature.wallify.home.repository.HomeApi
import com.example.wallify.feature.wallify.home.repository.HomeRepository
import com.example.wallify.feature.wallify.product.repository.ProductApi
import com.example.wallify.feature.wallify.product.repository.ProductRepository
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
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(homeApi: HomeApi): HomeRepository {
        return HomeRepository(homeApi)
    }

    // related image
    @Provides
    @Singleton
    fun provideRelatedImageApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRelatedImageRepository(productApi: ProductApi): ProductRepository {
        return ProductRepository(productApi)
    }
}