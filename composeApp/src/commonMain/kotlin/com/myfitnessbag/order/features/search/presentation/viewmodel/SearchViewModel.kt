package com.myfitnessbag.order.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfitnessbag.order.core.domain.usecase.product.SearchProductUseCase
import com.myfitnessbag.order.core.util.asUiText
import com.myfitnessbag.order.core.util.onFailure
import com.myfitnessbag.order.core.util.onSuccess
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchProductUseCase: SearchProductUseCase,
) : ViewModel() {


    private val _state = MutableStateFlow(SearchProductState())
    val state = _state.asStateFlow()
    private var searchJob: Job? = null

    fun onAction(action: SearchProductActions) {
        when (action) {
            is SearchProductActions.QueryChanged -> searchProducts(query = action.query)
        }
    }


    private fun searchProducts(
        query: String,
    ) {

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _state.update { it.copy(state = SearchProductStatus.Loading, query = query) }

            searchProductUseCase(query)
                .onFailure { fail ->
                    _state.update {
                        it.copy(
                            state = SearchProductStatus.Error,
                            error = fail.asUiText()
                        )
                    }
                }
                .onSuccess { prods ->
                    _state.update {
                        it.copy(
                            state = SearchProductStatus.Populated,
                            items = prods
                        )
                    }
                }
        }
    }


}