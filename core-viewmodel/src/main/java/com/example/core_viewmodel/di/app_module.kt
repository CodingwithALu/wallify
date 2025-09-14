package com.example.core_viewmodel.di

import android.content.Context
import com.example.core_viewmodel.repository.AuthenticationRepository
import com.example.core_viewmodel.repository.UserRepository
import com.example.core_viewmodel.utils.data_store.DataStoreUser
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
    fun provideAuthenticationRepository(): AuthenticationRepository {
        return AuthenticationRepository()
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }

    @Provides
    @Singleton
    fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager {
        return NetworkManager(context)
    }
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStoreUser {
        return DataStoreUser(context)
    }
}
