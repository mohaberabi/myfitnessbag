package com.myfitnessbag.order.core.domain.usecase.auth

import com.myfitnessbag.order.core.domain.repository.AuthRepository

class GetUserUseCase(
    private val authRepository: AuthRepository,
) {


    operator fun invoke() = authRepository.getUser()
}