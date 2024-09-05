package com.myfitnessbag.order.features.account.presentation.billing.viewmodel

sealed interface BillingInfoActions {


    data class NameChanged(val name: String) : BillingInfoActions

    data class LastNameChanged(val name: String) : BillingInfoActions

}