package com.myfitnessbag.order.features.account.presentation.shipping.viewmodel

import com.myfitnessbag.order.features.account.presentation.billing.viewmodel.BillingInfoActions


sealed interface ShippingInfoActions {

    data class NameChanged(val name: String) : ShippingInfoActions

    data class LastNameChanged(val name: String) : ShippingInfoActions

}