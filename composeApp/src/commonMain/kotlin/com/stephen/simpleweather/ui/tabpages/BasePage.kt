package com.stephen.simpleweather.ui.tabpages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stephen.simpleweather.ui.component.CenterText
import com.stephen.simpleweather.ui.theme.pageTitleText

@Composable
fun BasePage(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(1f).padding(start = 20.dp, end = 20.dp)) {
        CenterText(
            title,
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
            style = pageTitleText,
        )
        content()
    }
}