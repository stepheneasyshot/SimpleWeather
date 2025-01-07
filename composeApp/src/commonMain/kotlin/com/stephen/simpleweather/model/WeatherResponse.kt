package com.stephen.simpleweather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("reason")
    val reason: String,
    @SerialName("result")
    val weatherResult: WeatherResult
)

@Serializable
data class WeatherResult(
    @SerialName("city")
    val city: String,
    @SerialName("future")
    val future: List<Future>,
    @SerialName("realtime")
    val realtime: Realtime
)

@Serializable
data class Future(
    @SerialName("date")
    val date: String,
    @SerialName("direct")
    val direct: String,
    @SerialName("temperature")
    val temperature: String,
    @SerialName("weather")
    val weather: String,
    @SerialName("wid")
    val wid: Wid
)

@Serializable
data class Realtime(
    @SerialName("aqi")
    val aqi: String,
    @SerialName("direct")
    val direct: String,
    @SerialName("humidity")
    val humidity: String,
    @SerialName("info")
    val info: String,
    @SerialName("power")
    val power: String,
    @SerialName("temperature")
    val temperature: String,
    @SerialName("wid")
    val wid: String
)

@Serializable
data class Wid(
    @SerialName("day")
    val day: String,
    @SerialName("night")
    val night: String
)
