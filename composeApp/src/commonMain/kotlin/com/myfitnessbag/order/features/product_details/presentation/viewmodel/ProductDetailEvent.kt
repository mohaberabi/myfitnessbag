package com.myfitnessbag.order.features.product_details.presentation.viewmodel

import com.myfitnessbag.order.core.util.UiText


sealed interface ProductDetailEvent {


    data class ErrorAddToCart(
        val error: UiText,
    ) : ProductDetailEvent

    data class ErrorUpdateCart(
        val error: UiText
    ) : ProductDetailEvent


}