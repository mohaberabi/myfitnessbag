package com.myfitnessbag.order.features.account.presentation.billing.viewmodel

import com.myfitnessbag.order.core.util.UiText

sealed interface BillingInfoEvents {


    data object Updated : BillingInfoEvents
    data class Error(val error: UiText) : BillingInfoEvents
}