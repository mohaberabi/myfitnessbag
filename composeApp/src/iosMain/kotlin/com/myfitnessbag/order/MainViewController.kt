package com.myfitnessbag.order

import androidx.compose.ui.window.ComposeUIViewController
import com.myfitnessbag.order.app.presentation.MyFitnessBagApp
import com.myfitnessbag.order.core.util.constants.AppShortcuts
import com.myfitnessbag.order.platform_module.KoinInit
import platform.UIKit.UIApplicationShortcutItem

fun MainViewController(
) = ComposeUIViewController(
    configure = {
        KoinInit().init()
    }
) {
    MyFitnessBagApp(
    )
}

