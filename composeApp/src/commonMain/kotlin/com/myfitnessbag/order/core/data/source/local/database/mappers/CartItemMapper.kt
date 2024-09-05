package com.myfitnessbag.order.core.data.source.local.database.mappers

import com.myfitnessbag.order.core.data.source.local.database.entity.CartItemEntity
import com.myfitnessbag.order.core.domain.model.CartItemModel


fun CartItemEntity.toCartItem() = CartItemModel(
    id = id,
    name = name,
    qty = qty,
    price = price,
    variants = variants.split(",")
)


fun CartItemModel.toCartItemEntity() = CartItemEntity(
    id = id,
    name = name,
    qty = qty,
    price = price,
    variants = variants.joinToString { "," }
)