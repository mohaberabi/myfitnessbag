package com.myfitnessbag.order.core.domain.usecase.cart

data class CartUseCases(
    val addToCartUseCase: AddToCartUseCase,
    val updateCartItemUseCase: UpdateCartItemUseCase,
    val removeCartItemUseCase: RemoveCartItemUseCase,
    val getAllCartItemsUseCase: GetAllCartItemsUseCase,
    val getCartItemUseCase: GetCartItemUseCase,
    val getCartCountUseCase: GetCartCountUseCase,
    val getCartTotalUseCase: GetCartTotalUseCase
)
