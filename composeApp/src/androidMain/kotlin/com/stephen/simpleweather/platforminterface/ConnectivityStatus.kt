package com.stephen.simpleweather.platforminterface

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import com.stephen.simpleweather.appContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

actual object ConnectivityStatus {
    actual val isNetworkConnected = MutableStateFlow(true)

    private var connectivityManager: ConnectivityManager? = null
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            Log.d("Connectivity status", "Network available")
            isNetworkConnected.value = true
        }

        override fun onLost(network: Network) {
            Log.d("Connectivity status", "Network lost")
            isNetworkConnected.value = false
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)

            val isConnected =
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED) &&
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)

            Log.d(
                "Connectivity status", "Network status: ${
                    if (isConnected) {
                        "Connected"
                    } else {
                        "Disconnected"
                    }
                }"
            )

            isNetworkConnected.value = isConnected
        }
    }

    actual fun start() {
        try {
            if (connectivityManager == null) {
                connectivityManager =
                    appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            }
            // API 24 and above
            connectivityManager?.registerDefaultNetworkCallback(networkCallback)

            val currentNetwork = connectivityManager?.activeNetwork

            if (currentNetwork == null) {
                isNetworkConnected.value = false

                Log.d("Connectivity status", "Disconnected")
            }

            Log.d("Connectivity status", "Started")
        } catch (e: Exception) {
            Log.d("Connectivity status", "Failed to start: ${e.message.toString()}")
            e.printStackTrace()
            isNetworkConnected.value = false
        }
    }

    actual fun stop() {
        connectivityManager?.unregisterNetworkCallback(networkCallback)
        Log.d("Connectivity status", "Stopped")
    }

    actual fun getStatus(): Boolean = isNetworkConnected.value
}