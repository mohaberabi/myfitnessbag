package com.myfitnessbag.order.features.product_details.domain.model

import com.myfitnessbag.order.core.domain.model.ImageModel
import com.myfitnessbag.order.core.domain.model.ProductListingModel


data class ProductDetailModel(
    val product: ProductListingModel,
    val images: List<ImageModel>,
    val description: String,
    val relatedIds: List<Int>,
    val attributes: List<ProductAttrModel>,
    val shortDescription: String,
)