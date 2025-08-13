package com.example.wallify.feature.wallify.product.all_product
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallify.common.widgets.appbar.TAppBar
import com.example.wallify.feature.wallify.product.all_product.widgets.ProductListScreen
import com.example.wallify.feature.wallify.product.all_product.widgets.mobileWallpapers

@Composable
fun AllProductScreen(
    title: String,
    navController: NavController
){
    Scaffold(
        topBar = {
            TAppBar(
                title = {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold
                    )
                },
                showBackArrow = true
            )
        }
    ) { innerPadding ->
        // all Product
        Box(modifier = Modifier.fillMaxSize()
            .padding(innerPadding)
            .clip(RoundedCornerShape(24.dp))){
            ProductListScreen(mobileWallpapers)
        }
    }
}