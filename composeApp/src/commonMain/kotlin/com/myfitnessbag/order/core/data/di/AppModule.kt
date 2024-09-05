package com.myfitnessbag.order.core.data.di

import androidx.lifecycle.SavedStateHandle
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.myfitnessbag.order.app.presentation.viewmodel.MainAppViewModel
import com.myfitnessbag.order.core.data.repository.DefaultAppPrefsRepository
import com.myfitnessbag.order.core.data.repository.JwtTokenExtractorStrategy
import com.myfitnessbag.order.core.domain.repository.AppPrefsRepository
import com.myfitnessbag.order.core.domain.usecase.localization.ChangeLocalUseCase
import com.myfitnessbag.order.core.domain.usecase.localization.GetLocalUseCase
import com.myfitnessbag.order.core.domain.usecase.localization.LocalizationUseCases
import com.myfitnessbag.order.core.data.source.local.persestence.DataStorePersistenceClient
import com.myfitnessbag.order.core.data.source.remote.HttpClientFactory
import com.myfitnessbag.order.core.domain.model.AuthCredentials
import com.myfitnessbag.order.core.domain.repository.TokenExtractorStrategy
import com.myfitnessbag.order.core.domain.source.local.PersistenceClient
import com.myfitnessbag.order.core.domain.usecase.validator.ValidateEmailUseCase
import com.myfitnessbag.order.core.domain.usecase.validator.ValidatePasswordUseCase
import com.myfitnessbag.order.core.util.AppSupervisorScope
import com.myfitnessbag.order.core.util.DefaultSuperVisorScope
import com.myfitnessbag.order.core.util.DispatchersProvider
import com.myfitnessbag.order.core.util.MyFitnessBagDispatcherProvider
import com.myfitnessbag.order.features.layout.presentation.viewmodel.LayoutViewModel
import io.ktor.client.HttpClient
import okio.FileSystem
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {

    single {
        SavedStateHandle()
    }

    single<TokenExtractorStrategy> {
        JwtTokenExtractorStrategy()
    }

    single<AppSupervisorScope> {
        DefaultSuperVisorScope(get())
    }
    single {
        ValidateEmailUseCase()
    }
    single {
        ValidatePasswordUseCase()
    }
    single<HttpClient> {
        HttpClientFactory(
            engine = get(),
            credentials = AuthCredentials(),
        ).create()
    }
    single<DispatchersProvider> { MyFitnessBagDispatcherProvider() }
    single<DiskCache> {
        newDiskCache()
    }
    single<ImageLoader> {
        getAsyncImageLoader(get())
    }

    single<PersistenceClient> {
        DataStorePersistenceClient(get(), get())
    }


    single<AppPrefsRepository> {

        DefaultAppPrefsRepository(get(), get())
    }
    single {
        LocalizationUseCases(
            getLocalUseCase = GetLocalUseCase(get()),
            changeLocalUseCase = ChangeLocalUseCase(get())
        )
    }

    viewModelOf(::MainAppViewModel)
    viewModelOf(::LayoutViewModel)
}

private fun getAsyncImageLoader(
    context: PlatformContext,
) =
    ImageLoader.Builder(context).memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
        MemoryCache.Builder().maxSizePercent(context, 0.3).strongReferencesEnabled(true).build()
    }.diskCachePolicy(CachePolicy.ENABLED).networkCachePolicy(CachePolicy.ENABLED).diskCache {
        newDiskCache()
    }.crossfade(true).logger(DebugLogger()).build()

private fun newDiskCache(): DiskCache {
    return DiskCache.Builder().directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
        .maxSizeBytes(1024L * 1024 * 1024)
        .build()
}
