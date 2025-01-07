package com.stephen.simpleweather.ui.navigation

import org.jetbrains.compose.resources.StringResource
import simpleweather.composeapp.generated.resources.Res
import simpleweather.composeapp.generated.resources.nav_about
import simpleweather.composeapp.generated.resources.nav_author
import simpleweather.composeapp.generated.resources.nav_main

enum class ScreenTitle(val title: StringResource) {
    Home(Res.string.nav_main),
    Author(Res.string.nav_author),
    About(Res.string.nav_about),
}