package com.stephen.simpleweather.platforminterface

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * 常规小功能跨平台实现的接口
 */
expect object PlatformInterface {
    fun getSystemCurrentMills(): Long
    fun openNetworkSettingPage()
    fun vibrate()
}

/**
 * 网络监听状态
 * 功能较单一，另分一个类
 */
expect object ConnectivityStatus {
    val isNetworkConnected: MutableStateFlow<Boolean>
    fun start()
    fun stop()
    fun getStatus():Boolean
}