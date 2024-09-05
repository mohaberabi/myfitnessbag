package com.myfitnessbag.order.app.presentation.viewmodel


data class MainAppState(
    val local: String = "",
    val didLogIn: Boolean = false,
    val loadedData: Boolean = false,
)