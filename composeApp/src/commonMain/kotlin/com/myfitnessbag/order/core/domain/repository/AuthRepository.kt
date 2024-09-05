package com.myfitnessbag.order.core.domain.repository

import com.myfitnessbag.order.core.util.EmptyDataResult
import com.myfitnessbag.order.core.util.error.ErrorModel
import com.myfitnessbag.order.core.domain.model.LoginRequest
import com.myfitnessbag.order.core.domain.model.RegisterRequest
import com.myfitnessbag.order.core.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {


    suspend fun isLoggedIn(): Boolean
    suspend fun login(
        request: LoginRequest,
    ): EmptyDataResult<ErrorModel>

    suspend fun register(
        request: RegisterRequest,
    ): EmptyDataResult<ErrorModel>


    fun getUser(): Flow<UserModel?>
}