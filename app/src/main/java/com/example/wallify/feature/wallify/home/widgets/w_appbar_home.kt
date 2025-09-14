package com.example.wallify.feature.wallify.home.widgets

import TCircularImage
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_viewmodel.controller.authentiacations.AuthViewModel
import com.example.wallify.R

@Composable
fun TAppbarHome(
    modifier: Modifier = Modifier,
    onAvatarClick: () -> Unit = {},
    searchClick: () -> Unit = {},
){
    val viewModel: AuthViewModel = hiltViewModel()
    val googleLoginInfo by viewModel.googleLoginInfo.collectAsState()
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TCircularImage(modifier= modifier,
            image = googleLoginInfo.avatar,
            isNetworkImage = googleLoginInfo.isLoggedIn,
            onClick = onAvatarClick,
            fit = ContentScale.Crop)
        Text(text = stringResource(R.string.app_name),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = modifier
                .weight(1f)
                .align(Alignment.CenterVertically),)

        Row {
            Icon(
                modifier = Modifier
                    .clickable { searchClick() },
                imageVector = Icons.Default.Search,
                contentDescription = "Bell",
                tint = Color.Unspecified
            )
        }

    }
}
