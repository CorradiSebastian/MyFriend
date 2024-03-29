package com.sebastiancorradi.myfriend.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberWindowInfo(): WindowInfo{
    val configuration = LocalConfiguration.current
    return WindowInfo(
        screenWidthInfo = when  {
            configuration.screenWidthDp < 600 -> WindowInfo.WindowType.Compact //according android documentation
            configuration.screenWidthDp < 840 -> WindowInfo.WindowType.Medium //according android documentation
            else -> WindowInfo.WindowType.Expanded //according android documentation
        },
                screenHeightInfo = when  {
            configuration.screenHeightDp < 480 -> WindowInfo.WindowType.Compact //according android documentation
            configuration.screenHeightDp < 900 -> WindowInfo.WindowType.Medium //according android documentation
            else -> WindowInfo.WindowType.Expanded //according android documentation
        },
        screenHeight = configuration.screenHeightDp.dp,
        screenWidth = configuration.screenWidthDp.dp,
    )
}

data class WindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Dp,
    val screenHeight: Dp
) {
    sealed class WindowType{
        object Compact: WindowType()
        object Medium: WindowType()
        object Expanded: WindowType()
    }
}