package com.myfitnessbag.order.core.data.source.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDto(
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val username: String = email,
    val password: String
)
