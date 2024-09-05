package com.myfitnessbag.order.features.account.presentation.info.viewmodel

import com.myfitnessbag.order.core.domain.model.UserModel

data class AccountInfoState(
    val user: UserModel? = null,
)
