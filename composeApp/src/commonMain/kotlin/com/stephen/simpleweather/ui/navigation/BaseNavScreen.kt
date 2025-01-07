package com.stephen.simpleweather.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import simpleweather.composeapp.generated.resources.Res
import simpleweather.composeapp.generated.resources.back_button

@Composable
fun BaseNavScreen(onBackButtonClick: () -> Unit = {}, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(1f).padding(start = 20.dp, end = 20.dp, top = 70.dp)) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            modifier = Modifier.clickable {
                onBackButtonClick()
            },
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = stringResource(Res.string.back_button)
        )
        Spacer(Modifier.padding(vertical = 20.dp))
        content()
    }
}