package com.myfitnessbag.order.core.domain.usecase.cart

import com.myfitnessbag.order.core.domain.model.CartItemModel
import com.myfitnessbag.order.core.domain.repository.CartRepository

class UpdateCartItemUseCase(
    private val cartRepository: CartRepository,
) {

    suspend operator fun invoke(
        uid: Int,
        decrement: Boolean,
        itemId: String,
    ) = cartRepository.updateCartItemQty(
        uid = uid,
        decrement = decrement,
        itemId = itemId
    )
}