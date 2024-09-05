package com.myfitnessbag.order.features.cart.presentation.viewmodel


sealed interface CartActions {


    data class UpdateQty(
        val id: String,
        val decrement: Boolean
    ) : CartActions


}