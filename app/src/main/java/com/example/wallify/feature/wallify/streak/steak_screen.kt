import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallify.common.widgets.custom_shapes.container.TSearchContainer
import com.example.wallify.common.widgets.texts.TSectionHeading
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.R
import com.example.wallify.common.widgets.images.TRoundedImage
import com.example.wallify.feature.wallify.home.widgets.TAppbarHome
import com.example.wallify.feature.wallify.home.widgets.VerticalTopBar
import com.example.wallify.feature.wallify.streak.controller.StreakViewModel
import com.example.wallify.navigation.BottomAppBarr
import com.example.wallify.utlis.route.Screen
import com.google.gson.Gson

@SuppressLint("FrequentlyChangingValue")
@Composable
fun StreakScreen(navController: NavController) {
    val dark = isSystemInDarkTheme()
    var showTopBar by remember { mutableStateOf(true) }
    var showBottomBar by remember { mutableStateOf(true) }
    val listState = rememberLazyGridState()
    val lastPosition = remember { mutableStateOf(Pair(0, 0)) }
    // viewModel
    val viewModel: StreakViewModel = hiltViewModel()
    val streaks by viewModel.streak.collectAsState()
    val streakByPrice by viewModel.streakByPrice.collectAsState()
    val isLoading = viewModel.isLoading
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
            val isScrollingDown = currentIndex < 2
            showTopBar = isScrollingDown
            showBottomBar = !isScrollingUp
            lastPosition.value = Pair(currentIndex, currentOffset)
        }
    }
    Scaffold(
        bottomBar = {
            if(showBottomBar){
                BottomAppBarr(
                    showBar = true,
                    navController = navController
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier
                .fillMaxSize()
            .padding(innerPadding)) {
            if(showTopBar){
                VerticalTopBar(
                    topBar = {
                        TAppbarHome(
                            modifier = Modifier
                                .height(32.dp)
                                .width(32.dp)
                        )
                    },
                    showTopBar = showTopBar,
                    modifier = Modifier.padding(
                        horizontal = TSizes.md,
                    )
                )
            }
            LazyVerticalGrid(
                state = listState,
                columns = GridCells.Fixed(2),
            ) {
                // Title
                item(span = { GridItemSpan(2) }) {
                    TSectionHeading(
                        title = "Claim Your Coin",
                        showActionButton = false,
                        modifier = Modifier.padding(horizontal = TSizes.xs)
                            .height(26.dp),
                        startText = TSizes.xs
                    )
                }
                // coin
                item(span = { GridItemSpan(2) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.4f)
                            .padding(horizontal = TSizes.xs)
                            .clip(RoundedCornerShape(TSizes.spaceBtwItems)),
                        contentAlignment = Alignment.Center
                    ) {
                        TRoundedImage(
                            drawableResId = R.drawable.wallhaven_y8lqo7,
                            fit = ContentScale.Crop,
                        )
                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(
                                text = "Daily Streak Coins",
                                fontSize = TSizes.fontSizeLg,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(TSizes.sm))
                            TSearchContainer(
                                modifier = Modifier.height(56.dp),
                                icon = {
                                    Image(
                                        painter = painterResource(R.drawable.electric_bolt_48dp),
                                        contentDescription = "public_01"
                                    )
                                },
                                text = "Watch Ad & Claim 1 Coin",
                            )
                        }
                    }
                }
                // Streak Collections
                stickyHeader {
                    TSectionHeading(
                        title = "Streak Collections", showActionButton = true,
                        onPressed = {},
                        modifier = Modifier
                            .background(if (dark) Color.Black else Color.White),
                        startText = TSizes.xs
                    )
                }
                // Streak items
                items(streaks) { item ->
                    StreakItemScreen(
                        item = item,
                        onClick = {
                            val gson = Gson()
                            val streaks = Uri.encode(gson.toJson(item))
                            navController.navigate("${Screen.StreakList.route}/$streaks")
                        })
                }
            }
        }
    }
}