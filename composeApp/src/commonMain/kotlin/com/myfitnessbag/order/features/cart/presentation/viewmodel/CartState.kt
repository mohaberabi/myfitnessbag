package com.myfitnessbag.order.features.cart.presentation.viewmodel

import com.myfitnessbag.order.core.domain.model.CartItemModel
import com.myfitnessbag.order.core.domain.model.cartTotal

data class CartState(
    val items: List<CartItemModel> = listOf(),
) {
    val cartTotal: Double
        get() = items.cartTotal()
}
