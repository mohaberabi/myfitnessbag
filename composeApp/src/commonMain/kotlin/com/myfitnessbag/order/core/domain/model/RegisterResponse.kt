package com.myfitnessbag.order.core.domain.model


data class RegisterResponse(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val username: String,
)