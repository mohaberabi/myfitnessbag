package com.myfitnessbag.order.core.domain.model


data class LoginResponse(
    val token: String,
    val userEmail: String,
    val userNiceName: String,
    val userDisplayName: String
)