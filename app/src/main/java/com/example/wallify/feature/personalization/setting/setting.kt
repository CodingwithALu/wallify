import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.wallify.R
import com.example.wallify.feature.personalization.setting.SignInGoogle

@Composable
fun SettingScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            WAppBarCenter(
                title = {
                    Text(
                        text = "Setting"
                    )
                },
                showBackArrow = true,
                leadingOnPressed = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // profile
            item {
                SignInGoogle()
            }
            // Wallify Pro
            items (10) {
                SettingItem(
                    title = "Wallify Pro",
                    subtitle = "Unlock all features",
                    imageItem = R.drawable.flash,
                    onClickItem = {
                    },
                    showBackground = true
                )
            }
            // List function

        }
    }
}

