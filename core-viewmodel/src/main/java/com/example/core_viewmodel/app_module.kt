package com.example.core_viewmodel

import android.content.Context
import com.example.core_viewmodel.reponsitory.AuthenticationRepository
import com.example.core_viewmodel.reponsitory.UserRepository
import com.example.core_viewmodel.utils.helper.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAuthenticationRepository(): AuthenticationRepository = AuthenticationRepository()
    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository = UserRepository()
    @Provides
    @Singleton
    fun networkManager(@ApplicationContext context: Context): NetworkManager = NetworkManager(context)
}