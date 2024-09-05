package com.myfitnessbag.order.features.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfitnessbag.order.core.util.asUiText
import com.myfitnessbag.order.core.util.onFailure
import com.myfitnessbag.order.core.util.onSuccess
import com.myfitnessbag.order.core.domain.usecase.auth.AuthUseCases
import com.myfitnessbag.order.core.domain.usecase.validator.ValidateEmailUseCase
import com.myfitnessbag.order.core.domain.usecase.validator.ValidatePasswordUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AuthViewModel(
    private val authUseCases: AuthUseCases,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePassWordUseCase: ValidatePasswordUseCase,
) : ViewModel() {
    private val _event = Channel<AuthEvent>()
    val event = _event.receiveAsFlow()
    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()
    fun onAction(action: AuthActions) {
        when (action) {
            is AuthActions.OnEmailChanged -> emailChanged(action.email)
            is AuthActions.OnLastNameChanged -> lastnameChanged(action.lastname)
            AuthActions.OnLoginClick -> {
                if (_state.value.isLogin) {
                    login()
                } else {
                    register()
                }
            }

            is AuthActions.OnNameChanged -> nameChanged(action.name)
            is AuthActions.OnPasswordChanged -> passwordChanged(action.password)
            AuthActions.OnToggleAuthWay -> toggleAuthWay()
            else -> Unit
        }
    }


    private fun login() {
        val stateVal = _state.value
        _state.update { it.copy(loading = true) }
        viewModelScope.launch {
            authUseCases.loginUseCase(stateVal.loginRequest)
                .onFailure { _event.send(AuthEvent.Error(it.asUiText())) }
                .onSuccess { _event.send(AuthEvent.AuthDone) }
            _state.update { it.copy(loading = false) }

        }
    }

    private fun register() {

        val stateVal = _state.value
        _state.update { it.copy(loading = true) }
        viewModelScope.launch {
            authUseCases.registerUseCase(stateVal.registerRequest)
                .onFailure { _event.send(AuthEvent.Error(it.asUiText())) }
                .onSuccess { _event.send(AuthEvent.AuthDone) }
            _state.update { it.copy(loading = false) }
        }

    }

    private fun toggleAuthWay() = _state.update { it.copy(isLogin = !it.isLogin) }
    private fun emailChanged(email: String) =
        _state.update {
            it.copy(
                email = email.lowercase(),
                emailValid = validateEmailUseCase(email.lowercase())
            )
        }

    private fun passwordChanged(password: String) = _state.update {
        it.copy(
            password = password,
            passwordValid = validatePassWordUseCase(password)
        )
    }

    private fun nameChanged(name: String) = _state.update { it.copy(name = name) }
    private fun lastnameChanged(lastName: String) = _state.update { it.copy(lastName = lastName) }


}