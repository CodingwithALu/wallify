import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wallify.common.widgets.custom_shapes.container.TCircularContainer

@Composable
fun TPrimaryHeaderContainer(
    modifier: Modifier = Modifier,
    primaryColor: Color,
    textWhite: Color,
    child: @Composable () -> Unit
){
    TCurvedEdgeWidget(
        modifier = modifier
            .fillMaxWidth()
            .background(primaryColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TCircularContainer(
                modifier = Modifier
                    .offset(x = (-250).dp, y = (-150).dp),
                backgroundColor = textWhite.copy(alpha = 26f / 255f)
            )
            TCircularContainer(
                modifier = Modifier
                    .offset(x = (-300).dp, y = 100.dp),
                backgroundColor = textWhite.copy(alpha = 26f / 255f)
            )
            child()
        }
    }
}