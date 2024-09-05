package com.myfitnessbag.order.core.data.repository

import com.myfitnessbag.order.core.data.source.remote.dto.JwtPayload
import com.myfitnessbag.order.core.domain.repository.TokenExtractorStrategy
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class JwtTokenExtractorStrategy : TokenExtractorStrategy {
    @OptIn(ExperimentalEncodingApi::class)
    override fun extract(
        token: String,
    ): String? {
        return try {
            val parts = token.split(".")
            if (parts.size == 3) {
                val payload = parts[1]
                val decodedBytes = Base64.decode(payload)
                val decodedPayload = decodedBytes.decodeToString()
                val jwtPayload = Json.decodeFromString<JwtPayload>(decodedPayload)
                jwtPayload.data.user.id
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}