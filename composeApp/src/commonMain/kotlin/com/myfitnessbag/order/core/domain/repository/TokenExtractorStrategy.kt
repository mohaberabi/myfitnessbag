package com.myfitnessbag.order.core.domain.repository

interface TokenExtractorStrategy {


    fun extract(token: String): String?
}