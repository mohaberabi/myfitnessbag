package com.myfitnessbag.order.core.domain.usecase.cart

import com.myfitnessbag.order.core.domain.repository.CartRepository

class RemoveCartItemUseCase(
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke(
        uid: Int,
        itemId: String,
    ) = cartRepository.removeCartItem(uid = uid, itemId = itemId)
}