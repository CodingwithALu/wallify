package com.example.core_viewmodel.utils.loaders

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

/**
 * A Composable for displaying an animated loading indicator with optional text and action button.
 *
 * @param text The text to be displayed below the animation.
 * @param animationRes The Lottie animation resource (raw resource id).
 * @param showAction Whether to show an action button below the text.
 * @param actionText The text to be displayed on the action button.
 * @param onActionPressed Callback function to be executed when the action button is pressed.
 */
@SuppressLint("SuspiciousIndentation")
@Composable
fun AnimationLoaderWidget(
        text: String,
        animationRes: Int,
        showAction: Boolean = false,
        actionText: String? = null,
        onActionPressed: (() -> Unit)? = null,
        modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationRes))
    val progress by animateLottieCompositionAsState(composition)
       Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (showAction && actionText != null && onActionPressed != null) {
                OutlinedButton(
                    onClick = onActionPressed,
                    modifier = Modifier.width(250.dp),
                    colors =
                        ButtonDefaults.outlinedButtonColors(
                            containerColor =
                                Color(0xFF212121) // Replace with your dark color
                        )
                ) {
                    Text(
                        text = actionText,
                        style =
                            MaterialTheme.typography.bodyMedium.copy(
                                color = Color(0xFFFFFFFF)
                            ) // Replace with your light color
                    )
                }
            }
        }
    }
}
