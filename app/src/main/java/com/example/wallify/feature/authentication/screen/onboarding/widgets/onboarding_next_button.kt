package com.example.wallify.feature.authentication.screen.onboarding.widgets

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wallify.R

@Composable
fun OnBoardingNextButton(modifier: Modifier = Modifier,
                         selectedIndex: Int,
                         onClick: () -> Unit) {
    Button(onClick = onClick, modifier = modifier.size(56.dp)) {
       if (selectedIndex < 2) {
           Text(text = stringResource(id = R.string.next))
       } else {
           Text(text = stringResource(id = R.string.get_stated))
       }
    }
}