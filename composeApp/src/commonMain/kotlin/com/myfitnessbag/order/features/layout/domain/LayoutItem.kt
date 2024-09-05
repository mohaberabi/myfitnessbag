package com.myfitnessbag.order.features.layout.domain

import kotlinx.serialization.Serializable
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.account
import myfitnessbag.composeapp.generated.resources.cart
import myfitnessbag.composeapp.generated.resources.heart
import myfitnessbag.composeapp.generated.resources.home
import myfitnessbag.composeapp.generated.resources.orders
import myfitnessbag.composeapp.generated.resources.receipt
import myfitnessbag.composeapp.generated.resources.search
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Serializable

sealed interface LayoutRoutes {
    @Serializable
    data object Home : LayoutRoutes

    @Serializable
    data object Orders : LayoutRoutes

    @Serializable
    data object Cart : LayoutRoutes

    @Serializable
    data object Profile : LayoutRoutes
}


enum class LayoutItem(
    val route: LayoutRoutes,
    val label: StringResource,
    val icon: DrawableResource,
) {

    Home(route = LayoutRoutes.Home, Res.string.cart, Res.drawable.home),
    Orders(route = LayoutRoutes.Orders, Res.string.orders, Res.drawable.receipt),
    Cart(route = LayoutRoutes.Cart, Res.string.cart, Res.drawable.cart),
    Profile(route = LayoutRoutes.Profile, Res.string.account, Res.drawable.account)

}