package com.myfitnessbag.order.platform_module

import android.util.Log


import com.myfitnessbag.order.core.util.error.AppException
import com.myfitnessbag.order.core.util.error.ErrorModel


actual object AppLogger {

    actual fun e(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) {
            Log.e(tag, message, throwable)
        } else {
            Log.e(tag, message)
        }
    }

    actual fun d(tag: String, message: String) {
        Log.d(tag, message)
    }

    actual fun i(tag: String, message: String) {
        Log.i(tag, message)
    }

    actual fun appError(error: AppException) {
        Log.e("appError", error.error.toString())
    }


    actual fun errorModel(error: ErrorModel) {
        Log.e("appError", error.toString())
    }


}