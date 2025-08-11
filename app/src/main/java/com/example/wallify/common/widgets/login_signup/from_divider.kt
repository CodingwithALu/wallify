import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.wallify.ui.theme.inverseOnSurfaceDark
import com.example.wallify.ui.theme.scrimDark
import com.example.wallify.utlis.helpers.THelperFunctions

@Composable
fun TFormDivider(
    dividerText: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isDark = THelperFunctions.isDarkMode()
    val dividerColor = if (isDark) scrimDark else inverseOnSurfaceDark

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.width(60.dp))
        HorizontalDivider(
            modifier = Modifier.weight(1f).padding(end = 3.dp),
            thickness = 0.5.dp,
            color = dividerColor
        )
        Spacer(modifier = Modifier.width(0.dp))
        Text(
            text = dividerText,
            style = MaterialTheme.typography.labelSmall
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f).padding(start = 5.dp),
            thickness = 0.5.dp,
            color = dividerColor
        )
        Spacer(modifier = Modifier.width(60.dp))
    }
}