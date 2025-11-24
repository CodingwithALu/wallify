package com.example.wallify.feature.wallify.collections.collectionPhotos.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wallify.common.widgets.images.TRoundedImage
import com.example.wallify.feature.wallify.home.model.Collections
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.constants.TextString

@Composable
fun BoxHeader(
    collection: Collections,
    onDownloadAllClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
    showBoxHeader: Boolean = true
) {
    val transition = updateTransition(targetState = showBoxHeader, label = "BoxHeaderTransition")
    val autoHeight = 180.dp
    val animatedHeight by transition.animateDp(
        label = "AnimatedHeight",
        transitionSpec = { tween(durationMillis = 1200) }
    ) { visible ->
        if (visible) autoHeight else 0.dp
    }
    val animatedAlpha by transition.animateFloat(
        label = "AnimatedAlpha",
        transitionSpec = { tween(durationMillis = 1200) }
    ) { visible ->
        if (visible) 1f else 0f
    }
    AnimatedVisibility(
        visible = showBoxHeader,
        enter = slideInVertically(
            initialOffsetY = { -it },
            animationSpec = tween(1200) // chỉnh lâu hơn
        ) + fadeIn(animationSpec = tween(1200)),
        exit = slideOutVertically(
            targetOffsetY = { -it }, // trượt lên trên khi ẩn
            animationSpec = tween(1200) // chỉnh lâu hơn
        ) + fadeOut(animationSpec = tween(1200))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = TSizes.sm)
                .height(animatedHeight)
                .fillMaxWidth()
                .background(Color.Transparent)
                .alpha(animatedAlpha),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = collection.title,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Unsplash+ Collections row
            Row(verticalAlignment = Alignment.CenterVertically) {
                TRoundedImage(
                    imageUrl = collection.user.profileImage?.small ?: TextString.urlDefaultAvatar,
                    isNetworkImage = true,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = collection.user.name ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Button row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onDownloadAllClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock, // icon khoá
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Download all")
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    IconButton(
                        onClick = onShareClick,
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                            .size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = Color.Black
                        )
                    }
                    IconButton(
                        onClick = onMoreClick,
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                            .size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More",
                            tint = Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${collection.totalPhotos} images",
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray
            )
        }
    }
}