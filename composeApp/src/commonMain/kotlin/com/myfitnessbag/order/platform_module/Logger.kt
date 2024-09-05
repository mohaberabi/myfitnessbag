package com.myfitnessbag.order.platform_module

import com.myfitnessbag.order.core.util.error.AppException
import com.myfitnessbag.order.core.util.error.ErrorModel


expect object AppLogger {
    fun e(tag: String, message: String, throwable: Throwable? = null)
    fun d(tag: String, message: String)
    fun i(tag: String, message: String)
    fun appError(error: AppException)
    fun errorModel(error: ErrorModel)
}