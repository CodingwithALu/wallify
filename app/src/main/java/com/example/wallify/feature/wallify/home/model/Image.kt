package com.example.wallify.feature.wallify.home.model

import java.util.Date

data class Image(
    val id_image: Int,
    val title: String,
    val author: String,
    val description: String,
    val type: String,
    val upload_date: Date,
    val id_cate: Int? = null,
    val google_id: Int? = null,
    val id_point: Int? = null,
    val id_collec: Int? = null,
    val subImage: List<SubImage>
)

