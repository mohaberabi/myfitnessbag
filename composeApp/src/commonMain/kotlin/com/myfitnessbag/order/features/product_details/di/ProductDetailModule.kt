package com.myfitnessbag.order.features.product_details.di

import com.myfitnessbag.order.features.product_details.data.repository.DefaultProductDetailRepository
import com.myfitnessbag.order.features.product_details.data.source.remote.KtorProductDetailRemoteDataSource
import com.myfitnessbag.order.features.product_details.domain.repository.ProductDetailRepository
import com.myfitnessbag.order.features.product_details.domain.source.remote.ProductDetailRemoteDataSource
import com.myfitnessbag.order.features.product_details.domain.usecase.GetProductDetailUseCase
import com.myfitnessbag.order.features.product_details.domain.usecase.GetRelatedProductsUSeCase
import com.myfitnessbag.order.features.product_details.presentation.viewmodel.ProductDetailViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val productDetailModule = module {


    single {
        GetRelatedProductsUSeCase(get())
    }
    single {
        GetProductDetailUseCase(get())
    }


    single<ProductDetailRemoteDataSource> {
        KtorProductDetailRemoteDataSource(get(), get())
    }
    single<ProductDetailRepository> {
        DefaultProductDetailRepository(get())
    }
    viewModelOf(::ProductDetailViewModel)
}