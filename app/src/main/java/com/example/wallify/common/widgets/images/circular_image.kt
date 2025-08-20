import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.wallify.common.widgets.shimmer.TShimmerEffect
import com.example.wallify.ui.theme.onErrorLightHighContrast
import com.example.wallify.ui.theme.onSurfaceLightHighContrast
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.helpers.THelperFunctions

@Composable
fun TCircularImage(
    modifier: Modifier = Modifier,
    image: String,
    width: Dp = 56.dp,
    height: Dp = 56.dp,
    padding: Dp = TSizes.sm,
    fit: ContentScale = ContentScale.None,
    overlayColor: Color? = null,
    backgroundColor: Color? = null,
    isNetworkImage: Boolean = false,
    drawableResId: Int? = null,
    onClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val isDark = THelperFunctions.isDarkMode()
    val actualBackground = backgroundColor ?: if (isDark) onSurfaceLightHighContrast else onErrorLightHighContrast
    Box(
        modifier = modifier
            .size(width, height)
            .background(actualBackground, shape = CircleShape)
            .clip(CircleShape)
            .padding(padding)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (isNetworkImage) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(image)
                    .crossfade(true)
                    .build()
            )
            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    TShimmerEffect(width = width - padding * 2, height = height - padding * 2)
                }
                is AsyncImagePainter.State.Error -> {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Image error"
                    )
                }
                else -> {

                }
            }
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .then(
                        if (overlayColor != null) Modifier.background(overlayColor.copy(alpha = 0.5f)) else Modifier
                    ),
                contentScale = fit
            )
        } else {
            val localPainter: Painter = if (drawableResId != null) {
                painterResource(id = drawableResId)
            } else {
                rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context)
                        .data("file:///android_asset/$image")
                        .build()
                )
            }
            Image(
                painter = localPainter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .then(
                        if (overlayColor != null) Modifier.background(overlayColor.copy(alpha = 0.5f)) else Modifier
                    ),
                contentScale = fit
            )
        }
    }
}