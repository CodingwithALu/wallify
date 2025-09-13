import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallify.R
import com.example.wallify.common.widgets.images.TRoundedImage
import com.example.wallify.utlis.constants.TSizes

@Composable
fun StreakItemScreen(
    navController: NavController
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.aspectRatio(0.6f)
    ) {
        TRoundedImage(
            drawableResId = R.drawable.banner2,
            fit = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(TSizes.md))
                .padding(TSizes.xs / 4)
        )
        WTitleItems(
            title = "Chromium",
            subTitle = "Streak collections"
        )
        StreakPoint(
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }

}