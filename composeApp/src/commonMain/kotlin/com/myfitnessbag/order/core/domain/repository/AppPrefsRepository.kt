package com.myfitnessbag.order.core.domain.repository

import com.myfitnessbag.order.core.util.error.AppError
import com.myfitnessbag.order.core.util.EmptyDataResult

interface AppPrefsRepository {
    fun changeLocal(
    ): EmptyDataResult<AppError>

    fun getLocal(): String
}