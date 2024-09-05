package com.myfitnessbag.order.core.data.source.remote.dto.mapper


import com.myfitnessbag.order.core.data.source.remote.dto.ProductListingDto
import com.myfitnessbag.order.core.domain.model.ProductListingModel

fun ProductListingDto.toProductModel(): ProductListingModel {
    return ProductListingModel(
        id = this.id,
        name = this.name,
        price = this.price,
        regularPrice = this.regularPrice,
        salePrice = this.salePrice,
        isOnSale = this.onSale,
        stockQuantity = this.stockQuantity,
        images = this.images.map { it.toImageModel() },
    )
}

