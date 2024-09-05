package com.myfitnessbag.order.features.listing.presentation.di

import com.myfitnessbag.order.core.domain.usecase.product.GetProductsUseCase
import com.myfitnessbag.order.features.listing.presentation.viewmodel.ListingViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val listingModule = module {


    single {
        GetProductsUseCase(get())
    }

    
    viewModelOf(::ListingViewModel)
}