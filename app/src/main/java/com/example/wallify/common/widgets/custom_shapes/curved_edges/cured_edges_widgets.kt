import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.wallify.common.widgets.custom_shapes.curved_edges.TCustomCurvedEdges

@Composable
fun TCurvedEdgeWidget(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.clip(TCustomCurvedEdges)
    ) {
        content()
    }
}