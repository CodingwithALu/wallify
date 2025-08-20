import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
){
    Scaffold (
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
    ) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)
            .fillMaxSize()) {
            // profile
            SignInGoogle()
            // Wallify Pro
            SettingItem(
                title = "Wallify Pro",
                subtitle = "Unlock all features",
                imageItem = R.drawable.public_01,
                onClickItem = {

                },
                showBackground = true
            )
            // List function

        }
    }
}

