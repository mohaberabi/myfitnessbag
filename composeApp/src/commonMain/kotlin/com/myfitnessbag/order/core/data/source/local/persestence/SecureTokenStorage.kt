package com.myfitnessbag.order.core.data.source.local.persestence

import com.myfitnessbag.order.core.domain.source.local.PersistenceClient
import com.myfitnessbag.order.core.domain.source.local.TokenStorage
import com.myfitnessbag.order.core.util.error.AppError
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.error.CommonError
import com.myfitnessbag.order.core.util.EmptyDataResult
import com.myfitnessbag.order.core.util.error.ErrorModel
import com.myfitnessbag.order.platform_module.SecurePersistenceException
import kotlinx.coroutines.flow.firstOrNull


class SecureTokenStorage(
    private val persistenceClient: PersistenceClient,
) : TokenStorage {
    companion object {
        private const val KEY = "tokenKey"
    }

    override suspend fun getToken(): String? = persistenceClient.read(KEY).firstOrNull()
    override suspend fun clearToken() = persistenceClient.delete(KEY)
    override suspend fun saveToken(
        token: String,
    ) = persistenceClient.set(KEY, token)


}