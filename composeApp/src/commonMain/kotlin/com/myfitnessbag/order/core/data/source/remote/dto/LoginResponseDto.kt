package com.myfitnessbag.order.core.data.source.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val token: String,
    @SerialName("user_email")
    val userEmail: String,
    @SerialName("user_nicename")
    val userNiceName: String,
    @SerialName("user_display_name")
    val userDisplayName: String
)
