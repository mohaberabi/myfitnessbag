package com.myfitnessbag.order.core.data.source.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("cartItem")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val qty: Int,
    val price: Double,
    val variants: String,
)