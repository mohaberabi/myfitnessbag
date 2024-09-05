package com.myfitnessbag.order.core.domain.source.local

import com.myfitnessbag.order.core.domain.model.UserModel
import kotlinx.coroutines.flow.Flow


interface AuthLocalDataSource {
    suspend fun saveUser(
        user: UserModel,
    )

    fun getUser(): Flow<UserModel?>
}