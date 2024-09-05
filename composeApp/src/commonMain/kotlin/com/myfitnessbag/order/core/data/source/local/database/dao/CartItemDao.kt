package com.myfitnessbag.order.core.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.myfitnessbag.order.core.data.source.local.database.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CartItemDao {


    @Query("SELECT * FROM cartItem")
    fun getAllCartItems(): Flow<List<CartItemEntity>>

    @Query("SELECT * FROM cartItem  WHERE id = :itemId LIMIT 1")
    fun getCartItem(
        itemId: String,
    ): Flow<CartItemEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(item: CartItemEntity)

    @Query("DELETE FROM cartItem WHERE  id = :itemId")
    suspend fun removeCartItem(itemId: String)

    @Transaction
    suspend fun updateCartItemQty(
        itemId: String,
        decrement: Boolean
    ) {
        val cartItem = getCartItemForUpdate(itemId) ?: return
        val updatedQuantity = if (decrement) cartItem.qty - 1 else cartItem.qty + 1

        if (updatedQuantity <= 0) {
            removeCartItem(itemId)
        } else {
            val updatedItem = cartItem.copy(qty = updatedQuantity)
            addToCart(updatedItem)
        }
    }

    @Query("SELECT * FROM cartItem WHERE id = :itemId LIMIT 1")
    suspend fun getCartItemForUpdate(itemId: String): CartItemEntity?

    @Query("SELECT SUM(price * qty) FROM cartItem ")
    fun getTotalAmount(): Flow<Double?>

    @Query("SELECT COUNT(*) FROM cartItem")
    fun getCartItemCount(): Flow<Int?>
}