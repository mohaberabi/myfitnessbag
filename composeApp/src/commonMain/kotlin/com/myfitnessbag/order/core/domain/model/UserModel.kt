package com.myfitnessbag.order.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val role: String,
    val username: String,
    val billing: UserBillingModel,
    val shipping: UserShippingModel,
)



