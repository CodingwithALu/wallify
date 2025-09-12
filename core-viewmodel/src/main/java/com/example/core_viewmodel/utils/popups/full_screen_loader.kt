package com.example.core_viewmodel.utils.popups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.core_viewmodel.utils.loaders.AnimationLoaderWidget

// Nếu bạn dùng Lottie, import thêm:
// import com.airbnb.lottie.compose.*

/**
 * A utility composable for managing a full-screen loading dialog.
 */
@Composable
fun FullScreenLoader(
    isLoading: Boolean,
    text: String = "Loading...",
    animationRes: Int? = null, // Resource ID for Lottie animation (if needed)
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.95f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                 //Nếu bạn sử dụng Lottie Compose, thay thế bằng đoạn này:
                 if (animationRes != null) {
                     AnimationLoaderWidget(
                            animationRes = animationRes,
                            text = text
                     )
                 } else {
                     CircularProgressIndicator()
                 }
                 //Nếu chỉ cần hiệu ứng loading đơn giản:
                Spacer(modifier = Modifier.height(200.dp))
                Text(text, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}