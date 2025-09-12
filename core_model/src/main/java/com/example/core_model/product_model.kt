package com.example.core_model

data class ProductModel(
    val id: String = "",
    val url: String = "",
    val title:  String = "",
    val author: String = "",
    val description: String = "",
    val width: Int? = 0,
    val height: Int? = 0,
    val fileSizeMB: Double? = 0.0,
    val downloads: Int = 0,
    val category: Int = 0,
    val imageRes: Int? = 0
)