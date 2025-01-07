package com.stephen.simpleweather.platforminterface

import kotlinx.coroutines.flow.MutableStateFlow

actual object PlatformInterface {
    actual fun getSystemCurrentMills() = System.currentTimeMillis()

    actual fun openNetworkSettingPage() {
    }

    actual fun isNetworkAvaliable(): Boolean {
        return true
    }
}

actual object ConnectivityStatus {
    actual val isNetworkConnected: MutableStateFlow<Boolean> = MutableStateFlow(true)

    actual fun start() {
    }

    actual fun stop() {
    }

    actual fun getStatus(): Boolean = isNetworkConnected.value
}