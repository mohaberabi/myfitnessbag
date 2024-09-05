package com.myfitnessbag.order.core.data.source.local.persestence

import com.myfitnessbag.order.core.domain.source.local.PersistenceClient

import com.myfitnessbag.order.core.domain.model.UserModel
import com.myfitnessbag.order.core.domain.source.local.AuthLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class AuthDataStore(
    private val persistenceClient: PersistenceClient,
) : AuthLocalDataSource {
    companion object {
        private const val USER_KEY = "userKey"
    }

    override suspend fun saveUser(
        user: UserModel,
    ) = persistenceClient.set(USER_KEY, Json.encodeToString(user))


    override fun getUser(
    ): Flow<UserModel?> {
        return persistenceClient.read(USER_KEY)
            .map { user ->
                if (user == null) {
                    null
                } else {
                    Json.decodeFromString(user)
                }
            }
    }

}