package com.stephen.simpleweather.data

import com.stephen.simpleweather.model.WeatherResponse


sealed class WeatherScreenState {
    data object Loading : WeatherScreenState()
    data object NetworkClose : WeatherScreenState()
    data class Error(val errorMessage: String) : WeatherScreenState()
    data class Success(val responseData: MutableList<WeatherResponse>) : WeatherScreenState()
}

data class WeatherState(
    val isLoading: Boolean = true,
    val isNetworkClose: Boolean = false,
    val errorMessage: String? = null,
    val responseData: MutableList<WeatherResponse>? = null
) {
    fun toUiState(): WeatherScreenState {
        return if (isNetworkClose) {
            WeatherScreenState.NetworkClose
        } else if (isLoading) {
            WeatherScreenState.Loading
        } else if (errorMessage?.isNotEmpty() == true) {
            WeatherScreenState.Error(errorMessage)
        } else {
            WeatherScreenState.Success(responseData!!)
        }
    }
}
