package com.myfitnessbag.order.features.profile.presentation.viewmodel

sealed interface ProfileActions {
    data object ChangeLocal : ProfileActions
}