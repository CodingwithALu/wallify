package com.example.wallify.feature.authentication.screen.onboarding.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.R

@Composable
fun OnBoardingPage(imageRes: Int, title: String, subTitle: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(painter = painterResource(imageRes), contentDescription = null)
        Column (
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = TSizes.defaultSpace),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(R.drawable.logo_app),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.height(TSizes.borderRadiusMd))
            Column (
                modifier = Modifier.padding(horizontal = TSizes.defaultSpace),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = title,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(TSizes.borderRadiusMd))
                Text(text = subTitle,
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center)
            }
        }
    }
}