package com.myfitnessbag.order.features.cart.presentation.viewmodel

import com.myfitnessbag.order.core.util.UiText


sealed interface CartEvents {


    data class Error(val error: UiText) : CartEvents
}