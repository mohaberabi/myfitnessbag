package com.myfitnessbag.order.features.cart.presentation.screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.myfitnessbag.order.features.layout.domain.LayoutItem
import com.myfitnessbag.order.features.layout.domain.LayoutRoutes
import com.myfitnessbag.order.features.search.presentation.screen.SearchScreenRoot


fun NavGraphBuilder.cartScreen(
    onShowSnackBar: (String) -> Unit,
    rootNavController: NavController,
) = composable<LayoutRoutes.Cart> {
    CartScreenRoot(
        onShowSnackBar = onShowSnackBar,
        onGoBack = { rootNavController.popBackStack() }
    )
}

fun NavController.goToCartScreen(
) = navigate(LayoutItem.Cart.route)