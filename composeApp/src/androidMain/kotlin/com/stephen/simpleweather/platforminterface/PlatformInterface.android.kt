package com.stephen.simpleweather.platforminterface

import android.app.Activity.CONNECTIVITY_SERVICE
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.VibrationEffect
import android.os.VibratorManager
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.stephen.simpleweather.appContext

actual object PlatformInterface {
    actual fun getSystemCurrentMills() = System.currentTimeMillis()

    actual fun openNetworkSettingPage() {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        appContext.startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    actual fun vibrate() {
        val vibrator = appContext.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibrator.defaultVibrator.vibrate(VibrationEffect.createOneShot(20, 60))
    }
}