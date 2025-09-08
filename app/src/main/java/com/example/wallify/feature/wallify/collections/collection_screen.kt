package com.example.wallify.feature.wallify.collections

import CollectionItemScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.wallify.common.widgets.texts.TSectionHeading
import com.example.wallify.utlis.constants.TSizes

@Composable
fun CollectionScreen(navController: NavController){
    Scaffold { innerPadding ->
        LazyColumn (
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
        ) {
            items(5){
                // pro Collections
                // title
                TSectionHeading(
                    title = "Pro Collections",
                    showActionButton = true,
                    modifier = Modifier.padding(horizontal = TSizes.defaultSpace)
                )
                LazyRow {

                    items(4){ item ->
                        CollectionItemScreen(showPrimary = true,
                            navController)
                    }
                }
            }
        }
    }
}