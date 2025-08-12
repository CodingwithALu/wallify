import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.wallify.common.widgets.appbar.TAppBar

@Composable
fun AllProductScreen(
    title: String,
    navController: NavController
){
    Scaffold(
        topBar = {
            TAppBar(
                title = {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold
                    )
                },
                showBackArrow = true
            )
        }
    ) { innerPadding ->
        // all Product
        Box(modifier = Modifier.fillMaxSize()
            .padding(innerPadding)){
            Text(
                text = title,
                textAlign = TextAlign.Center)
        }
    }
}