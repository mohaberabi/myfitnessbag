package com.myfitnessbag.order.core.domain.source.local

import com.myfitnessbag.order.core.domain.model.CartItemModel
import kotlinx.coroutines.flow.Flow


interface CartLocalDataSource {


    fun getAllCartItems(
    ): Flow<List<CartItemModel>>

    fun getCartItem(
        itemId: String,
    ): Flow<CartItemModel?>

    suspend fun addToCart(
        item: CartItemModel,
    )

    suspend fun removeCartItem(
        itemId: String,
    )

    suspend fun updateCartItemQty(
        itemId: String,
        decrement: Boolean
    )

    fun getCartCount(): Flow<Int>
    fun getCartTotal(): Flow<Double>
}