package com.example.wallify.common.widgets.layouts

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wallify.utlis.constants.TSizes

@Composable
fun TGridLayout(
    itemCount: Int,
    mainAxisExtent: Dp = 288.dp,
    gridSpacing: Dp = TSizes.gridViewSpacing,
    modifier: Modifier = Modifier,
    itemBuilder: @Composable (index: Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
        horizontalArrangement = Arrangement.spacedBy(gridSpacing),
        verticalArrangement = Arrangement.spacedBy(gridSpacing),
        userScrollEnabled = false
    ) {
        items(itemCount) { index ->
            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .height(mainAxisExtent)
            ) {
                itemBuilder(index)
            }
        }
    }
}
