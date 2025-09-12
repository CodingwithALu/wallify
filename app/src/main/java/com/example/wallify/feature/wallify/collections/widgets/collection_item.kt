import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallify.common.widgets.custom_shapes.container.TRoundedContainer
import com.example.wallify.R
import com.example.wallify.utlis.constants.TSizes

@Composable
fun CollectionItemScreen(
    showPrimary: Boolean = false,
    navController: NavController
){
    TRoundedContainer (
        onTap = {
            navController
        },
        padding = PaddingValues(horizontal = TSizes.spaceBtwItems),
        content = {
            Box (modifier = Modifier,
                contentAlignment = Alignment.Center) {
                TRoundedImage(
                    drawableResId = R.drawable.wallhaven_og33jl,
                    width = 294.dp,
                    height = 186.dp,
                    applyImageRadius = true,
                    borderRadius = TSizes.spaceBtwSections,
                    fit = ContentScale.Crop
                )
                WTitleItems(
                    title = "Wavy Craze",
                    subTitle = "Pree Collections"
                )
                if (showPrimary) {
                    TCircularImage(
                        drawableResId = R.drawable.enhanced_encryption,
                        image = "",
                        modifier = Modifier.align(Alignment.BottomEnd)
                            .padding(TSizes.defaultSpace)
                    )
                }
            }
        }
    )
}
