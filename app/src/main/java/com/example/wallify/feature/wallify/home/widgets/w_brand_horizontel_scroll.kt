package com.example.wallify.feature.wallify.home.widgets

import TRoundedImage
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.core_model.BrandItem
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.R
import com.google.gson.Gson

@Composable
fun BrandHorizontalScroll(
    items: List<BrandItem>,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .clickable {
                        val gson = Gson()
                        val brands = Uri.encode(gson.toJson(item))
                        navController.navigate("allProducts/$brands")
                    }
            ) {

                TRoundedImage(
                    drawableResId = item.iconRes,
                    width = 56.dp,
                    height = 56.dp
                )
                Spacer(modifier = Modifier.height(TSizes.sm))
                Text(
                    text = item.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
val brandList = listOf(
    BrandItem(
        id = 1,
        backgroundColor = Color(0xFFDDDDDD),
        iconRes = R.drawable.brand_news,
        title = "News",
        iconBgColor = Color(0xFFDDDDDD)
    ),
    BrandItem(
        id = 2,
        backgroundColor = Color(0xFF00E68A),
        iconRes = R.drawable.brand_category,
        title = "Categories",
        iconBgColor = Color(0xFF00E68A)
    ),
    BrandItem(
        id = 3,
        backgroundColor = Color(0xFFFFD166),
        iconRes = R.drawable.brand_solors,
        title = "Colors",
        iconBgColor = Color(0xFFFFD166)
    ),
    BrandItem(
        id = 4,
        backgroundColor = Color(0xFFD72660),
        iconRes = R.drawable.brand_solors,
        title = "Selected",
        iconBgColor = Color(0xFFD72660)
    ),
    BrandItem(
        id = 5,
        backgroundColor = Color(0xFF48CAE4),
        iconRes = R.drawable.brand_news,
        title = "Popular",
        iconBgColor = Color(0xFF48CAE4)
    )
    )
