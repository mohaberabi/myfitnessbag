package com.myfitnessbag.order.features.product_details.data.source.remote.dto

import com.myfitnessbag.order.core.data.source.remote.dto.ImageDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProductDetailDto(
    val id: Int,
    val name: String,
    val featured: Boolean,
    val description: String,
    @SerialName("short_description")
    val shortDescription: String,
    val price: String,
    @SerialName("regular_price")
    val regularPrice: String,
    @SerialName("sale_price")
    val salePrice: String,
    @SerialName("on_sale")
    val onSale: Boolean,
    @SerialName("manage_stock")
    val manageStock: Boolean,
    @SerialName("stock_quantity")
    val stockQuantity: Int?,
    @SerialName("low_stock_amount")
    val lowStockAmount: Int?,
    val images: List<ImageDto>,
    val attributes: List<ProductAttrDto>,
    val variations: List<Long>,
    @SerialName("related_ids")
    val relatedIds: List<Int>,
    @SerialName("stock_status")
    val stockStatus: String,
)



