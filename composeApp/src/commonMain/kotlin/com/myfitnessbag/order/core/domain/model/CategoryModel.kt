package com.myfitnessbag.order.core.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class CategoryModel(
    val id: Int,
    val name: String,
    val display: String,
    val image: String?,
    val parent: Int,
    val menuOrder: Int,
)



