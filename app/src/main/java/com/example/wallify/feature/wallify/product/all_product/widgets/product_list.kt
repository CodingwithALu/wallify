package com.example.wallify.feature.wallify.product.all_product.widgets

import android.content.Context
import android.util.Log
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core_model.ProductModel
import com.example.wallify.common.widgets.products.WProductCardVertical
import com.example.wallify.R
import org.json.JSONArray

@Composable
fun ProductListScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val json = remember {
        readJsonFromRaw(context, rawResId = R.raw.images)
    }
    val images = remember { parseJson(json) }
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        items(images) { product ->
            Log.d("ProductCheck", "Product URL: ${product.url}")
            WProductCardVertical(product, navController = navController)
        }
    }
}
fun readJsonFromRaw(context: Context, @RawRes rawResId: Int): String {
    return context.resources.openRawResource(rawResId).bufferedReader().use { it.readText() }
}
fun parseJson(json: String): List<ProductModel> {
    val arr = JSONArray(json)
    return List(arr.length()) { i -> ProductModel(url = arr.getJSONObject(i).getString("url")) }
}
val mobileWallpapers = listOf(
    ProductModel(
        id = "1",
        url = "drawable/wallhaven_858lz1.png",
        title = "City Night Lights",
        author = "Unknown",
        description = "Stunning city night wallpaper for mobile.",
        width = 1080,
        height = 1920,
        fileSizeMB = 5.13,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_858lz1
    ),
    ProductModel(
        id = "2",
        url = "drawable/wallhaven_dgys1.png",
        title = "Abstract Neon Art",
        author = "Unknown",
        description = "Bright abstract neon art wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 13.87,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_dg21em
    ),
    ProductModel(
        id = "3",
        url = "drawable/wallhaven_dg21em.jpg",
        title = "Sunrise in Forest",
        author = "Unknown",
        description = "Beautiful sunrise in the forest.",
        width = 1080,
        height = 1920,
        fileSizeMB = 3.95,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_d8gygl
    ),
    ProductModel(
        id = "4",
        url = "drawable/wallhaven_d8y118.png",
        title = "Purple Sky",
        author = "Unknown",
        description = "Purple sky scenic wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 1.39,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_d8gygl
    ),
    ProductModel(
        id = "5",
        url = "drawable/wallhaven_e8y98m.jpg",
        title = "Lake Reflections",
        author = "Unknown",
        description = "Lake and reflections, perfect for mobile.",
        width = 1080,
        height = 1920,
        fileSizeMB = 0.44,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_e8ymg8
    ),
    ProductModel(
        id = "6",
        url = "drawable/wallhaven_gw813d.jpg",
        title = "Blue Universe",
        author = "Unknown",
        description = "Blue universe themed wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 1.37,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_gw813d
    ),
    ProductModel(
        id = "7",
        url = "drawable/wallhaven_j353oy.jpg",
        title = "Anime Girl",
        author = "Unknown",
        description = "Cute anime girl wallpaper for mobile.",
        width = 1080,
        height = 1920,
        fileSizeMB = 4.43,
        downloads = 0,
        category = 2,
        imageRes = R.drawable.wallhaven_j353oy
    ),
    ProductModel(
        id = "8",
        url = "drawable/wallhaven_lymr3r.jpg",
        title = "Autumn Forest",
        author = "Unknown",
        description = "Colorful autumn forest view.",
        width = 1080,
        height = 1920,
        fileSizeMB = 19.38,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_lymr3r
    ),
    ProductModel(
        id = "9",
        url = "drawable/wallhaven_lymvlr.jpg",
        title = "Pink Clouds",
        author = "Unknown",
        description = "Pink clouds over the horizon.",
        width = 1080,
        height = 1920,
        fileSizeMB = 3.5,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_lymvrl
    ),
    ProductModel(
        id = "10",
        url = "drawable/wallhaven_mdmp38.png",
        title = "Colorful Gradients",
        author = "Unknown",
        description = "Colorful gradient abstract wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 5.14,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_mdmp38
    ),
    ProductModel(
        id = "11",
        url = "drawable/wallhaven_nm8e2k.jpg",
        title = "Star Galaxy",
        author = "Unknown",
        description = "Night sky full of stars and galaxy.",
        width = 1080,
        height = 1920,
        fileSizeMB = 20.15,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_nm8e2k
    ),
    ProductModel(
        id = "12",
        url = "drawable/wallhaven_o53v3m.jpg",
        title = "Abstract Red",
        author = "Unknown",
        description = "Abstract red themed wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 0.38,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_o53v3m
    ),
    ProductModel(
        id = "13",
        url = "drawable/wallhaven_og33il.png",
        title = "Blue Minimal",
        author = "Unknown",
        description = "Minimal blue style wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 1.74,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_og33jl
    ),
    ProductModel(
        id = "14",
        url = "drawable/wallhaven_po8y79.jpg",
        title = "Dark Forest Night",
        author = "Unknown",
        description = "Dark forest at night wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 1.21,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_po8y79
    ),
    ProductModel(
        id = "15",
        url = "drawable/wallhaven_r7zk1j.jpg",
        title = "Cloudy Day",
        author = "Unknown",
        description = "A cloudy sky over green fields.",
        width = 1080,
        height = 1920,
        fileSizeMB = 6.46,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_r7zkj1
    ),
    ProductModel(
        id = "16",
        url = "drawable/wallhaven_rq2v8j.png",
        title = "Neon Road",
        author = "Unknown",
        description = "A neon road in the dark.",
        width = 1080,
        height = 1920,
        fileSizeMB = 8.13,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_rq2v8j
    ),
    ProductModel(
        id = "17",
        url = "drawable/wallhaven_m3vzqy.png",
        title = "Gradient Flow",
        author = "Unknown",
        description = "Smooth gradient flow wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 2.17,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_m3vzqy
    ),
    ProductModel(
        id = "18",
        url = "drawable/wallhaven_rq25wz.png",
        title = "Galaxy Night",
        author = "Unknown",
        description = "Galaxy night sky mobile wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 13.89,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_rq25zw
    ),
    ProductModel(
        id = "19",
        url = "drawable/wallhaven_rq757r.jpg",
        title = "Anime Forest",
        author = "Unknown",
        description = "Anime style forest background.",
        width = 1080,
        height = 1920,
        fileSizeMB = 4.59,
        downloads = 0,
        category = 2,
        imageRes = R.drawable.wallhaven_rq75r7
    ),
    ProductModel(
        id = "20",
        url = "drawable/wallhaven_wmw58.jpg",
        title = "Cyber City",
        author = "Unknown",
        description = "Cyberpunk city wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 8.04,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_vmrw58
    ),
    ProductModel(
        id = "21",
        url = "drawable/wallhaven_vpg3q5.jpg",
        title = "Starry Night Sky",
        author = "Unknown",
        description = "A night sky full of stars.",
        width = 1080,
        height = 1920,
        fileSizeMB = 14.37,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_vpg3q5
    ),
    ProductModel(
        id = "22",
        url = "drawable/wallhaven_w5yw3r.jpg",
        title = "Sunlit Forest",
        author = "Unknown",
        description = "Sunlight shining through forest.",
        width = 1080,
        height = 1920,
        fileSizeMB = 6.39,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_w5yw3r
    ),
    ProductModel(
        id = "23",
        url = "drawable/wallhaven_w8r2m7.jpg",
        title = "Minimal Night",
        author = "Unknown",
        description = "Minimal design night wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 6.69,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_w8r2m7
    ),
    ProductModel(
        id = "24",
        url = "drawable/wallhaven_wnyy9p.jpg",
        title = "Pastel Dream",
        author = "Unknown",
        description = "Pastel dreamy themed wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 13.47,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_wyy9yp
    ),
    ProductModel(
        id = "25",
        url = "drawable/wallhaven_y8lq7p.jpg",
        title = "Ocean Sunrise",
        author = "Unknown",
        description = "Sunrise over the ocean wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 3.21,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_y8lqo7
    ),
    ProductModel(
        id = "26",
        url = "drawable/wallhaven_yqm2gd.jpg",
        title = "Autumn Leaves",
        author = "Unknown",
        description = "Fallen autumn leaves wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 8.67,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_yqm2gd
    ),
    ProductModel(
        id = "27",
        url = "drawable/wallhaven_zpmdej.jpg",
        title = "Fantasy World",
        author = "Unknown",
        description = "Fantasy world for mobile wallpaper.",
        width = 1080,
        height = 1920,
        fileSizeMB = 0.22,
        downloads = 0,
        category = 1,
        imageRes = R.drawable.wallhaven_zpmdej
    )
)