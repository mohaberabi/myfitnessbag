package com.myfitnessbag.order.core.data.source.local

import com.myfitnessbag.order.core.data.source.local.database.dao.CartItemDao
import com.myfitnessbag.order.core.data.source.local.database.mappers.toCartItem
import com.myfitnessbag.order.core.data.source.local.database.mappers.toCartItemEntity
import com.myfitnessbag.order.core.domain.model.CartItemModel
import com.myfitnessbag.order.core.domain.source.local.CartLocalDataSource
import com.myfitnessbag.order.core.util.DispatchersProvider
import com.myfitnessbag.order.core.util.error.handleLocalOperation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RoomCartLocalDaaSource(
    private val cartItemDao: CartItemDao,
    private val dispatchers: DispatchersProvider,
) : CartLocalDataSource {
    override fun getAllCartItems(
    ): Flow<List<CartItemModel>> =
        cartItemDao.getAllCartItems().map { list ->
            list.map { it.toCartItem() }
        }.flowOn(dispatchers.io)

    override fun getCartItem(
        itemId: String,
    ): Flow<CartItemModel?> = cartItemDao.getCartItem(itemId).map {
        it?.toCartItem()
    }.flowOn(dispatchers.io)

    override suspend fun addToCart(
        item: CartItemModel,
    ) = withContext(dispatchers.io) {
        handleLocalOperation {
            cartItemDao.addToCart(item.toCartItemEntity())
        }
    }

    override suspend fun removeCartItem(
        itemId: String,
    ) = withContext(dispatchers.io) {
        handleLocalOperation { cartItemDao.removeCartItem(itemId) }
    }

    override suspend fun updateCartItemQty(
        itemId: String,
        decrement: Boolean
    ) = withContext(dispatchers.io) {
        handleLocalOperation {
            cartItemDao.updateCartItemQty(itemId, decrement)
        }
    }

    override fun getCartCount(): Flow<Int> =
        cartItemDao.getCartItemCount().map { count ->
            count ?: 0
        }.flowOn(dispatchers.io)

    override fun getCartTotal(): Flow<Double> =
        cartItemDao.getTotalAmount()
            .map { amount ->
                amount ?: 0.0
            }.flowOn(dispatchers.io)


}