package com.myfitnessbag.order.features.account.presentation.info.viewmodel

import com.myfitnessbag.order.core.util.UiText

sealed interface AccountInfoEvents {


    data object Updated : AccountInfoEvents
    data class Error(val error: UiText) : AccountInfoEvents
}