package com.stephen.simpleweather.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.stephen.simpleweather.ui.theme.LauncherCardBackColor
import org.jetbrains.compose.resources.painterResource
import simpleweather.composeapp.generated.resources.Res
import simpleweather.composeapp.generated.resources.ic_launcher

@Composable
fun AboutScreen(onBack: () -> Unit) {
    BaseNavScreen(onBackButtonClick = onBack) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth(1f)
                .height(160.dp)
                .clip(RoundedCornerShape(10))
                .background(LauncherCardBackColor)
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_launcher),
                contentDescription = null,
                modifier = Modifier.height(100.dp).width(100.dp)
            )
        }
    }
}