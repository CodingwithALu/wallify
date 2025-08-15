import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
                modifier = Modifier
            )
            // coin
            Box(
                modifier = Modifier.fillMaxWidth()
                          .aspectRatio(1.6f)
                    .clip(RoundedCornerShape(TSizes.spaceBtwItems)),
            ){
                    TRoundedImage(
                        drawableResId = R.drawable.wallhaven_y8lqo7,
                        fit = ContentScale.Crop
                    )
                    Column (modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom){
                        TSectionHeading(
                            title = "Daily Streak Coins"
                        )
                        TSearchContainer(
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
            LazyColumn {
                item{
                    // streak collections
                    TSectionHeading(title = "Streak Collections", showActionButton = true,
                        onPressed = {

                        })
                    }
                items(10){category ->
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .align(alignment = Alignment.CenterHorizontally)
                    ){
                        TRoundedImage(
                            drawableResId = R.drawable.wallhaven_e8y118
                        )
                        Column (modifier = Modifier) {
                            Text("Title")
                            Spacer(modifier = Modifier.height(TSizes.md))
                            Text("subTitle")
                        }
                    }
                }
            }
        }
    }
}