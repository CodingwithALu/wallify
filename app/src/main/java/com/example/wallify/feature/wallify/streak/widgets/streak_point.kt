import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wallify.R
import com.example.wallify.utlis.constants.TSizes

@Composable
fun StreakPoint(
    modifier: Modifier = Modifier,
    coin: Int
) {
    val dark = isSystemInDarkTheme()
    Box(
        modifier = modifier
            .padding(TSizes.md),
        contentAlignment = Alignment.TopCenter
    ) {
        Icon(
            painter = painterResource(R.drawable.bookmark),
            contentDescription = null,
            tint = if (dark) Color.White.copy(alpha = 0.5f) else Color.Black.copy(alpha = 0.5f),
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = coin.toString(),
                color = if (dark) Color.Black else Color.White,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 2.dp)
            )
            Image(
                painter = painterResource(R.drawable.coin),
                contentDescription = null,
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}