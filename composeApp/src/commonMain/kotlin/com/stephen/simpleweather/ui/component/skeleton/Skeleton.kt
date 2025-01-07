package com.stephen.simpleweather.ui.component.skeleton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

object WeSkeleton {

    @Composable
    fun Rectangle(isActive: Boolean = true, content: @Composable () -> Unit) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(200.dp)
                .clip(RoundedCornerShape(10))
                .background(MaterialTheme.colorScheme.onBackground)
                .shimmerLoading(isActive)
        ) {
            // 停止加载时显示内容，透明度动画稍慢
            AnimatedVisibility(
                !isActive,
                enter = fadeIn(animationSpec = spring(stiffness = Spring.StiffnessLow))
            ) {
                content()
            }
        }
    }
}
