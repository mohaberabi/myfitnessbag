package com.myfitnessbag.order.features.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfitnessbag.order.core.domain.model.AppLocales
import com.myfitnessbag.order.core.domain.model.toAppLocal
import com.myfitnessbag.order.core.domain.usecase.auth.GetUserUseCase
import com.myfitnessbag.order.core.domain.usecase.localization.LocalizationUseCases
import com.myfitnessbag.order.core.util.asUiText
import com.myfitnessbag.order.core.util.onFailure
import com.myfitnessbag.order.core.util.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val localizationUseCases: LocalizationUseCases,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {


    private val _event = Channel<ProfileEvents>()

    val event = _event.receiveAsFlow()


    private val _state = MutableStateFlow(
        ProfileState(
        )
    )

    init {
        getUserUseCase().onEach { user ->
            _state.update { it.copy(user = user) }
        }.launchIn(viewModelScope)
    }

    val state = _state.asStateFlow()
    fun onAction(action: ProfileActions) {
        when (action) {
            ProfileActions.ChangeLocal -> changeLocal()
        }
    }


    private fun changeLocal() {
        viewModelScope.launch {
            localizationUseCases.changeLocalUseCase()
                .onFailure { error ->
                    _event.send(ProfileEvents.Error(error.asUiText()))
                }
        }

    }
}