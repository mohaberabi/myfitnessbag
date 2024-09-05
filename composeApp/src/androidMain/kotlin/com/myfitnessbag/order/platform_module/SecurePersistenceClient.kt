package com.myfitnessbag.order.platform_module

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.myfitnessbag.order.core.domain.source.local.PersistenceClient
import com.myfitnessbag.order.core.util.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

actual class SecurePersistenceClient(
    private val context: Context,
    private val dispatchers: DispatchersProvider,
) : PersistenceClient {

    companion object {
        private const val FILE_NAME = "secure_prefs"
    }

    private val masterKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        FILE_NAME,
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override suspend fun set(
        key: String,
        value: String,
    ) {
        withContext(dispatchers.io) {
            try {
                sharedPreferences.edit().putString(key, value).apply()
            } catch (e: Exception) {
                e.printStackTrace()
                throw SecurePersistenceException.WriteFailureException(
                    cause = e,
                    message = e.message
                )
            }

        }
    }

    override fun read(key: String): Flow<String?> {
        val value = sharedPreferences.getString(
            key, null,
        )
        return flowOf(value)
    }

    override suspend fun clear() {
        withContext(dispatchers.io) {
            try {
                sharedPreferences.edit().clear().apply()
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
                sharedPreferences.edit().remove(key).apply()
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