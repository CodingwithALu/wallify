package com.example.wallify.feature.wallify.product.product_details

import ProductInformation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core_model.ProductModel

@Composable
fun ProductDetailsScreen(
    items: ProductModel,
    navController: NavController,
    onSave: () -> Unit = {},
    onSet: () -> Unit = {},
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF10181A))
    ) {
        Image(
            painter = painterResource(id = items.imageRes!!),
            contentDescription = items.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.73f)
                .clip(RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp))
        )

        // close
        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(32.dp)
                .background(Color.Black.copy(alpha = 0.55f), shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.White
            )
        }
        // information
        ProductInformation(
            items = items,
            navController = navController,
            onSave = onSave,
            onSet = onSet
        )
    }
}