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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core_model.ProductModel
import com.example.wallify.R
import com.example.wallify.utlis.constants.TSizes

@Composable
fun ProductInformation(
    items: ProductModel,
    modifier: Modifier = Modifier,
    onSave: () -> Unit = {},
    onSet: () -> Unit = {},
){
    Column(
        modifier = modifier
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
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = items.title ?: "",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = items.author ?: "",
                )
            }
            Spacer(Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.release_alert),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.favorite),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(Modifier.height(12.dp))
        Text(
            text = items.description ?: "",
            modifier = Modifier.padding(bottom = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        ) {
            Text("Downloads: ${items.downloads ?: 0}")
            Text("${items.width ?: 0} x ${items.height ?: 0}")
            Text("${items.fileSizeMB ?: 0.0} MB")
        }
        Spacer(Modifier.height(TSizes.spaceBtwSections))
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
                    tint = Color(0xFF2EE59D),
                    modifier = Modifier.size(32.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text("Save")
            }
            Spacer(Modifier.weight(0.5f))
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
                    tint = Color.Cyan,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text("Set")
            }
        }
    }
}