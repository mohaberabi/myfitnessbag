package com.myfitnessbag.order.core.domain.usecase.cart

import com.myfitnessbag.order.core.domain.model.CartItemModel
import com.myfitnessbag.order.core.domain.repository.CartRepository


class AddToCartUseCase(
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke(
        uid: Int,
        item: CartItemModel
    ) = cartRepository.addToCart(
        uid = uid,
        item = item
    )
}