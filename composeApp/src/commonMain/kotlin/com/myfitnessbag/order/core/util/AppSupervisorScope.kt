package com.myfitnessbag.order.core.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


interface AppSupervisorScope {
    val superVisor: CoroutineScope
}


class DefaultSuperVisorScope(
    private val dispatchers: DispatchersProvider
) : AppSupervisorScope {
    override val superVisor: CoroutineScope
        get() = CoroutineScope(dispatchers.io + SupervisorJob())
}