package com.myfitnessbag.order.features.listing.presentation.viewmodel

import com.myfitnessbag.order.core.domain.model.CategoryModel

sealed interface ListingActions {


    data class DataChanged(val index: Int, val categories: List<CategoryModel>) : ListingActions
    data object PaginateItems : ListingActions
    data class CategoryChanged(val index: Int) : ListingActions
}