package com.stephen.simpleweather.ui.tabpages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stephen.simpleweather.ui.component.WeDivider
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import simpleweather.composeapp.generated.resources.Res
import simpleweather.composeapp.generated.resources.home_title_more
import simpleweather.composeapp.generated.resources.ic_arrow_right
import simpleweather.composeapp.generated.resources.more_version
import simpleweather.composeapp.generated.resources.more_versioncode
import simpleweather.composeapp.generated.resources.nav_about
import simpleweather.composeapp.generated.resources.nav_about_content
import simpleweather.composeapp.generated.resources.nav_author

@Composable
fun MoreSection(
    onNavToAbout: () -> Unit,
    onNavToAuthor: () -> Unit,
) {
    BasePage(stringResource(Res.string.home_title_more)) {
        MenuGroupItem(
            stringResource(Res.string.nav_about),
            stringResource(Res.string.nav_about_content),
            onClick = onNavToAbout
        )
        MenuGroupItem(stringResource(Res.string.nav_author), onClick = onNavToAuthor)
        MenuGroupItem(
            stringResource(Res.string.more_version),
            stringResource(Res.string.more_versioncode),
            isNeedToNavigate = false,
            isLast = true
        )
    }
}

@Composable
fun MenuGroupItem(
    title: String,
    content: String? = null,
    onClick: () -> Unit = {},
    isNeedToNavigate: Boolean = true,
    isLast: Boolean = false
) {
    Column {
        Row(
            Modifier.padding(vertical = 16.dp).clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 17.sp,
                )
                content?.let {
                    Text(
                        text = it,
                        modifier = Modifier.alpha(0.6f),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 13.sp,
                    )
                }
            }
            // 需要导航的才显示箭头
            if (isNeedToNavigate)
                Icon(
                    modifier = Modifier.height(20.dp).width(16.dp),
                    painter = painterResource(Res.drawable.ic_arrow_right),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary,
                )
        }
        // 最后一个不需要分割线
        if (!isLast)
            WeDivider(Modifier.padding(horizontal = 5.dp))
    }
}