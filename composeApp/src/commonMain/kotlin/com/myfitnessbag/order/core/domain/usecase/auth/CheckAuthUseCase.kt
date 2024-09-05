package com.myfitnessbag.order.core.domain.usecase.auth

import com.myfitnessbag.order.core.domain.repository.AuthRepository

class CheckAuthUseCase(
    private val authRepository: AuthRepository,
) {


    suspend operator fun invoke() = authRepository.isLoggedIn()
}