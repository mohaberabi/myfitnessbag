package com.myfitnessbag.order.core.domain.usecase.cart

import com.myfitnessbag.order.core.domain.model.CartItemModel
import com.myfitnessbag.order.core.domain.repository.CartRepository

class GetCartItemUseCase(
    private val cartRepository: CartRepository,
) {
    operator fun invoke(
        uid: Int,
        itemId: String
    ) = cartRepository.getCartItem(uid = uid, itemId = itemId)
}