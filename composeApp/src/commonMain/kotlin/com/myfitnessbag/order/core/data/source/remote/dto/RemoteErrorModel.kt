package com.myfitnessbag.order.core.data.source.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class RemoteErrorModel(
    val code: String? = null,
    val message: String? = null,
    val data: ErrorData? = null
)

@Serializable
data class ErrorData(
    val status: Int? = null,
)