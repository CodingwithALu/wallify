package com.example.wallify.feature.wallify.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_viewmodel.utils.helper.NetworkManager
import com.example.wallify.feature.wallify.home.model.Category
import com.example.wallify.feature.wallify.home.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val networkManager: NetworkManager
): ViewModel(){
    private val _category = MutableStateFlow<List<Category>>(emptyList())
    val category: StateFlow<List<Category>> = _category
    var isLoading by mutableStateOf(false)
        private set
    init {
        fetchCategory()
    }
    fun fetchCategory() {
        viewModelScope.launch {
            isLoading = true
            if (!networkManager.checkConnection()){
                isLoading = false
                return@launch
            }
            try {
                val result = categoryRepository.fetchCategories()
                _category.value = result
                isLoading = false
            } catch (e: Exception){
                _category.value = emptyList()
                isLoading = false
            }
        }
    }
}