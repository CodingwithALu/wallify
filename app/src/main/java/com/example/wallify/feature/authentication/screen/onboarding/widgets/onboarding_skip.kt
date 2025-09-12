package com.example.wallify.feature.authentication.screen.onboarding.widgets

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.wallify.R

@Composable
fun OnBoardingSkip(modifier: Modifier = Modifier, onSkip: () -> Unit) {
    TextButton(onClick = onSkip, modifier = modifier) {
        Text(text = stringResource(id = R.string.skip), color = Color.White)
    }
}