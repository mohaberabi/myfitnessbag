package com.myfitnessbag.order.features.home.presentation.viewmodel

import com.myfitnessbag.order.core.util.UiText
import com.myfitnessbag.order.core.domain.model.CategoryModel


data class HomeState(
    val categories: List<CategoryModel> = listOf(),
    val error: UiText = UiText.Empty,
    val page: Int = 1,
    val loading: Boolean = false,

    ) {
    fun refresh(cats: List<CategoryModel>) = copy(
        categories = cats, page = 1,
        loading = false,
    )

    fun paginate(cats: List<CategoryModel>) =
        copy(
            categories = categories + cats,
            page = 1 + this.page,
            loading = false,
        )

}