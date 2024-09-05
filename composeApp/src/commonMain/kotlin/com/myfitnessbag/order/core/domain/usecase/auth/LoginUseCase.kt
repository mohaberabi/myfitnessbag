package com.myfitnessbag.order.core.domain.usecase.auth

import com.myfitnessbag.order.core.domain.model.LoginRequest
import com.myfitnessbag.order.core.domain.repository.AuthRepository


class LoginUseCase(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(
        request: LoginRequest,
    ) = authRepository.login(request)
}