package com.example.wallify.feature.wallify.home.widgets

import TCircularImage
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.packInts
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_viewmodel.controller.authentiacations.AuthViewModel
import com.example.wallify.R
import com.example.wallify.feature.wallify.home.model.Image
import com.example.wallify.utlis.constants.TSizes
import okhttp3.internal.notify
import org.checkerframework.checker.units.qual.mol

@Composable
fun TAppbarHome(
    onAvatarClick: () -> Unit = {},
    searchClick: () -> Unit = {},
    notify: () -> Unit = {},
    showNotification: Boolean = false,

    ) {
    val dark = isSystemInDarkTheme()
    val viewModel: AuthViewModel = hiltViewModel()
    val googleLoginInfo by viewModel.googleLoginInfo.collectAsState()
    val coin = 0
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Transparent)
    ) {
        Text(
            text = stringResource(R.string.app_name),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = TSizes.xs)
                .fillMaxSize()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                TCircularImage(
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp),
                    image = if (googleLoginInfo.isLoggedIn) googleLoginInfo.avatar else "",
                    isNetworkImage = googleLoginInfo.isLoggedIn,
                    drawableResId = if (googleLoginInfo.isLoggedIn) null else R.drawable.person_circle_sharp,
                    onClick = onAvatarClick,
                    fit = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(TSizes.xs))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = coin.toString(),
                        style = MaterialTheme.typography.labelSmall,
                    )
                    androidx.compose.foundation.Image(
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                            .clickable { },
                        painter = painterResource(id = R.drawable.coin),
                        contentDescription = "Coin",
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .height(48.dp)
                        .width(48.dp)
                        .clickable { searchClick() },
                    painter = painterResource(id = R.drawable.search_normal),
                    contentDescription = "Bell",
                    tint = if (dark) Color.White else Color.Black
                )
                if (showNotification) {
                    Icon(
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                            .clickable { notify() },
                        painter = painterResource(id = R.drawable.elements),
                        contentDescription = "Bell",
                        tint = if (dark) Color.White else Color.Black
                    )
                }
            }

        }
    }
}
