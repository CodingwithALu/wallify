package com.example.wallify.feature.wallify.home.widgets

import TCircularImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wallify.R
import com.example.wallify.utlis.constants.TSizes

@Composable
fun TSubAppbarHome(
    image: String = "",
    onAvatarClick: () -> Unit = {},
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TCircularImage(image = image, drawableResId = R.drawable.avater_profile,
            onClick = onAvatarClick,
            fit = ContentScale.Crop)
        Spacer(modifier = Modifier.width(TSizes.loadingIndicatorSize))
        Text(text = stringResource(R.string.app_name),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp)
    }
}