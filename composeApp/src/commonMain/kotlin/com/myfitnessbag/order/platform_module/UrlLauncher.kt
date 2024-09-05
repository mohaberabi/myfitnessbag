package com.myfitnessbag.order.platform_module

import com.myfitnessbag.order.core.util.error.AppError
import com.myfitnessbag.order.core.util.EmptyDataResult


expect class UrlLauncher {
    fun openAppSettings(): EmptyDataResult<AppError>
}