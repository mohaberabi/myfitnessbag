package com.myfitnessbag.order.core.data.repository

import com.myfitnessbag.order.core.domain.repository.AppPrefsRepository
import com.myfitnessbag.order.core.util.error.AppError
import com.myfitnessbag.order.core.util.EmptyDataResult
import com.myfitnessbag.order.platform_module.LocalizationClient
import com.myfitnessbag.order.platform_module.UrlLauncher

class DefaultAppPrefsRepository(
    private val localizationClient: LocalizationClient,
    private val urlLauncher: UrlLauncher,
) : AppPrefsRepository {
    override fun changeLocal(
    ): EmptyDataResult<AppError> = urlLauncher.openAppSettings()

    override fun getLocal() = localizationClient.getLocal()
}