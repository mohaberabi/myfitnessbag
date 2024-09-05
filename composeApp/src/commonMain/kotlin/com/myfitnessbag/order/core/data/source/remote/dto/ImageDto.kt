package com.myfitnessbag.order.core.data.source.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    val id: Int,
    val src: String,
)