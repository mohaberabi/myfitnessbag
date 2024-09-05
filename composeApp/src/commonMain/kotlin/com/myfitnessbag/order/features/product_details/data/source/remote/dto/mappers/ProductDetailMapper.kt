package com.myfitnessbag.order.features.product_details.data.source.remote.dto.mappers

import com.myfitnessbag.order.core.data.source.remote.dto.mapper.toImageModel
import com.myfitnessbag.order.core.data.source.remote.dto.mapper.toProductModel
import com.myfitnessbag.order.core.domain.model.ProductListingModel
import com.myfitnessbag.order.features.product_details.data.source.remote.dto.ProductDetailDto
import com.myfitnessbag.order.features.product_details.domain.model.ProductDetailModel


fun ProductDetailDto.toProductDetailModel(): ProductDetailModel {
    val listing = ProductListingModel(
        id = this.id,
        name = this.name,
        price = this.price,
        regularPrice = this.regularPrice,
        salePrice = this.salePrice,
        isOnSale = this.onSale,
        stockQuantity = this.stockQuantity,
        images = this.images.map { it.toImageModel() },
    )
    return ProductDetailModel(
        product = listing,
        attributes = attributes.map { it.toProductAttr() },
        images = images.map { it.toImageModel() },
        description = description.removeMarkup(),
        relatedIds = relatedIds,
        shortDescription = shortDescription,
    )
}

private fun String.removeMarkup(
) = replace(Regex("<[^>]*>"), "").replace("&nbsp;", " ").replace("&amp;", "&")