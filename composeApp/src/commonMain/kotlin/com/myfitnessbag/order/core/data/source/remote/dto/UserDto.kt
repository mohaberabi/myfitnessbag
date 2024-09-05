package com.myfitnessbag.order.core.data.source.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Int,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val role: String,
    val username: String,
    val billing: UserBillingDto,
    val shipping: UserShippingDto,
)

@Serializable
data class UserBillingDto(
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val company: String,
    @SerialName("address_1")
    val address1: String,
    @SerialName("address_2")
    val address2: String,
    val city: String,
    val state: String,
    val postcode: String,
    val country: String,
    val email: String,
    val phone: String
)

@Serializable
data class UserShippingDto(
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val company: String,
    @SerialName("address_1")
    val address1: String,
    @SerialName("address_2")
    val address2: String,
    val city: String,
    val state: String,
    val postcode: String,
    val country: String
)

