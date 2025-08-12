package com.example.wallify.feature.wallify.home
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.wallify.common.widgets.appbar.TAppBar
import com.example.wallify.R
import com.example.wallify.feature.wallify.home.widgets.BellIcon
import com.example.wallify.feature.wallify.home.widgets.NotificationCount
import com.example.wallify.feature.wallify.home.widgets.TSubAppbarHome

@Composable
fun HomeScreen(){
    Scaffold (
        topBar = {
            TAppBar(
                title = { TSubAppbarHome() },
                actions = listOf(
                    {
                        NotificationCount(count = 0)
                    },
                    {
                        BellIcon()
                    }
                )

            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
        ) {
            // banner and search

            // brand
            // category
        }
    }
}