package com.myfitnessbag.order.core.domain.usecase.cart

import com.myfitnessbag.order.core.domain.repository.CartRepository

class GetCartTotalUseCase(
    private val cartRepository: CartRepository,
) {

    operator fun invoke() = cartRepository.getCartTotal()

}