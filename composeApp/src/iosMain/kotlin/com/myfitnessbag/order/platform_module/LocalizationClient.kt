package com.myfitnessbag.order.platform_module

import com.myfitnessbag.order.core.util.error.AppError
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.error.CommonError
import platform.Foundation.NSBundle
import platform.Foundation.NSLocale
import platform.Foundation.NSUserDefaults
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual class LocalizationClient() {

    companion object {
        private const val KEY = "AppleLanguages"

    }

//    actual fun changeLocal(
//        local: String,
//    ): AppResult<String, AppError> {
//        return try {
//            NSUserDefaults.standardUserDefaults.setObject(arrayListOf(local), KEY)
//            NSUserDefaults.standardUserDefaults.synchronize()
//            AppResult.Done(currentLanguage())
//        } catch (e: Exception) {
//            e.printStackTrace()
//            AppResult.Error(CommonError.UNKNOWN)
//        }
//    }

    actual fun getLocal(): String = currentLanguage()

    private fun currentLanguage(): String {
        val preferredLanguages = NSBundle.mainBundle.preferredLocalizations
        val preferred = preferredLanguages.filterIsInstance<String>().firstOrNull()
        val languageCode = NSLocale.currentLocale.languageCode
        return preferred ?: languageCode
    }
}