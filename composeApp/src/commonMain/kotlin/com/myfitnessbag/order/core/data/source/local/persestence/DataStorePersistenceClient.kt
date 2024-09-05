package com.myfitnessbag.order.core.data.source.local.persestence

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.myfitnessbag.order.core.domain.source.local.PersistenceClient
import com.myfitnessbag.order.core.util.DispatchersProvider
import com.myfitnessbag.order.core.util.PrefsDataStore
import com.myfitnessbag.order.core.util.error.AppException
import com.myfitnessbag.order.core.util.error.LocalError
import com.myfitnessbag.order.core.util.error.errorModel
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DataStorePersistenceClient(
    private val dispatchers: DispatchersProvider,
    private val datastore: PrefsDataStore,
) : PersistenceClient {
    override suspend fun set(
        key: String,
        value: String,
    ) {
        withContext(dispatchers.io) {
            safeCall {
                datastore.edit { it[stringPreferencesKey(key)] = value }
            }
        }

    }

    override fun read(
        key: String,
    ): Flow<String?> {
        return datastore.data.map { it[stringPreferencesKey(key)] }
            .flowOn(dispatchers.io)
    }


    override suspend fun clear() {
        withContext(dispatchers.io) {
            safeCall {
                datastore.edit { it.clear() }
            }
        }
    }


    override suspend fun delete(key: String) {
        withContext(dispatchers.io) {
            safeCall {
                datastore.edit { it.remove(stringPreferencesKey(key)) }
            }
        }
    }


    private inline fun <T> safeCall(
        block: () -> T,
    ): T {
        return try {
            block()
        } catch (e: IOException) {
            throw AppException.LocalException(
                errorModel(LocalError.IO) {
                    message = e.message
                    cause = e
                }
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            throw AppException.LocalException(
                errorModel(LocalError.UNKNOWN) {
                    message = "An unknown error occurred during DataStore operation"
                    cause = e
                }
            )
        }
    }

}