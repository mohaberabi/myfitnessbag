package com.myfitnessbag.order.core.domain.source.local

import com.myfitnessbag.order.core.util.error.AppError
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.EmptyDataResult

interface TokenStorage {
    suspend fun getToken(): String?
    suspend fun clearToken()
    suspend fun saveToken(token: String)
}