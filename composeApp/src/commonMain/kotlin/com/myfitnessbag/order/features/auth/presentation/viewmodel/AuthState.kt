package com.myfitnessbag.order.features.auth.presentation.viewmodel

import com.myfitnessbag.order.core.domain.model.InputValidator
import com.myfitnessbag.order.core.domain.model.LoginRequest
import com.myfitnessbag.order.core.domain.model.RegisterRequest


data class AuthState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val lastName: String = "",
    val loading: Boolean = false,
    val isLogin: Boolean = true,
    val emailValid: InputValidator = InputValidator(),
    val passwordValid: InputValidator = InputValidator()

) {
    val canLogin: Boolean
        get() {

            return emailValid.status.isValid
                    && passwordValid.status.isValid
        }

    val canRegister: Boolean
        get() {
            return canLogin && name.trim().isNotEmpty() &&
                    lastName.trim().isNotEmpty()
        }
    val buttonEnabled: Boolean
        get() {
            return if (isLogin) canLogin else canRegister
        }

    val loginRequest
        get() = LoginRequest(
            username = email,
            password = password,
        )
    val registerRequest
        get() = RegisterRequest(
            username = email,
            password = password,
            firstName = name,
            lastName = lastName,
            email = email,
        )
}


