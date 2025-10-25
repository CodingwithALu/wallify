package com.example.core_viewmodel.utils.data_store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
class DataStoreUser(
    private val context: Context
) {
    val IS_GOOGLE_LOGGED_IN = booleanPreferencesKey("is_google_logged_in")
    suspend fun saveGoogleLoginInfo(
        isLoggedIn: Boolean,
    ) {
        context.onboardingDataStore.edit { prefs ->
            prefs[IS_GOOGLE_LOGGED_IN] = isLoggedIn
        }
    }
    fun getGoogleLoginInfo(): Flow<GoogleLoginInfo> =
        context.onboardingDataStore.data.map { prefs ->
            GoogleLoginInfo(
                isLoggedIn = prefs[IS_GOOGLE_LOGGED_IN] ?: false,
            )
        }
    suspend fun clearGoogleLoginInfo() {
        context.onboardingDataStore.edit { prefs ->
            prefs[IS_GOOGLE_LOGGED_IN] = false
        }
    }
}
data class GoogleLoginInfo(
    val isLoggedIn: Boolean
)
