package com.myfitnessbag.order.core.data.source.remote.dto.mapper

import com.myfitnessbag.order.core.data.source.remote.dto.RegisterRequestDto
import com.myfitnessbag.order.core.data.source.remote.dto.RegisterResponseDto
import com.myfitnessbag.order.core.domain.model.RegisterRequest
import com.myfitnessbag.order.core.domain.model.RegisterResponse


fun RegisterRequest.toRegisterRequestDto(
) = RegisterRequestDto(
    username = username,
    password = password,
    lastName = lastName,
    firstName = firstName,
    email = email,
)

fun RegisterResponseDto.toRegisterResponse(
) = RegisterResponse(
    id = id,
    email = email,
    lastName = lastName,
    firstName = firstName,
    username = username
)