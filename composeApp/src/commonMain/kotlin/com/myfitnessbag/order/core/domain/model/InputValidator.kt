package com.myfitnessbag.order.core.domain.model

import com.myfitnessbag.order.core.util.UiText
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.invalid_email
import myfitnessbag.composeapp.generated.resources.invalid_password


data class InputValidator(
    val status: InputValidatorStatus = InputValidatorStatus.Initial,
    val reason: InputInvalidReason? = null,
)


enum class InputValidatorStatus {
    Initial,
    Valid,
    NOT_VALID;

    val isValid: Boolean
        get() = this == Valid

    val notValid: Boolean
        get() = this == NOT_VALID
}

enum class InputInvalidReason {
    EMPTY_FIELD,
    INVALID_EMAIL,
    WEAK_PASSWORD
}


fun InputInvalidReason.asUiText(): UiText {
    val message = when (this) {
        InputInvalidReason.EMPTY_FIELD -> Res.string.invalid_email
        InputInvalidReason.INVALID_EMAIL -> Res.string.invalid_email
        InputInvalidReason.WEAK_PASSWORD -> Res.string.invalid_password
    }
    return UiText.StringRes(message)
}