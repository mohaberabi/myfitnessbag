package com.myfitnessbag.order.features.product_details.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfitnessbag.order.core.domain.model.CartItemModel
import com.myfitnessbag.order.core.domain.usecase.cart.CartUseCases
import com.myfitnessbag.order.core.util.asUiText
import com.myfitnessbag.order.core.util.onFailure
import com.myfitnessbag.order.core.util.onSuccess
import com.myfitnessbag.order.features.product_details.domain.usecase.GetProductDetailUseCase
import com.myfitnessbag.order.features.product_details.domain.usecase.GetRelatedProductsUSeCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val getRelatedProductsUSeCase: GetRelatedProductsUSeCase,
    private val savedStateHandle: SavedStateHandle,
    private val cartUseCases: CartUseCases
) : ViewModel() {


    companion object {
        private const val ITEM_ID_KEY = "itemIdKey"
    }

    private val _state = MutableStateFlow(ProductDetailState())

    val state = _state.asStateFlow()

    private val _event = Channel<ProductDetailEvent>()
    val event = _event.receiveAsFlow()


    private var updateCartJob: Job? = null


    init {
        savedStateHandle
            .getStateFlow<String?>(ITEM_ID_KEY, null)
            .flatMapLatest { cartItemId ->
                if (cartItemId == null) {
                    flowOf()
                } else {
                    cartUseCases.getCartItemUseCase(
                        uid = -1,
                        itemId = cartItemId
                    )
                }
            }.onEach { foundCartItem ->
                _state.update { it.copy(foundCartItem = foundCartItem) }
            }.launchIn(viewModelScope)


    }

    fun onAction(action: ProductDetailActions) {
        when (action) {
            is ProductDetailActions.GetData -> getData(action.id)


            ProductDetailActions.AddToCart -> addToCart()
            is ProductDetailActions.UpdateCartItem -> updateCartItem(action.decrement)
            else -> Unit
        }
    }

//    private fun saveVariant(value: String) {
//        val stateVal = _state.value
//        val newMap = stateVal.selectedVariants.toMutableMap()
//        newMap[stateVal.scopedVariant!!.id.toInt()] = value
//        _state.update { it.copy(selectedVariants = newMap, scopedVariant = null) }
//
//    }

    private fun getData(id: Int) {
        _state.update { it.copy(state = ProductDetailStatus.Loading) }

        viewModelScope.launch {
            getProductDetailUseCase(id)
                .onFailure { fail ->
                    _state.update {
                        it.copy(
                            error = fail.asUiText(),
                            state = ProductDetailStatus.Error
                        )
                    }
                }
                .onSuccess { product ->
                    _state.update {
                        it.copy(
                            state = ProductDetailStatus.Populated,
                            product = product
                        )
                    }
                    savedStateHandle[ITEM_ID_KEY] = product.product.id.toString()
                    getRelated(product.relatedIds)
                }
        }
    }

    private fun getRelated(ids: List<Int>) {
        viewModelScope.launch {
            getRelatedProductsUSeCase(ids)
                .onSuccess { related -> _state.update { it.copy(related = related) } }

        }
    }

    private fun addToCart() {
        val stateVal = _state.value
        _state.update { it.copy(loadingCart = true) }
        viewModelScope.launch {
            val res = cartUseCases.addToCartUseCase(
                uid = -1,
                item = stateVal.newCartItem
            )
            res.onFailure { fail ->
                _event.send(ProductDetailEvent.ErrorAddToCart(fail.asUiText()))
            }
            _state.update { it.copy(loadingCart = false) }

        }
    }

    private fun updateCartItem(decrement: Boolean) {

        updateCartJob?.cancel()
        updateCartJob = viewModelScope.launch {
            val stateVal = _state.value
            _state.update { it.copy(loadingCart = true) }
            val res = cartUseCases.updateCartItemUseCase(
                uid = -1,
                itemId = stateVal.product!!.product.id.toString(),
                decrement = decrement
            )
            res.onFailure { fail ->
                _event.send(ProductDetailEvent.ErrorUpdateCart(fail.asUiText()))
            }

            _state.update { it.copy(loadingCart = false) }
        }
    }
}