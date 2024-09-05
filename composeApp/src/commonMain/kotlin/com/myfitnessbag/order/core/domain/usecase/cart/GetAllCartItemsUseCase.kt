package com.myfitnessbag.order.core.domain.usecase.cart

import com.myfitnessbag.order.core.domain.repository.CartRepository

class GetAllCartItemsUseCase(
    private val cartRepository: CartRepository,
) {
    operator fun invoke(
        uid: Int,
    ) = cartRepository.getAllCartItems(uid = uid)
}
