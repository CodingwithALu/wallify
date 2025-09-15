package com.example.core_viewmodel.utils.data_store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.text.get

val Context.onboardingDataStore by preferencesDataStore(name = "onboarding_prefs")

class OnboardingDataStore(
    private val context: Context
) {

    val IS_FIRST_TIME = booleanPreferencesKey("is_first_time")

    // Function set
    suspend fun setFirstTime(value: Boolean) {
        context.onboardingDataStore.edit { prefs ->
            prefs[IS_FIRST_TIME] = value
        }
    }

    // Function get
    fun getFirstTime(): Flow<Boolean> =
        context.onboardingDataStore.data.map { prefs ->
            prefs[IS_FIRST_TIME] ?: true
        }
}
