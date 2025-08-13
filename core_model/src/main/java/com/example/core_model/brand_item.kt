package com.example.core_model

import androidx.compose.ui.graphics.Color
import com.example.core_model.R

data class BrandItem(
    val id: Int,
    val backgroundColor: Color?,
    val iconRes: Int,
    val title: String,
    val iconBgColor: Color?
)