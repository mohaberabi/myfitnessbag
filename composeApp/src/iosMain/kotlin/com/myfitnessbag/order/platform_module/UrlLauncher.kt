package com.myfitnessbag.order.platform_module

import com.myfitnessbag.order.core.util.error.AppError
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.error.CommonError
import com.myfitnessbag.order.core.util.EmptyDataResult
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

actual class UrlLauncher {
    actual fun openAppSettings(): EmptyDataResult<AppError> {
        return try {
            val url = NSURL(string = UIApplicationOpenSettingsURLString)
            if (UIApplication.sharedApplication.canOpenURL(url)) {
                UIApplication.sharedApplication.openURL(url)
            }
            AppResult.Done(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            AppResult.Error(CommonError.UNKNOWN)
        }
    }
}