package com.myfitnessbag.order.core.data.repository

import com.myfitnessbag.order.core.domain.model.CartItemModel
import com.myfitnessbag.order.core.domain.repository.CartRepository
import com.myfitnessbag.order.core.domain.source.local.CartLocalDataSource
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.EmptyDataResult
import com.myfitnessbag.order.core.util.error.ErrorModel
import kotlinx.coroutines.flow.Flow

class DefaultCartRepository(
    private val cartRemoteDataSource: CartLocalDataSource,
) : CartRepository {
    override fun getAllCartItems(
        uid: Int,
    ): Flow<List<CartItemModel>> = cartRemoteDataSource.getAllCartItems()

    override fun getCartItem(
        uid: Int, itemId: String,
    ): Flow<CartItemModel?> =
        cartRemoteDataSource.getCartItem(itemId = itemId)

    override suspend fun addToCart(
        uid: Int, item: CartItemModel,
    ): EmptyDataResult<ErrorModel> {
        return AppResult.handle {
            cartRemoteDataSource.addToCart(item = item)
        }
    }

    override suspend fun removeCartItem(
        uid: Int, itemId: String,
    ): EmptyDataResult<ErrorModel> {
        return AppResult.handle {
            cartRemoteDataSource.removeCartItem(itemId = itemId)
        }
    }

    override suspend fun updateCartItemQty(
        itemId: String,
        uid: Int,
        decrement: Boolean
    ): EmptyDataResult<ErrorModel> {
        return AppResult.handle {
            cartRemoteDataSource.updateCartItemQty(
                itemId = itemId,
                decrement = decrement
            )
        }
    }

    override fun getCartCount(
    ): Flow<Int> = cartRemoteDataSource.getCartCount()

    override fun getCartTotal(
    ): Flow<Double> = cartRemoteDataSource.getCartTotal()
}