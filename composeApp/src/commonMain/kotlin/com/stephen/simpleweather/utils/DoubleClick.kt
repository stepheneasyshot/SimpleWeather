package com.stephen.simpleweather.utils

import com.stephen.simpleweather.platforminterface.PlatformInterface

private var lastTriggerTime: Long = 0

/**
 * 判断快速请求，默认500ms
 */
fun isFastDoubleClick(triggerInteval: Long = 500): Boolean {
    val currentTime = PlatformInterface.getSystemCurrentMills()
    val elapsedTime = currentTime - lastTriggerTime
    // 小于设定的时长，视为快速点击
    return (elapsedTime <= triggerInteval).apply {
        if (!this)
            // 如果不是快速点击，更新触发时间
            lastTriggerTime = currentTime
    }
}