package com.stephen.simpleweather.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.stephen.simpleweather.data.WeatherScreenState
import com.stephen.simpleweather.platforminterface.PlatformInterface
import com.stephen.simpleweather.ui.component.CenterText
import com.stephen.simpleweather.ui.component.rememberDialogState
import com.stephen.simpleweather.ui.tabpages.BottomTab
import com.stephen.simpleweather.ui.tabpages.DiscoverySection
import com.stephen.simpleweather.ui.tabpages.MoreSection
import com.stephen.simpleweather.ui.tabpages.WeatherSection
import com.stephen.simpleweather.ui.theme.TabBackColorDark
import com.stephen.simpleweather.ui.theme.TabBackColorLight
import com.stephen.simpleweather.ui.theme.TabColorDark
import com.stephen.simpleweather.ui.theme.TabColorLight
import com.stephen.simpleweather.ui.theme.bottomTabText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun HomeScreen(
    weatherScreenState: WeatherScreenState,
    onNavToAbout: () -> Unit,
    onNavToAuthor: () -> Unit,
) {
    WeatherScaffold(weatherScreenState, onNavToAbout, onNavToAuthor)
    NetworkError(weatherScreenState is WeatherScreenState.NetworkClose)
}


@Composable
private fun WeatherScaffold(
    weatherState: WeatherScreenState, onNavToAbout: () -> Unit,
    onNavToAuthor: () -> Unit,
) {
    val tabList = listOf(BottomTab.Weather, BottomTab.Discovery, BottomTab.About)
    val (currentSection, setCurrentSection) = rememberSaveable { mutableStateOf(BottomTab.Weather) }
    Scaffold(
        content = { paddingValues ->
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
                    .consumeWindowInsets(paddingValues)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal),
                    ),
            ) {
                when (currentSection) {
                    BottomTab.Weather -> WeatherSection(weatherState)
                    BottomTab.Discovery -> DiscoverySection()
                    BottomTab.About -> MoreSection(onNavToAbout, onNavToAuthor)
                }
            }
        },
        bottomBar = {
            BottomNav(currentSection, items = tabList, onSectionSelect = setCurrentSection)
        },
    )
}

@Composable
private fun BottomNav(
    currentSection: BottomTab,
    onSectionSelect: (BottomTab) -> Unit,
    items: List<BottomTab>,
) {
    BottomAppBar(
        containerColor = if (isSystemInDarkTheme()) TabBackColorDark else TabBackColorLight
    ) {
        items.forEach { section ->
            val selected = section == currentSection
            val title = section.title
            BottomNavItem(
                onClick = { onSectionSelect(section) },
                modifier = Modifier.weight(1f).fillMaxSize(1f).padding(2.dp)
                    .clip(RoundedCornerShape(20)),
                icon = {
                    Image(
                        modifier = Modifier.height(32.dp).width(32.dp),
                        painter = painterResource(
                            if (selected) section.selectedIcon
                            else section.icon
                        ),
                        contentDescription = stringResource(title),
                    )
                },
                label = {
                    CenterText(
                        stringResource(title),
                        style = bottomTabText,
                        color = if (selected) MaterialTheme.colorScheme.primary
                        else if (isSystemInDarkTheme()) TabColorDark else
                            TabColorLight
                    )
                },
            )
        }
    }
}

@Composable
fun BottomNavItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    label: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = modifier.clickable {
            PlatformInterface.vibrate()
            onClick.invoke()
        },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            icon()
            if (label != null) {
                label()
            }
        }
    }
}

@Composable
fun NetworkError(isNetworkClose: Boolean) {
    val networkDialogState = rememberDialogState()
    if (isNetworkClose)
        networkDialogState.show(
            title = "网络未连接",
            content = "是否跳转到系统网络设置界面",
            okText = "确定",
            cancelText = "取消",
            onOk = {
                PlatformInterface.openNetworkSettingPage()
                networkDialogState.hide()
            },
            onCancel = {
                networkDialogState.hide()
            },
            onDismiss = {
                // Do nothing
            }
        )
    else
        networkDialogState.hide()
}