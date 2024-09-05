package com.myfitnessbag.order.core.domain.usecase.auth

import com.myfitnessbag.order.core.domain.model.RegisterRequest
import com.myfitnessbag.order.core.domain.repository.AuthRepository


class RegisterUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(
        request: RegisterRequest,
    ) = authRepository.register(request)
}