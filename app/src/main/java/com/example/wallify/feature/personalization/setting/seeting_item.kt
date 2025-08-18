import android.icu.text.CaseMap
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallify.common.widgets.custom_shapes.container.TRoundedContainer
import com.example.wallify.ui.theme.onBackgroundDark
import com.example.wallify.utlis.constants.TSizes
import com.google.firebase.annotations.concurrent.Background

@Composable
fun SettingItem(
    showBackground: Boolean = false,
    onClickItem: () -> Unit = {},
    showIconButton: Boolean = false,
    title: String,
    subtitle: String,
    imageItem: Int
){
    TRoundedContainer(
        modifier = Modifier.padding(horizontal = TSizes.sm),
        onTap = {
            onClickItem()
        },
        height = 72.dp,
        content = {
            Box(modifier = Modifier.fillMaxSize()
                .background(color = if (showBackground) onBackgroundDark else TODO())){
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
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
                            fontSize = TSizes.fontSizeLg
                        )
                        Text(
                            text = subtitle,
                            fontSize = TSizes.fontSizeSm
                        )
                    }
                    if (showIconButton){
                        Spacer(modifier = Modifier.width(TSizes.xs))
                        Spacer(modifier = Modifier.width(2.dp)
                            .fillMaxHeight()
                            .background(color = onBackgroundDark))
                        Spacer(modifier = Modifier.width(TSizes.xs))
                        TRoundedContainer(
                            width = 56.dp,
                            height = TSizes.defaultSpace,
                            onTap = {

                            },
                            content = {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ){

                                }
                            }
                        )
                    }
                }
            }
        }
    )
}