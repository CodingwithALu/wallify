import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.example.wallify.common.widgets.images.TRoundedImage
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.utlis.constants.TSizes

@Composable
fun StreakItemScreen(
    item: Image,
    showPoint: Boolean = true,
    onClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.aspectRatio(0.6f)
    ) {
        TRoundedImage(
            imageUrl = item.url,
            isNetworkImage = true,
            fit = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(TSizes.md))
                .padding(TSizes.xs / 2),
            onPressed = {
                onClick()
            }
        )
        WTitleItems(
            title = item.title,
            subTitle = item.description
        )
        if (showPoint){
            StreakPoint(
                modifier = Modifier.align(Alignment.TopEnd),
                coin = item.price?.toInt() ?: 0
            )
        }
    }

}