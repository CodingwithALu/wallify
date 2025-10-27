package com.example.core_viewmodel.utils.data_store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
class DataStoreUser(
    private val context: Context
) {
    val IS_GOOGLE_LOGGED_IN = booleanPreferencesKey("is_google_logged_in")
    val USER_ID = stringPreferencesKey("user_id")
    suspend fun saveGoogleLoginInfo(
        isLoggedIn: Boolean,
        userId: String,
        accessToken: String
    ) {
        context.onboardingDataStore.edit { prefs ->
            prefs[IS_GOOGLE_LOGGED_IN] = isLoggedIn
            prefs[USER_ID] = userId
        }
    }
    fun getGoogleLoginInfo(): Flow<GoogleLoginInfo> =
        context.onboardingDataStore.data.map { prefs ->
            GoogleLoginInfo(
                isLoggedIn = prefs[IS_GOOGLE_LOGGED_IN] ?: false,
                userId = prefs[USER_ID].toString(),
            )
        }
    suspend fun clearGoogleLoginInfo() {
        context.onboardingDataStore.edit { prefs ->
            prefs[IS_GOOGLE_LOGGED_IN] = false
            prefs[USER_ID] = ""
        }
    }
}
data class GoogleLoginInfo(
    val isLoggedIn: Boolean,
    val userId: String,
)
