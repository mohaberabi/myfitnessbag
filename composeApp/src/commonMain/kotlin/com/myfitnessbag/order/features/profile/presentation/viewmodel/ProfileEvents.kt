package com.myfitnessbag.order.features.profile.presentation.viewmodel

import com.myfitnessbag.order.core.util.UiText

sealed interface ProfileEvents {


    data class Error(val error: UiText) : ProfileEvents


}