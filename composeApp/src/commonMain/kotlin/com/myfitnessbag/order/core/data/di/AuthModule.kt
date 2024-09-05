package com.myfitnessbag.order.core.data.di

import com.myfitnessbag.order.core.data.source.remote.HttpClientFactory
import com.myfitnessbag.order.core.domain.model.AuthCredentials
import com.myfitnessbag.order.core.data.repository.DefaultAuthRepository
import com.myfitnessbag.order.core.data.source.local.persestence.AuthDataStore
import com.myfitnessbag.order.core.data.source.remote.KtorAuthRemoteDataSource
import com.myfitnessbag.order.core.domain.repository.AuthRepository
import com.myfitnessbag.order.core.domain.source.local.AuthLocalDataSource
import com.myfitnessbag.order.core.domain.source.remote.AuthRemoteDataSource
import com.myfitnessbag.order.core.domain.usecase.auth.AuthUseCases
import com.myfitnessbag.order.core.domain.usecase.auth.CheckAuthUseCase
import com.myfitnessbag.order.core.domain.usecase.auth.GetUserUseCase
import com.myfitnessbag.order.core.domain.usecase.auth.LoginUseCase
import com.myfitnessbag.order.core.domain.usecase.auth.RegisterUseCase
import com.myfitnessbag.order.features.auth.presentation.viewmodel.AuthViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val authModule = module {


    single<AuthRepository> {
        DefaultAuthRepository(
            get(),
            get(), get(), get(),
        )
    }


    single<AuthRemoteDataSource> {
        KtorAuthRemoteDataSource(
            httpClient = HttpClientFactory(
                engine = get(),
                credentials = AuthCredentials(),
            ).create(),
            dispatcher = get(),
        )
    }
    single<AuthLocalDataSource> {
        AuthDataStore(get())
    }

    single {
        CheckAuthUseCase(get())
    }
    single {
        GetUserUseCase(get())
    }
    single<AuthUseCases> {
        AuthUseCases(
            loginUseCase = LoginUseCase(get()),
            registerUseCase = RegisterUseCase(get()),
            checkAuthUserCase = get(),
            getUserUseCase = get()
        )


    }
    viewModelOf(::AuthViewModel)
}