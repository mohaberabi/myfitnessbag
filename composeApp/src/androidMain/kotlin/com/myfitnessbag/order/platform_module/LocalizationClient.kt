package com.myfitnessbag.order.platform_module

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.getSystemService
import com.myfitnessbag.order.core.domain.model.AppLocales
import com.myfitnessbag.order.core.util.error.AppError
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.error.CommonError


actual class LocalizationClient(
    context: Context,
) {

    private val localManager = context.getSystemService<LocaleManager>()!!

    actual fun getLocal(): String = currentLanguage()

    private fun currentLanguage(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            localManager.applicationLocales[0]?.toLanguageTag()?.split("-")?.firstOrNull()
                ?: AppLocales.En.code
        } else {
            AppCompatDelegate.getApplicationLocales()
                .toLanguageTags().split("-").firstOrNull() ?: AppLocales.En.code
        }
    }
}

