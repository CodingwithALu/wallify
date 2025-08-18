import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallify.common.widgets.custom_shapes.container.TRoundedContainer
import com.example.wallify.R
import com.example.wallify.common.widgets.appbar.TAppBar
import com.example.wallify.common.widgets.custom_shapes.container.TSearchContainer
import com.example.wallify.utlis.constants.TSizes

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
            TRoundedContainer(
                modifier = Modifier.aspectRatio(1.6f)
                    .padding(horizontal = TSizes.sm),
                content = {
                    Box(modifier = Modifier
                        .fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        TRoundedImage(
                            drawableResId = R.drawable.wallhaven_o53v3m,
                            fit = ContentScale.Crop
                        )
                        TCircularImage(
                            drawableResId = R.drawable.avater_profile,
                            image = "",
                            fit = ContentScale.Crop,
                            modifier = Modifier.align(Alignment.TopCenter)
                                .padding(vertical = TSizes.sm)
                        )
                        WTitleItems(
                            title = "Save & Sync Your Favorites",
                            subTitle = "Sign in below to get started"
                        )
                        TSearchContainer(
                            modifier = Modifier.align(Alignment.BottomCenter)
                                .height(60.dp)
                                .padding(bottom = TSizes.sm),
                            icon = {
                                Image(
                                    painter = painterResource(R.drawable.google_icon),
                                    contentDescription = null,
                                    modifier = Modifier.width(32.dp)
                                        .height(32.dp)
                                )
                            },
                            text = "Sign in with Google"
                        )
                    }
                }
            )
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