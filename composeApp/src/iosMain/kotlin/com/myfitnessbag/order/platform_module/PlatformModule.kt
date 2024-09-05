package com.myfitnessbag.order.platform_module

import coil3.PlatformContext
import com.myfitnessbag.order.core.data.source.local.database.AppDatabase
import com.myfitnessbag.order.core.util.PrefsDataStore
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module


actual val platformModule = module {
    single { PlatformContext.INSTANCE }
    single<PrefsDataStore> {
        createDataStore()
    }
    single<AppDatabase> { createDatabase() }

    single {
        LocalizationClient()
    }
    single<HttpClientEngine> {
        Darwin.create()
    }
    single {
        UrlLauncher()
    }
}