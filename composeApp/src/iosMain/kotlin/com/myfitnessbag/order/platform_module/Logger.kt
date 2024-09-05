package com.myfitnessbag.order.platform_module

import com.myfitnessbag.order.core.util.error.AppException
import com.myfitnessbag.order.core.util.error.ErrorModel
import platform.Foundation.NSLog
import platform.posix.err


actual object AppLogger {
    actual fun e(tag: String, message: String, throwable: Throwable?) {

        if (throwable != null) {
            NSLog("ERROR: [$tag] $message. Throwable: $throwable CAUSE ${throwable.cause}")
        } else {
            NSLog("ERROR: [$tag] $message")
        }
    }

    actual fun d(tag: String, message: String) {
        NSLog("DEBUG: [$tag] $message")
    }

    actual fun i(tag: String, message: String) {
        NSLog("INFO: [$tag] $message")
    }

    actual fun appError(error: AppException) {

        NSLog("appError ${error}")

    }

    actual fun errorModel(error: ErrorModel) {
        NSLog("appError ${error.toString()}")

    }


}