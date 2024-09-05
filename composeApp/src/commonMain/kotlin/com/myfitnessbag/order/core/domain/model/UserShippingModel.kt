package com.myfitnessbag.order.core.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class UserShippingModel(
    val firstName: String,
    val lastName: String,
    val company: String,
    val address1: String,
    val address2: String,
    val city: String,
    val state: String,
    val postcode: String,
    val country: String
)
