package com.myfitnessbag.order.core.data.source.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias CategoryDtoResponse = List<CategoryDto>

@Serializable
data class CategoryDto(
    val id: Int,
    val name: String,
    val display: String,
    val image: CategoryImageDto?,
    val parent: Int,
    @SerialName("menu_order")
    val menuOrder: Int,
)

@Serializable
data class CategoryImageDto(
    val src: String,
)
