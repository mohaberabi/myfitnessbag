package com.myfitnessbag.order.platform_module

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.myfitnessbag.order.core.util.error.AppError
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.error.CommonError
import com.myfitnessbag.order.core.util.EmptyDataResult


actual class UrlLauncher(private val context: Context) {
    actual fun openAppSettings(): EmptyDataResult<AppError> {
        return try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
            context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            AppResult.Done(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            AppResult.Error(CommonError.UNKNOWN)
        }
    }
}