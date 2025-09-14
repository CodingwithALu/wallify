import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
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
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.bookmark),
            contentDescription = null,
            tint = if (dark) Color.White else Color.Black,
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
        )
        Text(
            text = coin.toString(),
            fontWeight = FontWeight.Bold
        )
    }
}