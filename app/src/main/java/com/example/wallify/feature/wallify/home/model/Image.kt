package com.example.wallify.feature.wallify.home.model

import java.util.Date

data class Image(
    val id: Int,
    val url: String,
    val title: String,
    val author: String,
    val description: String,
    val width: Int,
    val height: Int,
    val sizeMB: Double,
    val upload_date: Date,
    val is_premium: Int,
    val id_cate: Int,
    val id_user: Int

)

