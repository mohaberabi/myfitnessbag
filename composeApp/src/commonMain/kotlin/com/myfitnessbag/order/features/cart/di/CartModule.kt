package com.myfitnessbag.order.features.cart.di

import com.myfitnessbag.order.core.data.repository.DefaultCartRepository
import com.myfitnessbag.order.core.data.source.local.RoomCartLocalDaaSource
import com.myfitnessbag.order.core.data.source.local.database.AppDatabase
import com.myfitnessbag.order.core.data.source.local.database.dao.CartItemDao
import com.myfitnessbag.order.core.domain.repository.CartRepository
import com.myfitnessbag.order.core.domain.source.local.CartLocalDataSource
import com.myfitnessbag.order.core.domain.usecase.cart.AddToCartUseCase
import com.myfitnessbag.order.core.domain.usecase.cart.CartUseCases
import com.myfitnessbag.order.core.domain.usecase.cart.GetAllCartItemsUseCase
import com.myfitnessbag.order.core.domain.usecase.cart.GetCartCountUseCase
import com.myfitnessbag.order.core.domain.usecase.cart.GetCartItemUseCase
import com.myfitnessbag.order.core.domain.usecase.cart.GetCartTotalUseCase
import com.myfitnessbag.order.core.domain.usecase.cart.RemoveCartItemUseCase
import com.myfitnessbag.order.core.domain.usecase.cart.UpdateCartItemUseCase
import com.myfitnessbag.order.features.cart.presentation.viewmodel.CartViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module


val cartModule: Module = module {


    single<CartItemDao> {
        get<AppDatabase>().cartDao()
    }
    single<CartLocalDataSource> {
        RoomCartLocalDaaSource(get(), get())
    }

    single<CartRepository> {
        DefaultCartRepository(get())
    }

    single {
        CartUseCases(
            getCartItemUseCase = GetCartItemUseCase(get()),
            getAllCartItemsUseCase = GetAllCartItemsUseCase((get())),
            addToCartUseCase = AddToCartUseCase(get()),
            removeCartItemUseCase = RemoveCartItemUseCase(get()),
            updateCartItemUseCase = UpdateCartItemUseCase(get()),
            getCartCountUseCase = GetCartCountUseCase(get()),
            getCartTotalUseCase = GetCartTotalUseCase(get())
        )
    }
    viewModelOf(::CartViewModel)
}