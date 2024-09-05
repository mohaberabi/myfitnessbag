package com.myfitnessbag.order.core.data.source.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class JwtPayload(
    val iss: String,
    val iat: Long,
    val nbf: Long,
    val exp: Long,
    val data: JwtUserData
)

@Serializable
data class JwtUserData(
    val user: JwtUser
)

@Serializable
data class JwtUser(
    val id: String
)
