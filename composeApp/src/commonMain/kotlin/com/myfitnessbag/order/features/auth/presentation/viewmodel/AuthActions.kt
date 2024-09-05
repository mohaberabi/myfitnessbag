package com.myfitnessbag.order.features.auth.presentation.viewmodel


sealed interface AuthActions {


    data object OnToggleAuthWay : AuthActions

    data object OnLoginClick : AuthActions

    data class OnNameChanged(val name: String) : AuthActions

    data class OnEmailChanged(val email: String) : AuthActions
    data class OnPasswordChanged(val password: String) : AuthActions

    data class OnLastNameChanged(val lastname: String) : AuthActions
    data object OnSkip : AuthActions

}