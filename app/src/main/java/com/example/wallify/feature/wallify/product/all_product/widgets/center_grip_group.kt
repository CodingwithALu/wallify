import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CenterGripButton(
    modifier: Modifier = Modifier,
    alpha: Float = 1f,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(24.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .offset(y = 14.dp)
                .size(width = 48.dp, height = 18.dp)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(1.dp))
                .background(Color(0xFF23202E).copy(alpha = alpha), shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.TopCenter
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowUp,
                contentDescription = "Up",
                tint = Color.White.copy(alpha = alpha),
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )
        }
    }
}