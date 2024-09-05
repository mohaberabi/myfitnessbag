package com.myfitnessbag.order.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfitnessbag.order.core.util.asUiText
import com.myfitnessbag.order.core.util.onFailure
import com.myfitnessbag.order.core.util.onSuccess
import com.myfitnessbag.order.core.domain.usecase.category.GetCategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getCategories()
    }

    fun onAction(action: HomeActions) {
        when (action) {
            HomeActions.LoadMore -> getCategories(reset = false)
            HomeActions.Refresh -> getCategories()
        }
    }

    private fun getCategories(
        reset: Boolean = true,
    ) {
        if (reset) {
            _state.update {
                it.copy(
                    loading = reset,
                )
            }
        }
        viewModelScope.launch {
            getCategoriesUseCase(page = _state.value.page)
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            error = error.type.asUiText()
                        )
                    }
                }
                .onSuccess { cats ->
                    _state.update {
                        if (reset) {
                            it.refresh(cats)
                        } else {
                            it.paginate(cats)
                        }

                    }
                }
        }
    }
}