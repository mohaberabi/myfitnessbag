package com.myfitnessbag.order.features.home.presentation.viewmodel


sealed interface HomeActions {
    data object Refresh : HomeActions
    data object LoadMore : HomeActions
}