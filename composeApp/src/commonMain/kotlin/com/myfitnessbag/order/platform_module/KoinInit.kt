package com.myfitnessbag.order.platform_module

import com.myfitnessbag.order.core.data.di.appModule
import com.myfitnessbag.order.core.data.di.authModule
import com.myfitnessbag.order.features.cart.di.cartModule
import com.myfitnessbag.order.features.account.di.accountModule
import com.myfitnessbag.order.features.home.homeModule
import com.myfitnessbag.order.features.listing.presentation.di.listingModule
import com.myfitnessbag.order.features.product_details.di.productDetailModule
import com.myfitnessbag.order.features.profile.data.di.profileModule
import com.myfitnessbag.order.features.search.di.searchModule
import org.koin.core.module.Module

expect class KoinInit {
    fun init(
        vararg extraModules: Module = arrayOf(
            platformModule,
            appModule,
            homeModule,
            profileModule,
            authModule,
            accountModule,
            searchModule,
            listingModule,
            productDetailModule,
            cartModule

        )
    )
}

