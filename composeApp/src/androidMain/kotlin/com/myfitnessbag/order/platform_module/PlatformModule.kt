package com.myfitnessbag.order.platform_module

import coil3.PlatformContext
import com.myfitnessbag.order.core.data.source.local.database.AppDatabase
import com.myfitnessbag.order.core.util.PrefsDataStore
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module


actual val platformModule = module {
    single<AppDatabase> {

        createDatabase(get())
    }
    single<HttpClientEngine> {
        OkHttp.create()
    }
    single<PrefsDataStore> {

        createDataStore(get())
    }
    single<PlatformContext> { get() }

    single<LocalizationClient> {
        LocalizationClient(get())
    }
    single {
        UrlLauncher(get())
    }
}