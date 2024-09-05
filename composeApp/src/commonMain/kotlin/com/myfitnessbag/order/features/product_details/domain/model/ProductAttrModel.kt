package com.myfitnessbag.order.features.product_details.domain.model


data class ProductAttrModel(
    val id: Long,
    val name: String,
    val visible: Boolean,
    val variation: Boolean,
    val options: List<String>,
)