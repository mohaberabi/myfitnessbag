package com.myfitnessbag.order.features.account.di

import com.myfitnessbag.order.features.account.presentation.billing.viewmodel.BillingInfoViewModel
import com.myfitnessbag.order.features.account.presentation.info.viewmodel.AccountInfoViewModel
import com.myfitnessbag.order.features.account.presentation.shipping.viewmodel.ShippingInfoViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val accountModule = module {
    viewModelOf(::ShippingInfoViewModel)
    viewModelOf(::AccountInfoViewModel)
    viewModelOf(::BillingInfoViewModel)
}