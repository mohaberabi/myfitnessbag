package com.myfitnessbag.order.core.domain.source.local

import kotlinx.coroutines.flow.Flow

interface PersistenceClient {
    suspend fun set(
        key: String,
        value: String,
    )

    fun read(
        key: String,
    ): Flow<String?>

    suspend fun clear()
    suspend fun delete(key: String)
}


