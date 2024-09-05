package com.myfitnessbag.order.core.domain.model


data class CartItemModel(
    val id: String,
    val name: String,
    val qty: Int,
    val price: Double,
    val variants: List<String> = listOf()
) {
    val totalPrice: Double
        get() = qty * price
}


fun List<CartItemModel>.cartTotal() = sumOf { it.totalPrice }