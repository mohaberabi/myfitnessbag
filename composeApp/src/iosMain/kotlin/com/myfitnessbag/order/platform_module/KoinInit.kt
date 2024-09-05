package com.myfitnessbag.order.platform_module

import org.koin.core.context.startKoin
import org.koin.core.module.Module


actual class KoinInit {
    actual fun init(
        vararg extraModules: Module,
    ) {
        startKoin {
            modules(
                *extraModules,
            )
        }
    }
}


