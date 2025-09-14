package com.example.wallify.feature.wallify.favorites

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.core_model.ImageItem
import com.example.wallify.feature.wallify.home.model.Image
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class FavoritesRepository (
    private val context: Context
) {
    val Context.favoritesDataStore by preferencesDataStore(name = "favorites_prefs")
    val FAVORITE_IMAGES_KEY = stringPreferencesKey("favorite_images")
    suspend fun saveFavoriteImage(image: Image) {
        val gson = Gson()
        val currentListJson = context.favoritesDataStore.data.first()[FAVORITE_IMAGES_KEY] ?: "[]"
        val currentList = gson.fromJson(currentListJson, Array<Image>::class.java).toMutableList()
        if (currentList.none { it.id == image.id }) {
            currentList.add(image)
        }
        val newListJson = gson.toJson(currentList)
        context.favoritesDataStore.edit { prefs ->
            prefs[FAVORITE_IMAGES_KEY] = newListJson
        }
    }
    // Remove image from favorites
    suspend fun removeFavoriteImage(image: Image) {
        val gson = Gson()
        val currentListJson = context.favoritesDataStore.data.first()[FAVORITE_IMAGES_KEY] ?: "[]"
        val currentList = gson.fromJson(currentListJson, Array<Image>::class.java).toMutableList()
        val newList = currentList.filter { it.id != image.id }
        val newListJson = gson.toJson(newList)
        context.favoritesDataStore.edit { prefs ->
            prefs[FAVORITE_IMAGES_KEY] = newListJson
        }
    }
    // Get all favorite images
    suspend fun getFavoriteImages(): Flow<List<Image>> =
        context.favoritesDataStore.data.map { prefs ->
            val gson = Gson()
            val currentListJson = prefs[FAVORITE_IMAGES_KEY] ?: "[]"
            gson.fromJson(currentListJson, Array<Image>::class.java).toList()
        }
}
