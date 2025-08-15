package com.example.wallify.feature.authentication.controller.onboarding

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.onboardingDataStore by preferencesDataStore(name = "onboarding_prefs")

object OnboardingPrefsKeys {
    val IS_FIRST_TIME = booleanPreferencesKey("is_first_time")
}

// Function set
suspend fun setFirstTime(context: Context, value: Boolean) {
    context.onboardingDataStore.edit { prefs ->
        prefs[OnboardingPrefsKeys.IS_FIRST_TIME] = value
    }
}

// Function get
fun getFirstTime(context: Context): Flow<Boolean> =
    context.onboardingDataStore.data.map { prefs ->
        prefs[OnboardingPrefsKeys.IS_FIRST_TIME] ?: true
    }