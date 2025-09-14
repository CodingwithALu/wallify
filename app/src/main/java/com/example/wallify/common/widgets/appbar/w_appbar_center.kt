import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WAppBarCenter(
    modifier: Modifier = Modifier,
    showBackArrow: Boolean = false,
    leadingIcon: ImageVector? = null,
    leadingOnPressed: (() -> Unit)? = null,
    actions: List<@Composable () -> Unit>? = null,
    title: @Composable (() -> Unit)? = null,
) {
    // kotlin
    CenterAlignedTopAppBar(
        modifier = modifier.padding(horizontal = TSizes.xs)
            .height(56.dp),
        navigationIcon = {
            when {
                showBackArrow -> {
                    Icon(
                        painter = painterResource(R.drawable.chevron_back),
                        contentDescription = "Back",
                        modifier = Modifier.clickable{
                            leadingOnPressed?.invoke()
                        },
                    )
                }
                leadingIcon != null -> {
                    IconButton(onClick = { leadingOnPressed?.invoke() }) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = "Leading Icon"
                        )
                    }
                }
                else -> {}
            }
        },
        title = { title?.invoke() },
        actions = {
            actions?.forEach { action -> action() }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        )
    )
}