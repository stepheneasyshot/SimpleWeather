package com.stephen.simpleweather.viewmodel

import com.stephen.simpleweather.data.WeatherScreenState
import com.stephen.simpleweather.data.WeatherState
import com.stephen.simpleweather.network.ApiStatus
import com.stephen.simpleweather.network.NetworkRepository
import com.stephen.simpleweather.platforminterface.ConnectivityStatus
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val networkRepository: NetworkRepository) {
    private val _weatherState = MutableStateFlow(WeatherState())
    private val _weatherViewState: MutableStateFlow<WeatherScreenState> =
        MutableStateFlow(WeatherScreenState.Loading)
    val weatherViewState = _weatherViewState.asStateFlow()

    init {
        // 开始网络监听
        ConnectivityStatus.start()

        // 先预存一次支持的城市列表
        CoroutineScope(Dispatchers.IO).launch {
            try {
                networkRepository.getSupportedCityList()
                    .collect { response ->
                        Napier.i(response.toString())
                    }
            } catch (e: Exception) {
                Napier.e(e.printStackTrace().toString())
            }
        }
    }

    suspend fun initGetData() {
        // 默认状态，网络未开启
        _weatherState.update {
            it.copy(
                isNetworkClose = true,
            )
        }
        _weatherViewState.value = _weatherState.value.toUiState()
        ConnectivityStatus.isNetworkConnected.collect { isNetworkConnected ->
            Napier.i("networkInit isNetworkConnected $isNetworkConnected")
            if (isNetworkConnected) {
                getWeatherDataList()
            } else {
                _weatherState.update {
                    it.copy(
                        isNetworkClose = true,
                    )
                }
                _weatherViewState.value = _weatherState.value.toUiState()
            }
        }
    }

    private suspend fun getWeatherDataList() {
        Napier.i("===========>getWeatherDataList<==========")
        CoroutineScope(Dispatchers.IO).launch {
            delay(1_000L)
            try {
                networkRepository.getWeatherDataList()
                    .collect { response ->
                        when (response.status) {
                            ApiStatus.LOADING -> {
                                _weatherState.update {
                                    it.copy(
                                        isNetworkClose = false,
                                        isLoading = true
                                    )
                                }
                            }

                            ApiStatus.SUCCESS -> {
                                _weatherState.update {
                                    it.copy(
                                        isNetworkClose = false,
                                        isLoading = false,
                                        errorMessage = "",
                                        responseData = response.data
                                    )
                                }
                            }

                            ApiStatus.ERROR -> {
                                _weatherState.update {
                                    it.copy(
                                        isNetworkClose = false,
                                        isLoading = false,
                                        errorMessage = response.message
                                    )
                                }
                            }
                        }
                    }
            } catch (e: Exception) {
                Napier.e(e.printStackTrace().toString())
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Failed to fetch data"
                    )
                }
            }
            _weatherViewState.value = _weatherState.value.toUiState()
        }
    }
}