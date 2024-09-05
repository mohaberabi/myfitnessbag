package com.myfitnessbag.order.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfitnessbag.order.core.domain.usecase.auth.CheckAuthUseCase
import com.myfitnessbag.order.core.domain.usecase.localization.LocalizationUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MainAppViewModel(
    useCases: LocalizationUseCases,
    private val checkAuthUserCase: CheckAuthUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(
        MainAppState(
            local = useCases.getLocalUseCase()
        )
    )
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val loggedIn = checkAuthUserCase()
            _state.update {
                it.copy(
                    loadedData = true,
                    didLogIn = loggedIn
                )
            }
        }
    }


}