package com.stephen.simpleweather.ui.tabpages

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.stephen.simpleweather.data.WeatherScreenState
import com.stephen.simpleweather.data.choosedCityList
import com.stephen.simpleweather.model.WeatherResponse
import com.stephen.simpleweather.model.WeatherResult
import com.stephen.simpleweather.ui.component.CenterText
import com.stephen.simpleweather.ui.component.VerticalText
import com.stephen.simpleweather.ui.component.WeSearchBar
import com.stephen.simpleweather.ui.component.rememberToastState
import com.stephen.simpleweather.ui.component.skeleton.WeSkeleton
import com.stephen.simpleweather.ui.theme.itemTitleText
import com.stephen.simpleweather.ui.theme.temText
import com.stephen.simpleweather.ui.theme.weatherInfoText
import com.stephen.simpleweather.ui.theme.weatherTempText
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import simpleweather.composeapp.generated.resources.Res
import simpleweather.composeapp.generated.resources.bg_clody
import simpleweather.composeapp.generated.resources.bg_foggy
import simpleweather.composeapp.generated.resources.bg_rainy
import simpleweather.composeapp.generated.resources.bg_snowy
import simpleweather.composeapp.generated.resources.bg_sunny
import simpleweather.composeapp.generated.resources.bg_windy
import simpleweather.composeapp.generated.resources.home_title_weather

@Composable
fun WeatherSection(weatherState: WeatherScreenState) {
    BasePage(stringResource(Res.string.home_title_weather)) {
        val toast = rememberToastState()
        val weatherResultList =
            remember { mutableStateOf<MutableList<WeatherResponse>>(mutableListOf()) }

        when (weatherState) {
            is WeatherScreenState.Loading -> {}
            is WeatherScreenState.Success -> weatherResultList.value = weatherState.responseData
            is WeatherScreenState.Error -> weatherResultList.value = mutableListOf()
            WeatherScreenState.NetworkClose -> {}
        }
        WeSearchBar(
            value = "",
            placeholder = "添加地点",
            disabled = true,
            onClick = {
                toast.show(title = "暂未开放")
            },
        ) {}
        LazyColumn(
            modifier = Modifier.fillMaxSize(1f).padding(top = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(choosedCityList) { city ->
                WeatherCard(
                    city,
                    weatherResultList.value.find { it.weatherResult.city == city }?.weatherResult
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun WeatherCard(city: String, weatherResult: WeatherResult?) {
    // 加载态
    WeSkeleton.Rectangle(weatherResult == null) {
        // 天气详情
        weatherResult?.let {
            val weatherinfo = it.realtime.info
            val bg: DrawableResource =
                if (weatherinfo.contains("云") || weatherinfo.contains("阴")) Res.drawable.bg_clody
                else if (weatherinfo.contains("雨")) Res.drawable.bg_rainy
                else if (weatherinfo.contains("雪")) Res.drawable.bg_snowy
                else if (weatherinfo.contains("雾")) Res.drawable.bg_foggy
                else if (weatherinfo.contains("风")) Res.drawable.bg_windy
                else if (weatherinfo.contains("晴")) Res.drawable.bg_sunny
                else Res.drawable.bg_sunny
            Image(
                painter = painterResource(bg),
                modifier = Modifier
                    .fillMaxSize(1f)
                    .alpha(if (isSystemInDarkTheme()) 0.7f else 1f),
                contentScale = ContentScale.FillBounds,
                contentDescription = "bg_weather"
            )
            Column(modifier = Modifier.fillMaxSize(1f)) {
                Row {
                    Spacer(Modifier.weight(1f))
                    CenterText(
                        "${it.realtime.temperature}℃",
                        modifier = Modifier.padding(end = 40.dp, top = 20.dp),
                        style = weatherTempText,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    // 晴天 阴天 雨天。。。。
                    CenterText(
                        weatherinfo,
                        style = weatherInfoText,
                        modifier = Modifier.padding(start = 20.dp),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    // 温度范围
                    VerticalText(
                        "最低",
                        style = temText,
                    )
                    CenterText(
                        "${it.realtime.temperature.toInt() - 5}℃",
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        style = weatherInfoText
                    )
                    VerticalText(
                        "最高",
                        style = temText,
                    )
                    CenterText(
                        "${it.realtime.temperature.toInt() + 3}℃",
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        style = weatherInfoText
                    )
                }
            }
        }
        // 城市title
        CenterText(
            city,
            modifier = Modifier.padding(start = 20.dp, top = 20.dp),
            style = itemTitleText
        )
    }
}