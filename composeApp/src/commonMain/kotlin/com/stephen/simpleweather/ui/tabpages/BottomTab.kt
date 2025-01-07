package com.stephen.simpleweather.ui.tabpages

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import simpleweather.composeapp.generated.resources.Res
import simpleweather.composeapp.generated.resources.home_title_more
import simpleweather.composeapp.generated.resources.home_title_discovery
import simpleweather.composeapp.generated.resources.home_title_weather
import simpleweather.composeapp.generated.resources.ic_about
import simpleweather.composeapp.generated.resources.ic_about_night
import simpleweather.composeapp.generated.resources.ic_about_selected
import simpleweather.composeapp.generated.resources.ic_discovery
import simpleweather.composeapp.generated.resources.ic_discovery_night
import simpleweather.composeapp.generated.resources.ic_discovery_selected
import simpleweather.composeapp.generated.resources.ic_weather
import simpleweather.composeapp.generated.resources.ic_weather_night
import simpleweather.composeapp.generated.resources.ic_weather_selected

enum class BottomTab(
    val title: StringResource,
    val icon: DrawableResource,
    val nightIcon: DrawableResource,
    val selectedIcon: DrawableResource
) {
    Weather(
        Res.string.home_title_weather,
        Res.drawable.ic_weather,
        Res.drawable.ic_weather_night,
        Res.drawable.ic_weather_selected
    ),
    Discovery(
        Res.string.home_title_discovery,
        Res.drawable.ic_discovery,
        Res.drawable.ic_discovery_night,
        Res.drawable.ic_discovery_selected
    ),
    About(
        Res.string.home_title_more,
        Res.drawable.ic_about,
        Res.drawable.ic_about_night,
        Res.drawable.ic_about_selected
    )
}