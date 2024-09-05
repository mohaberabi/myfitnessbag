package com.myfitnessbag.order.core.util.error


interface AppError


enum class LocalError : AppError {
    DISK_FULL,
    UNKNOWN,
    Corrupted,

    IO
}

enum class CommonError : AppError {
    IO_ERROR,
    UNKNOWN,
}


enum class RemoteError : AppError {
    REQUEST_TIMEOUT,
    UNAUTHORIZED,
    WRONG_EMAIL_USERNAME,
    WRONG_PASSWORD,

    CONFLICT,
    TOO_MANY_REQUEST,
    NO_NETWORK,
    PAYLOAD_TOO_LARGE,
    SERVER_ERROR,
    SERIALIZATION_ERROR,
    UNKNOWN_ERROR
}