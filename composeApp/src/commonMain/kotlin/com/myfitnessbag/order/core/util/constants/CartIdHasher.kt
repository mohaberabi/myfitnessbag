package com.myfitnessbag.order.core.util.constants

object CartIdHasher {


    fun hashKey(
        id: String, variants: List<String>,
    ) = buildString {
        append(id)
        if (variants.isNotEmpty()) {
            variants.forEach {
                append("-${it}")
            }
        }
    }


}