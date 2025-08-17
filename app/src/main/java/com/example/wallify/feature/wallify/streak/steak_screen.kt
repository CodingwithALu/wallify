import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallify.common.widgets.custom_shapes.container.TSearchContainer
import com.example.wallify.common.widgets.texts.TSectionHeading
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.R

@Composable
fun StreakScreen(modifier: Modifier = Modifier,
                 navController: NavController){
    Scaffold { innerPadding ->
        Column (modifier = modifier) {
            // Title
            TSectionHeading(
                title = "Claim Your Coin",
                showActionButton = false,
                modifier = Modifier.padding(horizontal = TSizes.defaultSpace)
            )
            // coin
            Box(
                modifier = Modifier.fillMaxWidth()
                          .aspectRatio(1.6f)
                    .padding(horizontal = TSizes.defaultSpace)
                    .clip(RoundedCornerShape(TSizes.spaceBtwItems)),
                contentAlignment = Alignment.Center
            ){
                TRoundedImage(
                    drawableResId = R.drawable.wallhaven_y8lqo7,
                    fit = ContentScale.Crop,
                    applyImageRadius = true,
                    borderRadius = TSizes.defaultSpace
                )
                Column (modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom){
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
                                painter = painterResource(R.drawable.public_01),
                                contentDescription = "public_01"
                            )
                        },
                        text = "Watch Ad & Claim 1 Coin"
                    )
                  }
                }
            TSectionHeading(title = "Streak Collections", showActionButton = true,
                onPressed = {

                },
                modifier = Modifier.padding(horizontal = TSizes.defaultSpace))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(horizontal = TSizes.defaultSpace - TSizes.xs)
            ) {
                items(10){category ->
                    StreakItemScreen(navController)
                }
            }
        }
    }
}