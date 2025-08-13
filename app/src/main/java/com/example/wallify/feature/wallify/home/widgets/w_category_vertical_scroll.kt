import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.core_model.Category
import com.example.wallify.R
import com.google.gson.Gson


@Composable
fun CategoryMasonryList(categories: List<Category>,
                        navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(26.dp))
            .background(Color.Black),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories.chunked(4)) { chunk ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (chunk.isNotEmpty()) CategoryCard(chunk[0], Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f),
                        navController
                    )
                    if (chunk.size > 2) CategoryCard(chunk[2], Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.4f),
                        navController
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (chunk.size > 1) CategoryCard(chunk[1], Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.4f),
                        navController
                    )
                    if (chunk.size > 3) CategoryCard(chunk[3], Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f),
                        navController
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryCard(category: Category, modifier: Modifier = Modifier,
                 navController: NavController) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .clickable {
                val gson = Gson()
                val categoryJson = Uri.encode(gson.toJson(category))
                navController.navigate("allProducts/$categoryJson")
            }
    ) {
        Image(
            painter = painterResource(id = category.imageRes),
            contentDescription = category.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        if (category.title.isNotEmpty()) {
            Text(
                text = category.title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(18.dp)
            )
        }
    }
}
val sampleCategories = listOf(
    Category(
        id = 1,
        imageRes = R.drawable.category_01,
        title = "CARTOON"
    ),
    Category(
        id = 2,
        imageRes = R.drawable.vector,
        title = "Stuck in Time"
    ),
    Category(
        id = 3,
        imageRes = R.drawable.vector_1,
        title = "VCG_Flowers"
    ),
    Category(
        id = 4,
        imageRes = R.drawable.vector_2,
        title = "TOP 200 ★"
    ),
    Category(
        id = 5,
        imageRes = R.drawable.vector_4,
        title = "CARTOON"
    ),
    Category(
        id = 6,
        imageRes = R.drawable.vector_2,
        title = "ANIMALS"
    ),
    Category(
        id = 7,
        imageRes = R.drawable.vector_2,
        title = "NATURE"
    ),
    Category(
        id = 8,
        imageRes = R.drawable.vector_5,
        title = "TECHNOLOGY"
    )
)