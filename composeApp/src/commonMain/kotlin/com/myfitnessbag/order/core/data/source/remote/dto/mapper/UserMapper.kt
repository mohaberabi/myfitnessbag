package com.myfitnessbag.order.core.data.source.remote.dto.mapper

import com.myfitnessbag.order.core.data.source.remote.dto.UserBillingDto
import com.myfitnessbag.order.core.data.source.remote.dto.UserDto
import com.myfitnessbag.order.core.data.source.remote.dto.UserShippingDto
import com.myfitnessbag.order.core.domain.model.UserBillingModel
import com.myfitnessbag.order.core.domain.model.UserModel
import com.myfitnessbag.order.core.domain.model.UserShippingModel


fun UserDto.toUserModel() = UserModel(
    email = email,
    lastName = lastName,
    firstName = firstName,
    shipping = shipping.toUserShipping(),
    billing = billing.toUserShipping(),
    username = username,
    role = role,
    id = id
)

fun UserBillingDto.toUserShipping() = UserBillingModel(
    address1 = address1,
    address2 = address2,
    state = state,
    city = city,
    postcode = postcode,
    company = company,
    country = country,
    lastName = lastName,
    firstName = firstName,
    email = email,
    phone = phone,
)

fun UserShippingDto.toUserShipping() = UserShippingModel(
    address1 = address1,
    address2 = address2,
    state = state,
    city = city,
    postcode = postcode,
    company = company,
    country = country,
    lastName = lastName,
    firstName = firstName,
)