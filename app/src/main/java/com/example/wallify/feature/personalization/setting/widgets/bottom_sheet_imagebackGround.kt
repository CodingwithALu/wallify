package com.example.wallify.feature.personalization.setting.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.wallify.utlis.constants.TSizes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetImageBackGround(
    onClick: (Boolean) -> Unit = {},
    upLoad: () -> Unit = {},
) {
    ModalBottomSheet(
        onDismissRequest = { onClick(false) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = TSizes.xs),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Seen cover photo",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = TSizes.sm)
            )
            Text(
                text = "Upload photo",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(bottom = TSizes.sm)
                    .clickable(onClick = { upLoad() })
            )
            Text(
                text = "Choose cover photo",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = TSizes.sm)
            )
        }
    }
}