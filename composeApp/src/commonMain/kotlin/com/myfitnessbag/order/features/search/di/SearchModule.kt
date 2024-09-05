package com.myfitnessbag.order.features.search.di

import com.myfitnessbag.order.core.data.repository.DefaultProductRepository
import com.myfitnessbag.order.core.data.source.remote.KtorProductsRemoteDataSource
import com.myfitnessbag.order.core.domain.repository.ProductRepository
import com.myfitnessbag.order.core.domain.source.remote.ProductRemoteDataSource
import com.myfitnessbag.order.core.domain.usecase.product.SearchProductUseCase
import com.myfitnessbag.order.features.search.presentation.viewmodel.SearchViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val searchModule = module {


    single<ProductRemoteDataSource> {
        KtorProductsRemoteDataSource(get(), get())
    }
    single<ProductRepository> {
        DefaultProductRepository(get())
    }
    single {
        SearchProductUseCase(get())
    }
    viewModelOf(::SearchViewModel)
}