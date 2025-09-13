import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wallify.R
import com.example.wallify.common.widgets.images.TRoundedImage
import com.example.wallify.utlis.constants.TSizes

@Composable
fun StreakPoint(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(TSizes.md),
        contentAlignment = Alignment.Center
    ) {
        TRoundedImage(
            drawableResId = R.drawable.point,
            modifier = Modifier
                .height(32.dp)
                .width(32.dp)
        )
        Text(
            text = "10",
            color = Color.White
        )
    }
}