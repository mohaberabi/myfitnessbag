package com.myfitnessbag.order.core.domain.source.remote

import com.myfitnessbag.order.core.domain.model.LoginRequest
import com.myfitnessbag.order.core.domain.model.LoginResponse
import com.myfitnessbag.order.core.domain.model.RegisterRequest
import com.myfitnessbag.order.core.domain.model.RegisterResponse
import com.myfitnessbag.order.core.domain.model.UserModel

interface AuthRemoteDataSource {
    suspend fun register(
        request: RegisterRequest
    ): RegisterResponse

    suspend fun login(
        request: LoginRequest
    ): LoginResponse

    suspend fun getUserData(
        id: Int,
    ): UserModel
}