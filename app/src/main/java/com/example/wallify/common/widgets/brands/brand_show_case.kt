//package com.example.ui.components
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import coil.compose.AsyncImage
//import coil.compose.AsyncImagePainter
//import coil.compose.rememberAsyncImagePainter
//import com.example.model.BrandModel
//import com.example.ui.theme.com.example.wallify.common.widgets.custom_shapes.container.com.example.wallify.common.widgets.chips.TColors
//import com.example.ui.theme.com.example.wallify.common.widgets.custom_shapes.container.TSizes
//
//@Composable
//fun TBrandShowcase(
//    images: List<String>,
//    brands: BrandModel,
//    onBrandClick: (BrandModel) -> Unit
//) {
//    val dark = MaterialTheme.colorScheme.isDark()
//    com.example.wallify.common.widgets.custom_shapes.container.TRoundedContainer(
//        showBorder = true,
//        borderColor = com.example.wallify.common.widgets.custom_shapes.container.com.example.wallify.common.widgets.chips.TColors.darkerGrey,
//        backgroundColor = if (dark) Color.Transparent else com.example.wallify.common.widgets.custom_shapes.container.com.example.wallify.common.widgets.chips.TColors.lightGrey,
//        padding = com.example.wallify.common.widgets.custom_shapes.container.TSizes.md,
//        margin = PaddingValues(bottom = com.example.wallify.common.widgets.custom_shapes.container.TSizes.spaceBtwItems),
//        modifier = Modifier.clickable { onBrandClick(brands) }
//    ) {
//        Column {
//            // Brand with Products Count
//            TBrandCard(showBorder = false, brands = brands)
//            Spacer(modifier = Modifier.height(com.example.wallify.common.widgets.custom_shapes.container.TSizes.spaceBtwItems))
//            // Brand Top 3 Products Image
//            Row {
//                images.forEach { image ->
//                    BrandTopProductImageWidget(imageUrl = image)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun BrandTopProductImageWidget(
//    imageUrl: String,
//    height: Dp = 100.dp
//) {
//    val dark = MaterialTheme.colorScheme.isDark()
//    Expanded {
//        com.example.wallify.common.widgets.custom_shapes.container.TRoundedContainer(
//            height = height,
//            padding = com.example.wallify.common.widgets.custom_shapes.container.TSizes.md,
//            margin = PaddingValues(end = com.example.wallify.common.widgets.custom_shapes.container.TSizes.sm),
//            backgroundColor = if (dark) com.example.wallify.common.widgets.custom_shapes.container.com.example.wallify.common.widgets.chips.TColors.darkerGrey else com.example.wallify.common.widgets.custom_shapes.container.com.example.wallify.common.widgets.chips.TColors.light
//        ) {
//            val painter = rememberAsyncImagePainter(model = imageUrl)
//            val state = painter.state
//            when (state) {
//                is AsyncImagePainter.State.Loading -> {
//                    TShimmerEffect(width = 100.dp, height = 100.dp)
//                }
//                is AsyncImagePainter.State.Error -> {
//                    Icon(
//                        painter = painterResource(id = android.R.drawable.ic_dialog_alert),
//                        contentDescription = "Error",
//                        tint = Color.Red
//                    )
//                }
//                else -> {
//                    Image(
//                        painter = painter,
//                        contentDescription = null,
//                        contentScale = ContentScale.Fit,
//                        modifier = Modifier.fillMaxSize()
//                    )
//                }
//            }
//        }
//    }
//}
//
///**
// * Expanded composable for Row's children (Compose equivalent of Flutter's Expanded).
// */
//@Composable
//fun Expanded(content: @Composable BoxScope.() -> Unit) {
//    Box(
//        modifier = Modifier
//            .weight(1f)
//            .fillMaxHeight(),
//        content = content
//    )
//}