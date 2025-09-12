package com.example.wallify.common.widgets.appbar

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Tab
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import com.example.wallify.feature.wallify.home.model.Category
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.utlis.constants.TSizes

@Composable
fun TTabBar(
    tabs: List<Category>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    appBarHeight: Dp = TSizes.xl,
    colors: TTabBarColors = TTabBarColors.default()
) {
    val dark = isSystemInDarkTheme()
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.height(appBarHeight),
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = colors.indicatorColor
            )
        },
        divider = {}
    ) {
        tabs.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = item.title,
                        color = when {
                            selectedTabIndex == index -> if (dark) colors.selectedLabelDark else colors.selectedLabelLight
                            else -> colors.unselectedLabelColor
                        }
                    )
                }
            )
        }
    }
}
data class TTabBarColors(
    val backgroundDark: Color,
    val backgroundLight: Color,
    val indicatorColor: Color,
    val selectedLabelDark: Color,
    val selectedLabelLight: Color,
    val unselectedLabelColor: Color
) {
    companion object {
        fun default() = TTabBarColors(
            backgroundDark = Color(0xFF000000),
            backgroundLight = Color(0xFFFFFFFF),
            indicatorColor = Color(0xFF0066CC),
            selectedLabelDark = Color(0xFFFFFFFF),
            selectedLabelLight = Color(0xFF0066CC),
            unselectedLabelColor = Color(0xFF555555)
        )
    }
}