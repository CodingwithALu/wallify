import android.text.Layout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallify.common.widgets.custom_shapes.container.TRoundedContainer
import com.example.wallify.R
import com.example.wallify.utlis.constants.TSizes

@Composable
fun StreakItemScreen(
    navController: NavController
){
    TRoundedContainer(
        padding = PaddingValues(TSizes.xs),
        width = 186.dp,
        height = 244.dp,
        radius = TSizes.defaultSpace,
        content = {
            Box(
                contentAlignment = Alignment.Center
            ){
                TRoundedImage(
                    drawableResId = R.drawable.banner2,
                    fit = ContentScale.Crop
                )
                WTitleItems(
                    title = "Chromium",
                    subTitle = "Streak collections"
                )
                Box(
                    modifier = Modifier.align(Alignment.TopEnd)
                        .padding(TSizes.sm),
                    contentAlignment = Alignment.Center
                ){
                    TRoundedImage(
                        drawableResId = R.drawable.point,
                        width = 32.dp,
                        height = 32.dp
                    )
                    Text(
                        text = "10",
                        color = Color.White
                    )
                }
            }
        }
    )
}