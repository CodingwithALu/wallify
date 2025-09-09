package com.example.wallify.feature.wallify.home.widgets

import TRoundedImage
import android.net.Uri
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core_model.BrandItem
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.R
import com.example.wallify.feature.wallify.home.viewmodel.CategoryViewModel
import com.google.gson.Gson

@Composable
fun BrandHorizontalScroll(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel: CategoryViewModel = hiltViewModel()
    val categories by viewModel.category.collectAsState()
    val isLoading = viewModel.isLoading
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        categories.forEach { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = TSizes.xs)
                    .clickable {
                        val gson = Gson()
                        val brands = Uri.encode(gson.toJson(item))
                        navController.navigate("allProducts/$brands")
                    }
            ) {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}