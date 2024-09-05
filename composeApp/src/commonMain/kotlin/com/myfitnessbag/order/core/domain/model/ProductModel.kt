package com.myfitnessbag.order.core.domain.model


data class ProductListingModel(
    val id: Int,
    val name: String,
    val price: String,
    val regularPrice: String,
    val salePrice: String? = null,
    val isOnSale: Boolean,
    val stockQuantity: Int? = null,
    val images: List<ImageModel>,
)
