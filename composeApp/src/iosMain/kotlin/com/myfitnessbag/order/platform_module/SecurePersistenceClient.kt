package com.myfitnessbag.order.platform_module

import com.myfitnessbag.order.KeyChainHelper
import com.myfitnessbag.order.core.domain.source.local.PersistenceClient
import com.myfitnessbag.order.core.util.DispatchersProvider
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

@OptIn(ExperimentalForeignApi::class)

actual class SecurePersistenceClient(
    private val dispatchers: DispatchersProvider,
) : PersistenceClient {
    override suspend fun set(key: String, value: String) {
        withContext(dispatchers.io) {
            try {
                val done = KeyChainHelper.saveWithKey(key, value)
                if (!done) {
                    throw SecurePersistenceException.WriteFailureException()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                throw SecurePersistenceException.WriteFailureException(
                    cause = e,
                    message = e.message
                )
            }
        }
    }

    override fun read(
        key: String,
    ): Flow<String?> = flowOf(
        KeyChainHelper.getWithKey(key),
    ).flowOn(dispatchers.io)


    override suspend fun clear() {

        withContext(dispatchers.io) {
            try {
                KeyChainHelper.clear()
            } catch (e: Exception) {
                e.printStackTrace()
                throw SecurePersistenceException.ClearFailureException(
                    cause = e,
                    message = e.message
                )
            }
        }
    }

    override suspend fun delete(key: String) {
        withContext(dispatchers.io) {
            try {
                KeyChainHelper.removeWithKey(key)
            } catch (e: Exception) {
                e.printStackTrace()
                throw SecurePersistenceException.DeleteFailureException(
                    cause = e,
                    message = e.message
                )
            }
        }
    }

}