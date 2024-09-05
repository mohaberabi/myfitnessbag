package com.myfitnessbag.order.core.domain.usecase.validator

import com.myfitnessbag.order.core.domain.model.InputInvalidReason
import com.myfitnessbag.order.core.domain.model.InputValidator
import com.myfitnessbag.order.core.domain.model.InputValidatorStatus

class ValidateEmailUseCase {
    companion object {
        private val emailRegex = Regex(
            pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        )
    }

    operator fun invoke(
        email: String,
    ): InputValidator {
        val reason: InputInvalidReason? = when {
            email.isBlank() -> InputInvalidReason.EMPTY_FIELD
            !emailRegex.matches(email) -> InputInvalidReason.INVALID_EMAIL
            else -> null
        }
        return if (reason != null) {
            InputValidator(
                reason = reason,
                status = InputValidatorStatus.NOT_VALID
            )
        } else {
            InputValidator(status = InputValidatorStatus.Valid)
        }

    }
}