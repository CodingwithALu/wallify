import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.wallify.R
import com.example.wallify.feature.personalization.setting.SignInGoogle
import com.example.wallify.utlis.constants.TSizes

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
            item {
                SettingItem(
                    title = "Wallify Pro",
                    subtitle = "Unlock all features",
                    imageItem = R.drawable.flash,
                    onClickItem = {
                    },
                )
            }
            // List function
            item {
                SettingItem(
                    title = "Notifications",
                    subtitle = "Manage notification settings",
                    imageItem = R.drawable.elements,
                    onClickItem = {
                        navController.navigate("app_settings")
                    }
                )
            }
            item {
                SettingItem(
                    title = "My Uploads",
                    subtitle = "View and manage your uploads",
                    imageItem = R.drawable.elements_upload,
                    onClickItem = {
                        navController.navigate("my_uploads")
                    }
                )
            }
            item {
                SettingItem(
                    title = "Favorites",
                    subtitle = "View your favorite wallpapers",
                    imageItem = R.drawable.heart,
                    onClickItem = {
                        navController.navigate("favorites")
                    }
                )
            }
            item {
                SettingItem(
                    title = "Download History",
                    subtitle = "View your download history",
                    imageItem = R.drawable.elements_download,
                    onClickItem = {
                        navController.navigate("download_history")
                    }
                )
            }
            item {
                SettingItem(
                    title = "Sync Favorites",
                    subtitle = "Sync your favorites across devices",
                    imageItem = R.drawable.sync_38dp,
                    onClickItem = {
                        navController.navigate("app_settings")
                    }
                )
            }
            item {
                Divider(
                    modifier = Modifier.padding(vertical = TSizes.sm)
                )
            }
            item {
                SettingItem(
                    title = "Follow Us",
                    subtitle = "Stay connected with us",
                    imageItem = R.drawable.view_object_track_38dp,
                    onClickItem = {
                        navController.navigate("app_settings")
                    }
                )
            }
            item {
                SettingItem(
                    title = "Rate Wallify",
                    subtitle = "Leave a review on the Play Store",
                    imageItem = R.drawable.star,
                    onClickItem = {
                        navController.navigate("app_settings")
                    }
                )
            }
            item {
                SettingItem(
                    title = "Help & Support",
                    subtitle = "Get assistance and support",
                    imageItem = R.drawable.help_38dp,
                    onClickItem = {
                        navController.navigate("app_settings")
                    }
                )
            }
            item {
                SettingItem(
                    title = "Privacy Policy",
                    subtitle = "Read our privacy policy",
                    imageItem = R.drawable.privacy_tip_38dp,
                    onClickItem = {
                        navController.navigate("app_settings")
                    }
                )
            }
        }
    }
}

