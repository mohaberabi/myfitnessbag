package com.myfitnessbag.order.core.util.error

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.IOException
import androidx.sqlite.SQLiteException
import com.myfitnessbag.order.platform_module.AppLogger


data class ErrorModel(
    val message: String? = null,
    val statusCode: Int? = null,
    val cause: Exception? = null,
    val type: AppError,
) : AppError {
    override fun toString(): String {
        return buildString {
            append("App ERROR EXCEPTION ")
            append("TYPE: ${type}")
            message?.let {
                append("MESSAGE: $it")
            }
            statusCode?.let {
                append("STATUS_CODE: $it")
            }
            cause?.let {
                append("CAUSE: $it")
            }

        }
    }
}

class ErrorModelBuilder(
    private val type: AppError,
) {
    var message: String? = null
    var statusCode: Int? = null
    var cause: Exception? = null

    fun build(): ErrorModel {
        return ErrorModel(
            message = message,
            statusCode = statusCode,
            cause = cause,
            type = type
        )
    }
}

fun errorModel(
    type: AppError,
    action: ErrorModelBuilder.() -> Unit = {},
): ErrorModel {
    val errorModel = ErrorModelBuilder(type).apply(action).build()
    AppLogger.errorModel(errorModel)
    return errorModel
}

sealed class AppException(
    val error: ErrorModel,
) : Exception(error.message) {


    class RemoteException(
        error: ErrorModel,
    ) : AppException(error)

    class LocalException(
        error: ErrorModel,
    ) : AppException(error)

    class CommonException(
        error: ErrorModel,
    ) : AppException(error)
}


suspend inline fun <reified T> handleLocalOperation(
    action: () -> T,
): T {
    return try {
        action()
    } catch (e: SQLiteException) {
        throw AppException.LocalException(
            errorModel(LocalError.UNKNOWN) {
                cause = e
                message = e.message
            },
        )
    } catch (e: CorruptionException) {
        throw AppException.LocalException(
            errorModel(LocalError.Corrupted) {
                cause = e
                message = e.message
            },
        )
    } catch (e: IOException) {
        throw AppException.LocalException(
            errorModel(LocalError.IO) {
                cause = e
                message = e.message
            },
        )
    }
}
