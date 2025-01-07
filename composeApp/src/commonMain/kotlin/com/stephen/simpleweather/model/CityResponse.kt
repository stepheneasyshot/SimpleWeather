package com.stephen.simpleweather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityResponse(
    @SerialName("error_code")
    val errorCode: Int,
    @SerialName("reason")
    val reason: String,
    @SerialName("result")
    val result: List<CityResult>
)

@Serializable
data class CityResult(
    @SerialName("city")
    val city: String,
    @SerialName("district")
    val district: String,
    @SerialName("id")
    val id: String,
    @SerialName("province")
    val province: String
)