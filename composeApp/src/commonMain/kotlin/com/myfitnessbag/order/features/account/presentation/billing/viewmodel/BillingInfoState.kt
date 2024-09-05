package com.myfitnessbag.order.features.account.presentation.billing.viewmodel

import com.myfitnessbag.order.core.domain.model.UserModel

data class BillingInfoState(
    val user: UserModel? = null,
)
