package com.stephen.simpleweather

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.stephen.simpleweather.di.appModule
import com.stephen.simpleweather.ui.MainView
import com.stephen.simpleweather.ui.theme.WeUITheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App(onThemeUpdate: (isDarkTheme: Boolean) -> Unit = {}) {
    val isDarkTheme = isSystemInDarkTheme()
    onThemeUpdate(isDarkTheme)
    KoinApplication(application = { modules(appModule) }) {
        WeUITheme {
            MainView()
        }
    }
}