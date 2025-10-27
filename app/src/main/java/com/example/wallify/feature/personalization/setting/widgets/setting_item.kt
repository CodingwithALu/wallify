package com.example.wallify.feature.personalization.setting.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.wallify.common.widgets.custom_shapes.container.TRoundedContainer
import com.example.wallify.utlis.constants.TSizes

@Composable
fun SettingItem(
    onClickItem: () -> Unit = {},
    title: String,
    subtitle: String,
    imageItem: Int
) {
    val dark = isSystemInDarkTheme()
    TRoundedContainer(
        height = 72.dp,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onClickItem()
                    }
                    .padding(horizontal = TSizes.lg)
            ) {
                Image(
                    painter = painterResource(imageItem),
                    contentDescription = null,
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp),
                    colorFilter = ColorFilter.tint(if (dark) Color.White else Color.Black)
                )
                Spacer(modifier = Modifier.width(TSizes.defaultSpace))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = TSizes.fontSizeLg,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = subtitle,
                        fontSize = TSizes.fontSizeSm,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    )
}