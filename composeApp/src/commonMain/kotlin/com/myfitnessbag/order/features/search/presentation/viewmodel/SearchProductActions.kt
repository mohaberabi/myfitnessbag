package com.myfitnessbag.order.features.search.presentation.viewmodel

sealed interface SearchProductActions {


    data class QueryChanged(
        val query: String,
    ) : SearchProductActions


}