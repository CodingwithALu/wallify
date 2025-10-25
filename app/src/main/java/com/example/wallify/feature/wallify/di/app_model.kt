package com.example.wallify.feature.wallify.di

import android.content.Context
import com.example.wallify.feature.personalization.setting.reponsitory.SettingRepository
import com.example.wallify.feature.wallify.collections.reponsitory.CollectionApi
import com.example.wallify.feature.wallify.collections.reponsitory.CollectionRepository
import com.example.wallify.feature.wallify.favorites.FavoritesRepository
import com.example.wallify.feature.wallify.home.repository.HomeApi
import com.example.wallify.feature.wallify.home.repository.HomeRepository
import com.example.wallify.feature.wallify.photos.repository.ProductApi
import com.example.wallify.feature.wallify.photos.repository.ProductRepository
import com.example.wallify.feature.wallify.search.repository.SearchApi
import com.example.wallify.feature.wallify.search.repository.SearchRepository
import com.example.wallify.feature.wallify.streak.reponsitory.StreakApi
import com.example.wallify.feature.wallify.streak.reponsitory.StreakRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
            .baseUrl("https://laulu.io.vn/hmoob_store_api/")
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
    fun provideRelatedImageRepository(productApi: ProductApi, @ApplicationContext context: Context): ProductRepository {
        return ProductRepository(productApi, context)
    }
    // streak
    @Provides
    @Singleton
    fun provideStreakApi(retrofit: Retrofit): StreakApi {
        return retrofit.create(StreakApi::class.java)
    }
    @Provides
    @Singleton
    fun provideStreakRepository(streakApi: StreakApi): StreakRepository {
        return StreakRepository(streakApi)
    }
    @Provides
    @Singleton
    fun provideFavoriteRepository(@ApplicationContext context: Context): FavoritesRepository {
        return FavoritesRepository(context)
    }
    // collections
    @Provides
    @Singleton
    fun providerCollectionApi(retrofit: Retrofit): CollectionApi {
        return retrofit.create(CollectionApi::class.java)
    }
    // fetch Images from collections
    @Provides
    @Singleton
    fun providerFetchImageFromCollections(collectionApi: CollectionApi): CollectionRepository {
        return CollectionRepository(collectionApi)
    }
    // setting repository
    @Provides
    @Singleton
    fun provideSettingRepository(@ApplicationContext context: Context): SettingRepository {
        return SettingRepository(context)
    }
    // search photos
    @Provides
    @Singleton
    fun providerSearchApi(retrofit: Retrofit) : SearchApi {
        return retrofit.create(SearchApi::class.java)
    }
    // fetch photos
    @Provides
    @Singleton
    fun providerSearchRepository(searchApi: SearchApi, @ApplicationContext context: Context) : SearchRepository {
        return SearchRepository(searchApi, context)
    }
}