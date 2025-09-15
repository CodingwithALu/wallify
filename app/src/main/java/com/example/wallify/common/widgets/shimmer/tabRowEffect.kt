package com.example.wallify.common.widgets.shimmer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wallify.utlis.constants.TSizes

@Composable
fun TabRowEffect() {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = TSizes.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(4) {
            TShimmerEffect(
                modifier = Modifier.height(32.dp)
                    .weight(1f))
            if (it < 3) Spacer(modifier = Modifier.width(14.dp))
        }
    }
}