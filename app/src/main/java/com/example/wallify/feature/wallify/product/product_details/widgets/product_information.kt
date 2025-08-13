import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core_model.ProductModel
import com.example.wallify.R

@Composable
fun ProductInformation(
    items: ProductModel,
    navController: NavController,
    onSave: () -> Unit = {},
    onSet: () -> Unit = {},
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .background(Color(0xFF16708D))
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = items.title ?: "",
                )
                Text(
                    text = items.author ?: "",
                )
            }
            Spacer(Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.release_alert),
                contentDescription = null,
                tint = Color(0xFF2EE59D),
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.favorite),
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.88f),
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(Modifier.height(12.dp))
        Text(
            text = items.description ?: "",
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        ) {
            Text("Downloads: ${items.downloads ?: 0}", color = Color.White.copy(alpha = 0.85f))
            Text("${items.width ?: 0} x ${items.height ?: 0}", color = Color.White.copy(alpha = 0.85f))
            Text("${items.fileSizeMB ?: 0.0} MB", color = Color.White.copy(alpha = 0.85f))
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = onSave,
                border = BorderStroke(2.dp, Color(0xFF2EE59D)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF2EE59D),
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.download), // icon tải
                    contentDescription = null,
                    tint = Color(0xFF2EE59D)
                )
                Spacer(Modifier.width(8.dp))
                Text("Save")
            }
            Spacer(Modifier.width(16.dp))
            OutlinedButton(
                onClick = onSet,
                border = BorderStroke(2.dp, Color.Cyan),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Cyan,
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.set), // icon đặt hình nền
                    contentDescription = null,
                    tint = Color.Cyan
                )
                Spacer(Modifier.width(8.dp))
                Text("Set")
            }
        }
    }
}