import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wallify.ui.theme.surfaceDimLightMediumContrast
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.R

@Composable
fun TSocialButtons(
    onGoogleClick: () -> Unit,
    onFacebookClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, surfaceDimLightMediumContrast), CircleShape)
        ) {
            IconButton(
                onClick = onGoogleClick
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = "Google",
                    modifier = Modifier
                        .size(TSizes.iconMd)
                )
            }
        }
        Spacer(modifier = Modifier.width(TSizes.spaceBtwItems))
        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, surfaceDimLightMediumContrast), CircleShape)
        ) {
            IconButton(
                onClick = onFacebookClick
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook_icon),
                    contentDescription = "Facebook",
                    modifier = Modifier
                        .size(TSizes.iconMd)
                )
            }
        }
    }
}