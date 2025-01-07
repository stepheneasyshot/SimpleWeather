package com.stephen.simpleweather.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stephen.simpleweather.ui.component.CenterText
import com.stephen.simpleweather.ui.theme.pageTitleText

@Composable
fun AuthorScreen(onBack: () -> Unit) {
    BaseNavScreen(onBackButtonClick = onBack) {
        CenterText(
            "作者页面",
            modifier = Modifier.padding(10.dp).fillMaxSize(1f)
                .background(MaterialTheme.colorScheme.background),
            style = pageTitleText
        )
    }
}