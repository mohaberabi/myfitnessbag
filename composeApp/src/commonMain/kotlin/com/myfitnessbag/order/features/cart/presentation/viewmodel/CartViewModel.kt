package com.myfitnessbag.order.features.cart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myfitnessbag.order.core.domain.usecase.cart.CartUseCases
import com.myfitnessbag.order.core.util.asUiText
import com.myfitnessbag.order.core.util.onFailure
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartUseCases: CartUseCases,
) : ViewModel() {


    private val _state = MutableStateFlow(CartState())
    val state = _state.asStateFlow()


    private val _event = Channel<CartEvents>()
    val event = _event.receiveAsFlow()

    init {
        cartUseCases.getAllCartItemsUseCase(-1)
            .onEach { itms ->
                _state.update {
                    it.copy(
                        items = itms
                    )
                }
            }.launchIn(viewModelScope)
    }

    fun onAction(action: CartActions) {
        when (action) {
            is CartActions.UpdateQty -> updateQty(
                id = action.id,
                decrement = action.decrement
            )
        }
    }

    private fun updateQty(
        id: String,
        decrement: Boolean
    ) {
        viewModelScope.launch {
            cartUseCases.updateCartItemUseCase(-1, decrement, id)
                .onFailure { fail -> _event.send(CartEvents.Error(fail.asUiText())) }
        }
    }
}