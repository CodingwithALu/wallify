package com.example.wallify.feature.wallify.search.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.wallify.feature.wallify.home.model.Photos
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    // search photo
    @GET("https://aayqa9hmi0.execute-api.us-east-1.amazonaws.com/search/photos")
    suspend fun getSearchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): List<Photos>
}
class SearchRepository(
    private  val api: SearchApi,
    private val context: Context
) {
    val Context.historySearchDataStore by preferencesDataStore("historySearch")
    val HISTORY_SEARCH_KEY = stringPreferencesKey("history_search_key")
    private val gson = Gson()
    private val listType = object : TypeToken<List<String>>() {}.type
    private val DEFAULT_MAX_HISTORY = 50
    // search photos
    suspend fun searchPhotos(query: String, page: Int = 1) : List<Photos>{
        return api.getSearchPhotos(query, page)
    }
    // history search
    // Return current history as List<String> (newest first)
    suspend fun getSearchHistory(): List<String> {
        val json = context.historySearchDataStore.data.first()[HISTORY_SEARCH_KEY] ?: "[]"
        return gson.fromJson<List<String>>(json, listType) ?: emptyList()
    }
    // Save a query: put the newest item at the beginning (index 0).
    // If it already exists, move it to the beginning.
    // Optional maxSize to cap history length (default 50).
    suspend fun saveSearchQuery(query: String, maxSize: Int = DEFAULT_MAX_HISTORY) {
        val normalized = query.trim()
        if (normalized.isBlank()) return

        context.historySearchDataStore.edit { prefs ->
            val currentJson = prefs[HISTORY_SEARCH_KEY] ?: "[]"
            val currentList = gson.fromJson<List<String>>(currentJson, listType)?.toMutableList() ?: mutableListOf()

            // remove existing duplicate (if any) and add to head
            currentList.removeAll { it.equals(normalized, ignoreCase = true) }
            currentList.add(0, normalized)

            // enforce max size
            while (currentList.size > maxSize) {
                currentList.removeAt(currentList.size - 1)
            }

            prefs[HISTORY_SEARCH_KEY] = gson.toJson(currentList)
        }
    }

    // Remove a specific query from history
    suspend fun removeSearchQuery(query: String) {
        val normalized = query.trim()
        if (normalized.isBlank()) return

        context.historySearchDataStore.edit { prefs ->
            val currentJson = prefs[HISTORY_SEARCH_KEY] ?: "[]"
            val currentList = gson.fromJson<List<String>>(currentJson, listType)?.toMutableList() ?: mutableListOf()
            val changed = currentList.removeAll { it.equals(normalized, ignoreCase = true) }
            if (changed) {
                prefs[HISTORY_SEARCH_KEY] = gson.toJson(currentList)
            }
        }
    }

    // Clear all history
    suspend fun clearSearchHistory() {
        context.historySearchDataStore.edit { prefs ->
            prefs.remove(HISTORY_SEARCH_KEY)
        }
    }
}