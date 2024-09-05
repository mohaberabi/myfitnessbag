package com.myfitnessbag.order.features.listing.presentation.viewmodel

import com.myfitnessbag.order.core.domain.model.CartItemModel
import com.myfitnessbag.order.core.domain.model.ProductListingModel
import com.myfitnessbag.order.core.util.UiText
import com.myfitnessbag.order.core.domain.model.CategoryModel


data class ListingState(
    val categories: List<CategoryModel> = listOf(),
    val items: List<ProductListingModel> = listOf(),
    val error: UiText = UiText.Empty,
    val itemsPage: Int = 1,
    val categoryIndex: Int = 0,
    val loading: Boolean = false,
    val cartTotal: Double = 0.0
) {
    fun paginateItems(
        newItems: List<ProductListingModel>,
    ) = copy(
        itemsPage = 1 + itemsPage,
        items = this.items + newItems,
        loading = false,
    )


}