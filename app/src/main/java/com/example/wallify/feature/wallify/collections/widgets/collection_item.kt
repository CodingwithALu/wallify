import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallify.common.widgets.custom_shapes.container.TRoundedContainer
import com.example.wallify.R
import com.example.wallify.common.widgets.images.TRoundedImage
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.utlis.constants.TSizes

@Composable
fun CollectionItemScreen(
    item: Image,
    showPrimary: Boolean = false,
    navController: NavController
){
    TRoundedContainer (
        onTap = {
            navController
        },
        padding = PaddingValues(horizontal = TSizes.sm),
        content = {
            Box (modifier = Modifier
                .width(280.dp)
                .height(180.dp),
                contentAlignment = Alignment.Center) {
                TRoundedImage(
                    isNetworkImage = true,
                    imageUrl = item.subImage.first().url,
                    fit = ContentScale.Crop
                )
                WTitleItems(
                    title = item.title,
                    subTitle = item.description
                )
                if (showPrimary) {
                    TCircularImage(
                        drawableResId = R.drawable.lock_56dp,
                        image = "",
                        modifier = Modifier.align(Alignment.BottomEnd)
                            .padding(TSizes.defaultSpace)
                            .width(48.dp)
                            .height(48.dp),
                    )
                }
            }
        }
    )
}
