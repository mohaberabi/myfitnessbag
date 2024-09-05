package com.myfitnessbag.order.core.domain.model


data class RegisterRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String
)
