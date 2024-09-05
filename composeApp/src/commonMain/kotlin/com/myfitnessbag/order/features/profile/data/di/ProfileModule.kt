package com.myfitnessbag.order.features.profile.data.di

import com.myfitnessbag.order.features.profile.presentation.viewmodel.ProfileViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val profileModule = module {
    viewModelOf(::ProfileViewModel)
}