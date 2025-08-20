package com.example.core_viewmodel.utils.data_store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


object LoginPrefsKeys {
    val IS_GOOGLE_LOGGED_IN = booleanPreferencesKey("is_google_logged_in")
    val USER_EMAIL = stringPreferencesKey("user_email")
    val USER_NAME = stringPreferencesKey("user_name")
    val USER_AVATAR = stringPreferencesKey("user_avatar")
}
suspend fun saveGoogleLoginInfo(
    context: Context,
    isLoggedIn: Boolean,
    email: String,
    userName: String,
    avatar: String
) {
    context.onboardingDataStore.edit { prefs ->
        prefs[LoginPrefsKeys.IS_GOOGLE_LOGGED_IN] = isLoggedIn
        prefs[LoginPrefsKeys.USER_EMAIL] = email
        prefs[LoginPrefsKeys.USER_NAME] = userName
        prefs[LoginPrefsKeys.USER_AVATAR] = avatar
    }
}
fun getGoogleLoginInfo(context: Context): Flow<GoogleLoginInfo> =
    context.onboardingDataStore.data.map { prefs ->
        GoogleLoginInfo(
            isLoggedIn = prefs[LoginPrefsKeys.IS_GOOGLE_LOGGED_IN] ?: false,
            email = prefs[LoginPrefsKeys.USER_EMAIL] ?: "",
            userName = prefs[LoginPrefsKeys.USER_NAME] ?: "",
            avatar = prefs[LoginPrefsKeys.USER_AVATAR] ?: ""
        )
    }
suspend fun clearGoogleLoginInfo(context: Context) {
    context.onboardingDataStore.edit { prefs ->
        prefs[LoginPrefsKeys.IS_GOOGLE_LOGGED_IN] = false
        prefs.remove(LoginPrefsKeys.USER_EMAIL)
        prefs.remove(LoginPrefsKeys.USER_NAME)
        prefs.remove(LoginPrefsKeys.USER_AVATAR)
    }
}

data class GoogleLoginInfo(
    val isLoggedIn: Boolean,
    val email: String,
    val userName: String,
    val avatar: String
)