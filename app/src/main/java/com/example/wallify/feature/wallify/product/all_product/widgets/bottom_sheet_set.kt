import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen
import com.google.gson.Gson
import com.example.wallify.R

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
                .padding(horizontal = TSizes.xs),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Set Wallpaper",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = TSizes.xs)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val gson = Gson()
                        val items = Uri.encode(gson.toJson(item))
                        navController.navigate("${Screen.ProductDetails.route}/$items")
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.image_54dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        "Portrait",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "HD, ${item.width} x ${item.height} px, ${item.sizeMB} kb",
                    )
                }
            }
            Spacer(modifier = Modifier.height(TSizes.xs))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val gson = Gson()
                        val items = Uri.encode(gson.toJson(item))
                        navController.navigate("${Screen.ProductDetails.route}/$items")
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.image_inset_54dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Original",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "4K, ${item.width} x ${item.height} px, ${item.sizeMB} MB",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}