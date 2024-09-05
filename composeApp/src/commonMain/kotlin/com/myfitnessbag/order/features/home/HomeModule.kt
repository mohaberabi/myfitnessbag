package com.myfitnessbag.order.features.home

import com.myfitnessbag.order.core.data.source.remote.KtorCategoryRemoteDataSource
import com.myfitnessbag.order.core.data.repository.DefaultCategoryRepository
import com.myfitnessbag.order.core.domain.repository.CategoryRepository
import com.myfitnessbag.order.core.domain.source.remote.CategoryRemoteDataSource
import com.myfitnessbag.order.core.domain.usecase.category.GetCategoriesUseCase
import com.myfitnessbag.order.features.home.presentation.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val homeModule = module {

    single<CategoryRemoteDataSource> {
        KtorCategoryRemoteDataSource(get())
    }
    single<CategoryRepository> {
        DefaultCategoryRepository(get())
    }
    single {
        GetCategoriesUseCase(get())
    }
    viewModelOf(::HomeViewModel)
}