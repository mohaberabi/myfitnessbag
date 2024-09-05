package com.myfitnessbag.order.features.account.presentation.info.viewmodel

sealed interface AccountInfoActions {


    data class NameChanged(val name: String) : AccountInfoActions

    data class LastNameChanged(val name: String) : AccountInfoActions

}