package com.myfitnessbag.order.features.account.presentation.info.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfitnessbag.order.core.domain.usecase.auth.GetUserUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class AccountInfoViewModel(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {


    private val _state = MutableStateFlow(AccountInfoState())

    val state = _state.asStateFlow()


    private val _event = Channel<AccountInfoEvents>()

    val event = _event.receiveAsFlow()


    init {
        getUserUseCase().onEach { user ->
            _state.update { it.copy(user = user) }
        }.launchIn(viewModelScope)
    }


    fun onAction(action: AccountInfoActions) {
        when (action) {
            is AccountInfoActions.LastNameChanged -> lastNameChanged(action.name)
            is AccountInfoActions.NameChanged -> nameChanged(action.name)
        }
    }

    private fun lastNameChanged(name: String) =
        _state.update { it.copy(user = it.user?.copy(lastName = name)) }

    private fun nameChanged(name: String) =
        _state.update { it.copy(user = it.user?.copy(firstName = name)) }
}