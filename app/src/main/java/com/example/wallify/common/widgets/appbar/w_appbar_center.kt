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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.example.wallify.utlis.constants.TSizes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WAppBarCenter(
    horizontalPadding: Dp = TSizes.xs,
    showBackArrow: Boolean = false,
    leadingIcon: ImageVector? = null,
    leadingOnPressed: (() -> Unit)? = null,
    actions: List<@Composable () -> Unit>? = null,
    title: @Composable (() -> Unit)? = null,
) {
    // kotlin
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(horizontal = horizontalPadding),
        navigationIcon = {
            when {
                showBackArrow -> {
                    IconButton(onClick = { leadingOnPressed?.invoke() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
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
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
    )
}