package com.stephen.simpleweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.stephen.simpleweather.platforminterface.ConnectivityStatus


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App { isDark ->
                updateTheme(isDark)
            }
        }
    }

    private fun updateTheme(isDarkTheme: Boolean) {
        val systemBarStyle = if (isDarkTheme) {
            SystemBarStyle.dark(darkScrim)
        } else {
            SystemBarStyle.light(lightScrim, darkScrim)
        }
        val navigationBarStyle = if (isDarkTheme) {
            SystemBarStyle.dark(darkNavigationScrim)
        } else {
            SystemBarStyle.light(lightNavigationScrim, darkNavigationScrim)
        }
        enableEdgeToEdge(statusBarStyle = systemBarStyle, navigationBarStyle = navigationBarStyle)
    }

    override fun onDestroy() {
        super.onDestroy()
        ConnectivityStatus.stop()
    }
}


/**
 * Android light scrim color.
 */
private val lightScrim = android.graphics.Color.argb(0xff, 0xee, 0xee, 0xee)
private val lightNavigationScrim = android.graphics.Color.argb(0xff, 0xf3, 0xf3, 0xf3)

/**
 * Android dark scrim color.
 */
private val darkScrim = android.graphics.Color.argb(0x80, 0x10, 0x10, 0x10)
private val darkNavigationScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)
