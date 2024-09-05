package com.myfitnessbag.order.features.listing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfitnessbag.order.core.domain.model.CategoryModel
import com.myfitnessbag.order.core.domain.usecase.cart.CartUseCases
import com.myfitnessbag.order.core.domain.usecase.product.GetProductsUseCase
import com.myfitnessbag.order.core.util.asUiText
import com.myfitnessbag.order.core.util.onFailure
import com.myfitnessbag.order.core.util.onSuccess
import com.myfitnessbag.order.core.domain.usecase.category.GetCategoriesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ListingViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val cartUseCases: CartUseCases,
) : ViewModel() {


    private val _state = MutableStateFlow(ListingState())
    val state = _state.asStateFlow()

    init {
        cartUseCases.getCartTotalUseCase()
            .onEach { total ->
                _state.update { it.copy(cartTotal = total) }
            }.launchIn(viewModelScope)
    }

    private var itemsJob: Job? = null
    fun onAction(action: ListingActions) {
        when (action) {
            is ListingActions.CategoryChanged -> categoryChanged(index = action.index)

            ListingActions.PaginateItems -> getItems(reset = false)
            is ListingActions.DataChanged -> categoryChanged(
                categories = action.categories,
                index = action.index
            )
        }
    }


    private fun categoryChanged(
        categories: List<CategoryModel> = _state.value.categories,
        index: Int = 0,
    ) {
        setState {
            copy(
                categories = categories,
                categoryIndex = index
            )
        }
        getItems()

    }


    private fun getItems(
        reset: Boolean = true,
    ) {
        val stateVal = _state.value
        itemsJob?.cancel()
        itemsJob = viewModelScope.launch {
            if (reset) {
                setState {
                    copy(
                        itemsPage = 1,
                        items = emptyList(),
                        loading = true,
                    )
                }
            }
            val index = stateVal.categoryIndex
            val id = stateVal.categories[index].id.toString()
            getProductsUseCase(
                categoryId = id,
                page = stateVal.itemsPage,
            ).onFailure { fail ->
                setState { copy(error = fail.asUiText()) }
            }.onSuccess { newItems ->
                setState {
                    paginateItems(newItems)
                }
            }
        }

    }

    private fun setState(
        block: ListingState.() -> ListingState,
    ) = _state.update { block(it) }


}