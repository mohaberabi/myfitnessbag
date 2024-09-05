package com.myfitnessbag.order.core.domain.repository

import com.myfitnessbag.order.core.domain.model.CartItemModel
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.EmptyDataResult
import com.myfitnessbag.order.core.util.error.ErrorModel
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllCartItems(
        uid: Int
    ): Flow<List<CartItemModel>>

    fun getCartItem(
        uid: Int,
        itemId: String,
    ): Flow<CartItemModel?>

    suspend fun addToCart(
        uid: Int,
        item: CartItemModel,
    ): EmptyDataResult<ErrorModel>

    suspend fun removeCartItem(
        uid: Int,
        itemId: String,
    ): EmptyDataResult<ErrorModel>

    suspend fun updateCartItemQty(
        itemId: String,
        uid: Int,
        decrement: Boolean
    ): EmptyDataResult<ErrorModel>

    fun getCartTotal(): Flow<Double>
    fun getCartCount(): Flow<Int>
}