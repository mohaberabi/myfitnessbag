package com.myfitnessbag.order.core.domain.usecase.auth


data class AuthUseCases(
    val loginUseCase: LoginUseCase,
    val registerUseCase: RegisterUseCase,
    val getUserUseCase: GetUserUseCase,
    val checkAuthUserCase: CheckAuthUseCase
)