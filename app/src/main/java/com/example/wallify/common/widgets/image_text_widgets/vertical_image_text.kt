import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wallify.ui.theme.onPrimaryLight
import com.example.wallify.utlis.constants.TSizes

@Composable
fun TVerticalImageText(
    image: String,
    title: String,
    modifier: Modifier = Modifier,
    textColor: Color = onPrimaryLight,
    backgroundColor: Color? = onPrimaryLight,
    isNetworkImage: Boolean = true,
    onTap: (() -> Unit)? = null,
    imageSize: Dp = 56.dp
) {
    Column(
        modifier = modifier
            .padding(end = TSizes.spaceBtwItems)
            .then(
                if (onTap != null) Modifier.clickable { onTap() } else Modifier
            ),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        TCircularImage(
            image = image,
            isNetworkImage = isNetworkImage,
            padding = TSizes.sm,
            backgroundColor = backgroundColor,
            fit = androidx.compose.ui.layout.ContentScale.FillWidth,
            width = imageSize,
            height = imageSize
        )
        Spacer(modifier = Modifier.height(TSizes.spaceBtwItems / 2))
        Box(
            modifier = Modifier.width(55.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium.copy(color = textColor),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}