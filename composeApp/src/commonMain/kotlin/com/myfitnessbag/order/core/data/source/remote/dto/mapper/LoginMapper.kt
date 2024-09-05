package com.myfitnessbag.order.core.data.source.remote.dto.mapper

import com.myfitnessbag.order.core.data.source.remote.dto.LoginRequestDto
import com.myfitnessbag.order.core.data.source.remote.dto.LoginResponseDto
import com.myfitnessbag.order.core.domain.model.LoginRequest
import com.myfitnessbag.order.core.domain.model.LoginResponse


fun LoginRequest.toLoginRequestDto(
) = LoginRequestDto(
    username = username,
    password = password
)

fun LoginResponseDto.toLoginResponse(
) = LoginResponse(
    token = token,
    userEmail = userEmail,
    userNiceName = userNiceName,
    userDisplayName = userDisplayName
)