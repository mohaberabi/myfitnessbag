package com.myfitnessbag.order.core.util


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.myfitnessbag.order.core.util.error.AppError
import com.myfitnessbag.order.core.util.error.CommonError
import com.myfitnessbag.order.core.util.error.ErrorModel
import com.myfitnessbag.order.core.util.error.LocalError
import com.myfitnessbag.order.core.util.error.RemoteError
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.conflict
import myfitnessbag.composeapp.generated.resources.disk_full
import myfitnessbag.composeapp.generated.resources.invalid_email
import myfitnessbag.composeapp.generated.resources.invalid_password
import myfitnessbag.composeapp.generated.resources.no_netowrk
import myfitnessbag.composeapp.generated.resources.payload_too_large
import myfitnessbag.composeapp.generated.resources.request_timeout
import myfitnessbag.composeapp.generated.resources.serialize_error
import myfitnessbag.composeapp.generated.resources.server_error
import myfitnessbag.composeapp.generated.resources.too_many_request
import myfitnessbag.composeapp.generated.resources.unAuthed
import myfitnessbag.composeapp.generated.resources.unknown_error

import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource


sealed class UiText {

    data object Empty : UiText()

    data class Dynamic(val value: String) : UiText()

    data class StringRes(val id: StringResource, val formatArgs: List<Any> = listOf()) : UiText()


    @Composable
    fun asString(): String {
        return when (this) {
            is Dynamic -> this.value
            Empty -> ""
            is StringRes -> stringResource(this.id)
        }
    }

    suspend fun getAsString(
    ): String {
        return when (this) {
            is Dynamic -> this.value
            Empty -> ""
            is StringRes -> getString(this.id, this.formatArgs.toTypedArray())
        }
    }

    @Composable
    fun AsText(
        modifier: Modifier = Modifier,
        style: TextStyle = TextStyle(),
    ) {
        val value = asString()
        Text(value, style = style, modifier = modifier)
    }


    val isEmpty: Boolean
        get() = this == Empty
}


fun AppError.asUiText(): UiText {
    val id = when (this) {

        is CommonError -> {
            when (this) {
                CommonError.IO_ERROR -> Res.string.unknown_error
                CommonError.UNKNOWN -> Res.string.unknown_error
            }
        }

        is RemoteError -> {
            when (this) {
                RemoteError.REQUEST_TIMEOUT -> Res.string.request_timeout
                RemoteError.UNAUTHORIZED -> Res.string.unAuthed
                RemoteError.CONFLICT -> Res.string.conflict
                RemoteError.TOO_MANY_REQUEST -> Res.string.too_many_request
                RemoteError.NO_NETWORK -> Res.string.no_netowrk
                RemoteError.PAYLOAD_TOO_LARGE -> Res.string.payload_too_large
                RemoteError.SERVER_ERROR -> Res.string.server_error
                RemoteError.SERIALIZATION_ERROR -> Res.string.serialize_error
                RemoteError.UNKNOWN_ERROR -> Res.string.unknown_error
                RemoteError.WRONG_EMAIL_USERNAME -> Res.string.invalid_email
                RemoteError.WRONG_PASSWORD -> Res.string.invalid_password
            }
        }

        is LocalError -> {
            when (this) {
                LocalError.DISK_FULL -> Res.string.disk_full
                LocalError.UNKNOWN -> Res.string.unknown_error
                LocalError.IO -> Res.string.disk_full
                LocalError.Corrupted -> Res.string.disk_full
            }
        }


        else -> Res.string.unknown_error
    }
    return UiText.StringRes(id)
}

