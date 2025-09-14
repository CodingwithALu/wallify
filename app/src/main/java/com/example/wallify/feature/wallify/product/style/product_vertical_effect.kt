import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallify.R
import com.example.wallify.common.widgets.shimmer.TShimmerEffect
import com.example.wallify.utlis.constants.TSizes

@Composable
fun ProductVerticalEffect() {
    Box(modifier = Modifier
        .height(280.dp)
        .width(80.dp)
        .background(Color.Black)) {
        TShimmerEffect(
            modifier = Modifier
                .padding(TSizes.xs /2)
                .fillMaxSize()
        )
    }
}