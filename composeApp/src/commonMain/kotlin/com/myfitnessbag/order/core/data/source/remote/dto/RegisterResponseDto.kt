package com.myfitnessbag.order.core.data.source.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
    val id: Int,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val username: String,
)
