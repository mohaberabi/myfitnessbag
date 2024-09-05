package com.myfitnessbag.order.features.orders

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.myfitnessbag.order.features.layout.domain.LayoutRoutes


fun NavGraphBuilder.ordersScreen(
) = composable<LayoutRoutes.Orders> {
    OrdersScreenRoot()
}