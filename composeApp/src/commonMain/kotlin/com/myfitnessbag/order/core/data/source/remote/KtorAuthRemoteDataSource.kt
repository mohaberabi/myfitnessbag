package com.myfitnessbag.order.core.data.source.remote

import com.myfitnessbag.order.core.data.source.remote.dto.UserDto
import com.myfitnessbag.order.core.util.DispatchersProvider
import com.myfitnessbag.order.core.util.constants.CommonParams
import com.myfitnessbag.order.core.util.constants.EndPoints
import com.myfitnessbag.order.core.data.source.remote.dto.LoginRequestDto
import com.myfitnessbag.order.core.data.source.remote.dto.LoginResponseDto
import com.myfitnessbag.order.core.data.source.remote.dto.RegisterRequestDto
import com.myfitnessbag.order.core.data.source.remote.dto.RegisterResponseDto
import com.myfitnessbag.order.core.data.source.remote.dto.mapper.toLoginRequestDto
import com.myfitnessbag.order.core.data.source.remote.dto.mapper.toLoginResponse
import com.myfitnessbag.order.core.data.source.remote.dto.mapper.toRegisterRequestDto
import com.myfitnessbag.order.core.data.source.remote.dto.mapper.toRegisterResponse
import com.myfitnessbag.order.core.data.source.remote.dto.mapper.toUserModel
import com.myfitnessbag.order.core.domain.model.LoginRequest
import com.myfitnessbag.order.core.domain.model.LoginResponse
import com.myfitnessbag.order.core.domain.model.RegisterRequest
import com.myfitnessbag.order.core.domain.model.RegisterResponse
import com.myfitnessbag.order.core.domain.model.UserModel
import com.myfitnessbag.order.core.domain.source.remote.AuthRemoteDataSource
import io.ktor.client.HttpClient
import kotlinx.coroutines.withContext

class KtorAuthRemoteDataSource(
    private val httpClient: HttpClient,
    private val dispatcher: DispatchersProvider,
) : AuthRemoteDataSource {
    override suspend fun register(
        request: RegisterRequest,
    ): RegisterResponse {
        return withContext(dispatcher.io) {
            httpClient.post<RegisterRequestDto, RegisterResponseDto>(
                route = EndPoints.REGISTER,
                body = request.toRegisterRequestDto(),
            ).toRegisterResponse()

        }
    }

    override suspend fun getUserData(
        id: Int,
    ): UserModel {
        return withContext(dispatcher.io) {
            httpClient.get<UserDto>(
                route = "${EndPoints.REGISTER}/${id}",
                queryParams = mapOf(CommonParams.ID to id)
            ).toUserModel()

        }
    }

    override suspend fun login(
        request: LoginRequest,
    ): LoginResponse {
        return withContext(dispatcher.io) {
            httpClient.post<LoginRequestDto, LoginResponseDto>(
                route = EndPoints.LOG_IN,
                body = request.toLoginRequestDto(),
            ).toLoginResponse()

        }
    }
}