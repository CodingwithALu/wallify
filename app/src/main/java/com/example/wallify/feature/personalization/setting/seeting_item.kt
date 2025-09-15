import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.wallify.common.widgets.custom_shapes.container.TRoundedContainer
import com.example.wallify.ui.theme.onBackgroundDark
import com.example.wallify.utlis.constants.TSizes

@Composable
fun SettingItem(
    showBackground: Boolean = false,
    onClickItem: () -> Unit = {},
    title: String,
    subtitle: String,
    imageItem: Int
) {
    TRoundedContainer(
        modifier = Modifier.padding(horizontal = TSizes.sm),
        onTap = {
            onClickItem()
        },
        height = 72.dp,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(imageItem),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(TSizes.xs))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = TSizes.fontSizeLg,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = subtitle,
                        fontSize = TSizes.fontSizeSm,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    )
}