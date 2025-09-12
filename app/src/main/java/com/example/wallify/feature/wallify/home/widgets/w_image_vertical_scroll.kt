import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen
import com.google.gson.Gson


@Composable
fun ImageMasonryList(categories: List<Image>,
                     onScroll: (isScrollingUp: Boolean) -> Unit,
                     navController: NavController) {
    val listState = rememberLazyListState()
    val lastPosition = remember { mutableStateOf(Pair(0, 0)) }
    LaunchedEffect(
        listState.isScrollInProgress,
        listState.firstVisibleItemIndex,
        listState.firstVisibleItemScrollOffset
    ) {
        val currentIndex = listState.firstVisibleItemIndex
        val currentOffset = listState.firstVisibleItemScrollOffset
        val lastIndex = lastPosition.value.first
        val lastOffset = lastPosition.value.second

        if (listState.isScrollInProgress) {
            val isScrollingUp = currentIndex < lastIndex ||
                    (currentIndex == lastIndex && currentOffset < lastOffset)
            val isScrollingDown = currentIndex > lastIndex ||
                    (currentIndex == lastIndex && currentOffset > lastOffset)
            if (isScrollingUp) onScroll(true)
            else if (isScrollingDown) onScroll(false)
            lastPosition.value = Pair(currentIndex, currentOffset)
        }
    }
    LazyColumn (
        state = listState,
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(TSizes.xs)
    ) {
        items(categories.chunked(4)) {chunk ->
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(TSizes.xs)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(TSizes.xs)
                ) {
                    if (chunk.isNotEmpty()) ImageCard(chunk[0], Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f),
                        navController
                    )
                    if (chunk.size > 2) ImageCard(chunk[2], Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.4f),
                        navController
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(TSizes.xs)
                ) {
                    if (chunk.size > 1) ImageCard(chunk[1], Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.4f),
                        navController
                    )
                    if (chunk.size > 3) ImageCard(chunk[3], Modifier
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
fun ImageCard(category: Image, modifier: Modifier = Modifier,
              navController: NavController) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(TSizes.md))
            .clickable {
                val gson = Gson()
                val categoryJson = Uri.encode(gson.toJson(category))
                navController.navigate("${Screen.ProductList.route}/$categoryJson")
            }
    ) {
        AsyncImage(
            model = category.url,
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