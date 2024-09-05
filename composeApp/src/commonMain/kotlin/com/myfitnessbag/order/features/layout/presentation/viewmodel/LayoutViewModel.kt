package com.myfitnessbag.order.features.layout.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfitnessbag.order.core.domain.usecase.cart.CartUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update


class LayoutViewModel(
    private val cartUseCases: CartUseCases,
) : ViewModel() {


    private val _state = MutableStateFlow(LayoutState())
    val state = _state.asStateFlow()

    init {
        cartUseCases.getCartCountUseCase().onEach { count ->
            _state.update { it.copy(cartCount = count) }
        }.launchIn(viewModelScope)
    }
}