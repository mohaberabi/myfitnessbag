package com.myfitnessbag.order.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.myfitnessbag.order.features.account.presentation.accountInfoScreen
import com.myfitnessbag.order.features.account.presentation.accountScreen
import com.myfitnessbag.order.features.account.presentation.billingInfoScreen
import com.myfitnessbag.order.features.account.presentation.shippingInfoScreen
import com.myfitnessbag.order.features.auth.presentation.screen.LoginRoute
import com.myfitnessbag.order.features.auth.presentation.screen.loginScreen
import com.myfitnessbag.order.features.cart.presentation.screen.cartScreen
import com.myfitnessbag.order.features.layout.domain.LayoutItem
import com.myfitnessbag.order.features.layout.presentation.homeLayout
import com.myfitnessbag.order.features.listing.presentation.navigation.listingScreen
import com.myfitnessbag.order.features.product_details.presentation.navigation.productDetailScreen
import com.myfitnessbag.order.features.search.presentation.screen.searchScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startRoute: Any = LoginRoute,
    onShowSnackBar: (String) -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startRoute
    )
    {
        homeLayout(
            onShowSnackBar = onShowSnackBar,
            rootNavController = navController
        )

        loginScreen(
            rootNavController = navController,
            onShowSnackBar = onShowSnackBar,
        )
        accountScreen(
            rootNavController = navController,
        )

        billingInfoScreen(
            rootNavController = navController,
            onShowSnackBar = onShowSnackBar,
        )
        shippingInfoScreen(
            rootNavController = navController,
            onShowSnackBar = onShowSnackBar,
        )
        accountInfoScreen(
            rootNavController = navController,
            onShowSnackBar = onShowSnackBar,
        )
        searchScreen(
            rootNavController = navController,
        )
        listingScreen(
            rootNavController = navController,
        )
        productDetailScreen(
            rootNavController = navController,
            onShowSnackBar = onShowSnackBar
        )
        cartScreen(
            onShowSnackBar = onShowSnackBar,
            rootNavController = navController,
        )
    }


}

