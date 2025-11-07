package com.example.core_viewmodel.di

import android.content.Context
import com.example.core_viewmodel.repository.AuthenticationRepository
import com.example.core_viewmodel.repository.UserApi
import com.example.core_viewmodel.repository.UserRepository
import com.example.core_viewmodel.utils.data_store.DataStoreUser
import com.example.core_viewmodel.utils.data_store.OnboardingDataStore
import com.example.core_viewmodel.utils.helper.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
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
    fun userApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
    @Provides
    @Singleton
    fun provideUserRepository(userApi: UserApi): UserRepository {
        return UserRepository(userApi)
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
    @Provides
    @Singleton
    fun provideOnboardingDataStore(@ApplicationContext context: Context): OnboardingDataStore {
        return OnboardingDataStore(context)
    }
}
