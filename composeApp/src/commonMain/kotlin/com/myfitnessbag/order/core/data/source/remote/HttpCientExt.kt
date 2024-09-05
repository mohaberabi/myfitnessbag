package com.myfitnessbag.order.core.data.source.remote


import com.myfitnessbag.order.core.data.source.remote.dto.RemoteErrorModel
import com.myfitnessbag.order.core.util.constants.AppConst
import com.myfitnessbag.order.core.util.error.AppError
import com.myfitnessbag.order.core.util.AppResult
import com.myfitnessbag.order.core.util.error.RemoteError
import com.myfitnessbag.order.core.util.constants.ErrorCodes
import com.myfitnessbag.order.core.util.error.AppException
import com.myfitnessbag.order.core.util.error.ErrorModel
import com.myfitnessbag.order.core.util.error.errorModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import kotlin.coroutines.cancellation.CancellationException


suspend inline fun <reified Response : Any> HttpClient.get(
    route: String,
    queryParams: Map<String, Any?> = mapOf()
): Response {

    return safeCall {
        get {
            url(constructRoute(route))
            queryParams.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

suspend inline fun <reified Response : Any> HttpClient.delete(
    route: String,
    queryParams: Map<String, Any?> = mapOf()
): Response {
    return safeCall {
        delete {
            url(constructRoute(route))
            queryParams.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

suspend inline fun <reified Request, reified Response : Any> HttpClient.post(
    route: String,
    body: Request
): Response {

    return safeCall {
        post {
            url(constructRoute(route))
            contentType(ContentType.Application.Json)
            setBody(body)
        }
    }
}

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse,
): T {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        throw AppException.RemoteException(errorModel(RemoteError.NO_NETWORK))
    } catch (e: SerializationException) {
        e.printStackTrace()
        throw AppException.RemoteException(errorModel(RemoteError.SERIALIZATION_ERROR))
    } catch (e: Exception) {
        if (e is CancellationException) throw e
        e.printStackTrace()
        throw AppException.RemoteException(errorModel(RemoteError.UNKNOWN_ERROR))

    }
    return mapResponseToAppResult(response)
}


suspend inline fun <reified T> mapResponseToAppResult(
    response: HttpResponse,
): T {
    return when (response.status.value) {
        in 200..299 -> response.body<T>()
        in 401..403 -> {
            val errorBody = try {
                response.body<RemoteErrorModel>()
            } catch (e: Exception) {
                null
            }
            val errorType = when (errorBody?.code) {
                ErrorCodes.WRONG_PASSWORD -> RemoteError.WRONG_PASSWORD
                ErrorCodes.WRONG_USERNAME -> RemoteError.WRONG_EMAIL_USERNAME
                else -> RemoteError.UNAUTHORIZED
            }
            throw AppException.RemoteException(
                errorModel(type = errorType) {
                    statusCode = response.status.value
                    message = errorBody?.message ?: "Unauthorized access"
                }
            )
        }

        else -> {
            val errorType = response.status.value.mapToErrorType()
            throw AppException.RemoteException(
                errorModel(type = errorType) {
                    statusCode = response.status.value
                    message = "Error occurred with status code: ${response.status.value}"
                }
            )
        }
    }
}


fun Int.mapToErrorType(): RemoteError {
    return when (this) {
        408 -> RemoteError.REQUEST_TIMEOUT
        409 -> RemoteError.CONFLICT
        413 -> RemoteError.CONFLICT
        429 -> RemoteError.CONFLICT
        in 500..599 -> RemoteError.CONFLICT
        else -> RemoteError.UNKNOWN_ERROR

    }
}

fun constructRoute(route: String): String {

    return when {
        route.contains(AppConst.BASE_URL) -> route
        route.startsWith("/") -> AppConst.BASE_URL + route
        else -> AppConst.BASE_URL + "/$route"

    }
}