import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetSet(
    item: Image,
    navController: NavController,
    onDismiss: (Boolean) -> Unit,

) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss(false) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = TSizes.sm),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Set Wallpaper",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = TSizes.xs)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }) {
                Text(
                    "Portrait",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Xử lý chọn Portrait
                            onDismiss(false)
                        }
                )
                Text(
                    text = "Landscape",
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val gson = Gson()
                        val items = Uri.encode(gson.toJson(item))
                        navController.navigate("${Screen.ProductDetails.route}/$items")
                    }) {
                Text(
                    text = "Original",
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = "${item.sizeMB}, ${item.width} x ${item.height}",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}