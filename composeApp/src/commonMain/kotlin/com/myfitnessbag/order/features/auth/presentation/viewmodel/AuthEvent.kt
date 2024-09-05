package com.myfitnessbag.order.features.auth.presentation.viewmodel

import com.myfitnessbag.order.core.util.UiText


sealed interface AuthEvent {


    data class Error(val error: UiText) : AuthEvent
    data object AuthDone : AuthEvent
}