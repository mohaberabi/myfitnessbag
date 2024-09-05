package com.myfitnessbag.order.core.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

interface DispatchersProvider {
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
}


class MyFitnessBagDispatcherProvider : DispatchersProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}