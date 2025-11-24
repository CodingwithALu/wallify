package com.example.wallify.feature.wallify.network

import com.example.wallify.feature.personalization.setting.reponsitory.SettingApi
import com.example.wallify.feature.wallify.collections.reponsitory.CollectionApi
import com.example.wallify.feature.wallify.home.repository.HomeApi
import com.example.wallify.feature.wallify.photos.repository.PhotosApi
import com.example.wallify.feature.wallify.search.repository.SearchApi
import com.example.core_viewmodel.repository.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConstants {
    const val BASE_URL = "https://dsk89w9wz0.execute-api.us-east-1.amazonaws.com/"
}
object ApiClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL) // change BASE_URL only in ApiConstants.kt
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val collectionApi: CollectionApi = retrofit.create(CollectionApi::class.java)
    val homApi: HomeApi = retrofit.create(HomeApi::class.java)
    val photos: PhotosApi = retrofit.create(PhotosApi::class.java)
    val search: SearchApi = retrofit.create(SearchApi::class.java)
    val userApi: UserApi = retrofit.create(UserApi::class.java)
    val settingApi: SettingApi = retrofit.create(SettingApi::class.java)
}
