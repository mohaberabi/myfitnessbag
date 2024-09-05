package com.myfitnessbag.order.features.search.presentation.viewmodel

import com.myfitnessbag.order.core.domain.model.ProductListingModel
import com.myfitnessbag.order.core.util.UiText


enum class SearchProductStatus {
    Initial,
    Loading,
    Error,
    Populated,
}

data class SearchProductState(
    val query: String = "",
    val items: List<ProductListingModel> = listOf(),
    val error: UiText = UiText.Empty,
    val state: SearchProductStatus = SearchProductStatus.Initial,
)