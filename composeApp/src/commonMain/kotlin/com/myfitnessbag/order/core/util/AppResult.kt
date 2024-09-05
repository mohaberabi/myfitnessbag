package com.myfitnessbag.order.core.util

import com.myfitnessbag.order.core.util.error.AppError
import com.myfitnessbag.order.core.util.error.AppException
import com.myfitnessbag.order.core.util.error.CommonError
import com.myfitnessbag.order.core.util.error.ErrorModel
import com.myfitnessbag.order.core.util.error.errorModel
import io.ktor.utils.io.CancellationException


sealed interface AppResult<out D, out E : AppError> {
    data class Done<out D>(val data: D) : AppResult<D, Nothing>
    data class Error<out E : AppError>(val error: E) : AppResult<Nothing, E>

    companion object {
        suspend inline fun <D> handle(
            call: () -> D,
        ): AppResult<D, ErrorModel> {
            return try {
                Done(call())
            } catch (e: AppException) {
                Error(e.error)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Error(errorModel(CommonError.UNKNOWN) {
                    message = e.message
                    cause = e
                })
            }
        }
    }

}


inline fun <T, E : AppError, R> AppResult<T, E>.map(map: (T) -> R): AppResult<R, E> {
    return when (this) {
        is AppResult.Error -> AppResult.Error(error)
        is AppResult.Done -> AppResult.Done(map(data))
    }
}

fun <T, E : AppError> AppResult<T, E>.asEmptyResult(): EmptyDataResult<E> {
    return map { }
}
typealias EmptyDataResult<E> = AppResult<Unit, E>

inline fun <T, E : AppError> AppResult<T, E>.onSuccess(
    action: (T) -> Unit,
): AppResult<T, E> {
    if (this is AppResult.Done) {
        action(data)
    }
    return this
}

inline fun <T, E : AppError> AppResult<T, E>.onFailure(
    action: (E) -> Unit,
): AppResult<T, E> {
    if (this is AppResult.Error) {
        action(error)
    }
    return this
}

inline fun <T, E : AppError, R, F : AppError> AppResult<T, E>.foldResult(
    onFailure: (E) -> AppResult<R, F>,
    onSuccess: (T) -> AppResult<R, F>,
): AppResult<R, F> {
    return when (this) {
        is AppResult.Done -> onSuccess(data)
        is AppResult.Error -> onFailure(error)
    }
}

