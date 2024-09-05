package com.myfitnessbag.order.core.data.source.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductListingDto(
    val id: Int,
    val name: String,
    val price: String,
    @SerialName("regular_price")
    val regularPrice: String,
    @SerialName("sale_price")
    val salePrice: String? = null,
    @SerialName("on_sale")
    val onSale: Boolean,
    @SerialName("stock_quantity")
    val stockQuantity: Int? = null,
    val images: List<ImageDto>,
)

