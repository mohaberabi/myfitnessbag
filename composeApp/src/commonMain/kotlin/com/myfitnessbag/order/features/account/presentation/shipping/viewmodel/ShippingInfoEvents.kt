package com.myfitnessbag.order.features.account.presentation.shipping.viewmodel

import com.myfitnessbag.order.core.util.UiText

sealed interface ShippingInfoEvents {
    data object Updated : ShippingInfoEvents
    data class Error(val error: UiText) : ShippingInfoEvents
}