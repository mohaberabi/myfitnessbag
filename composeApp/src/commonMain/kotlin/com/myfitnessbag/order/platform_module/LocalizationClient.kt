package com.myfitnessbag.order.platform_module

import com.myfitnessbag.order.core.util.error.AppError
import com.myfitnessbag.order.core.util.AppResult

expect class LocalizationClient {
    fun getLocal(): String
}