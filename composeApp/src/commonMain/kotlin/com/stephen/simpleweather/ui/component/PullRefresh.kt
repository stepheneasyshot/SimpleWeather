package com.stephen.simpleweather.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stephen.simpleweather.utils.isFastDoubleClick
import dev.materii.pullrefresh.PullRefreshLayout
import dev.materii.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PullRefresh(
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val toast = rememberToastState()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            if (isFastDoubleClick(5_000)) {
                toast.show("刷新太频繁了", icon = ToastIcon.FAIL)
            } else {
                onRefresh.invoke()
                scope.launch {
                    isRefreshing = true
                    delay(2_000)
                    isRefreshing = false
                }
            }
        }
    )
    PullRefreshLayout(
        modifier = modifier.fillMaxSize(1f),
        state = pullRefreshState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.TopCenter
        ) {
            content()
        }
    }
}