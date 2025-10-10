package com.example.wallify.feature.wallify.home.model

data class Collections(
    val id_collec: Int,
    val title: String,
    val imageCount: Int,
    val images: List<Image>
)
