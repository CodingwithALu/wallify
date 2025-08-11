import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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
import com.example.wallify.ui.theme.onPrimaryLight
import com.example.wallify.utlis.constants.TSizes

@Composable
fun TRoundedImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    width: Dp? = null,
    height: Dp? = null,
    applyImageRadius: Boolean = true,
    borderColor: Color? = null,
    borderWidth: Dp = 1.dp,
    backgroundColor: Color = onPrimaryLight,
    fit: ContentScale = ContentScale.Fit,
    padding: Dp = 0.dp,
    isNetworkImage: Boolean = false,
    onPressed: (() -> Unit)? = null,
    borderRadius: Dp = TSizes.md,
    drawableResId: Int? = null
) {
    val shape: Shape = if (applyImageRadius) RoundedCornerShape(borderRadius) else RoundedCornerShape(0.dp)
    val context = LocalContext.current

    Box(
        modifier = modifier
            .then(if (width != null && height != null) Modifier.size(width, height) else Modifier)
            .padding(padding)
            .clip(shape)
            .background(backgroundColor, shape)
            .then(
                if (borderColor != null) Modifier.border(borderWidth, borderColor, shape)
                else Modifier
            )
            .clickable(enabled = onPressed != null) { onPressed?.invoke() },
        contentAlignment = Alignment.Center
    ) {
        if (isNetworkImage) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .crossfade(true)
                    .build()
            )
            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    TShimmerEffect(
                        width = width ?: 158.dp,
                        height = height ?: 158.dp
                    )
                }
                is AsyncImagePainter.State.Error -> {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Image error"
                    )
                }
                else -> {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        } else {
            val localPainter: Painter =
                if (drawableResId != null) {
                    painterResource(id = drawableResId)
                } else {
                    rememberAsyncImagePainter(
                        model = ImageRequest.Builder(context)
                            .data("file:///android_asset/$imageUrl")
                            .build()
                    )
                }
            Image(
                painter = localPainter,
                contentDescription = null,
                contentScale = fit,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}