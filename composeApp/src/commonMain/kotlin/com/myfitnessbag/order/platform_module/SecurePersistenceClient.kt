package com.myfitnessbag.order.platform_module

import com.myfitnessbag.order.core.domain.source.local.PersistenceClient


expect class SecurePersistenceClient : PersistenceClient

sealed class SecurePersistenceException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message) {
    class WriteFailureException(
        message: String? = null,
        cause: Throwable? = null
    ) : SecurePersistenceException(
        message = message ?: "Failed to write to local storage",
        cause = cause
    )

    class ReadFailureException(
        message: String? = null,
        cause: Throwable? = null
    ) : SecurePersistenceException(
        message = message ?: "Failed to read data from local storage",
        cause = cause
    )

    class DeleteFailureException(
        message: String? = null,
        cause: Throwable? = null
    ) : SecurePersistenceException(
        message = message ?: "Failed to delete data from local storage",
        cause = cause
    )

    class ClearFailureException(
        message: String? = null,
        cause: Throwable? = null
    ) : SecurePersistenceException(
        message = message ?: "Failed to clear local storage",
        cause = cause
    )

}
