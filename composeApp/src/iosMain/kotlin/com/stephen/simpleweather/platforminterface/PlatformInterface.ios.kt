package com.stephen.simpleweather.platforminterface

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970


actual object PlatformInterface {
    actual fun getSystemCurrentMills(): Long = (NSDate().timeIntervalSince1970 * 1000).toLong()

    actual fun openNetworkSettingPage() {

    }

    actual fun vibrate() {

    }
}