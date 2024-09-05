package com.myfitnessbag.order.features.product_details.data.source.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class ProductAttrDto(
    val id: Long,
    val name: String,
    val visible: Boolean,
    val variation: Boolean,
    val options: List<String>,
)

