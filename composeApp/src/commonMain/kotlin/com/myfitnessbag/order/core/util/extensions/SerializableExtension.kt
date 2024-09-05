package com.myfitnessbag.order.core.util.extensions

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer


inline fun <reified T> String.decodeAsList(): List<T> {
    return Json.decodeFromString<List<T>>(serializer(), this)
}

inline fun <reified T> String?.decode(): T? {
    return this?.let {
        Json.decodeFromString<T>(serializer(), it)
    }
}

inline fun <reified T> T.encode(): String {
    return Json.encodeToString(serializer(), this)
}

inline fun <reified T> List<T>.encode(): String {
    return Json.encodeToString(serializer(), this)
}