package com.myfitnessbag.order.features.account.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.myfitnessbag.order.features.account.presentation.billing.screen.BillingInfoScreenRoot
import com.myfitnessbag.order.features.account.presentation.info.screen.AccountInfoScreenRoot
import com.myfitnessbag.order.features.account.presentation.shipping.screen.ShippingInfoScreenRoot
import kotlinx.serialization.Serializable


@Serializable
data object AccountRoute

@Serializable
data object AccountInfoRoute

@Serializable
data object BillingInfoRoute

@Serializable
data object ShippingInfoRoute

fun NavGraphBuilder.accountInfoScreen(
    rootNavController: NavHostController,
    onShowSnackBar: (String) -> Unit,
) = composable<AccountInfoRoute> {
    AccountInfoScreenRoot(
        onGoBack = { rootNavController.popBackStack() },
        onShowSnackBar = onShowSnackBar,
    )
}

fun NavGraphBuilder.shippingInfoScreen(
    rootNavController: NavHostController,
    onShowSnackBar: (String) -> Unit,
) = composable<ShippingInfoRoute> {
    ShippingInfoScreenRoot(
        onGoBack = { rootNavController.popBackStack() },
        onShowSnackBar = onShowSnackBar,
    )
}

fun NavGraphBuilder.billingInfoScreen(
    rootNavController: NavHostController,
    onShowSnackBar: (String) -> Unit,
) = composable<BillingInfoRoute> {
    BillingInfoScreenRoot(
        onGoBack = { rootNavController.popBackStack() },
        onShowSnackBar = onShowSnackBar,
    )
}

fun NavGraphBuilder.accountScreen(
    rootNavController: NavHostController,
) = composable<AccountRoute> {
    AccountScreenRoot(
        onGoAccount = { rootNavController.goToAccountInfo() },
        onGoShipping = { rootNavController.goToShippingRoute() },
        onBackClick = { rootNavController.popBackStack() },
        onGoBilling = { rootNavController.goToBillingInfo() },
    )
}

fun NavController.goToAccountInfo(
) = navigate(AccountInfoRoute)

fun NavController.goToAccountScreen(
) = navigate(AccountRoute)

fun NavController.goToBillingInfo(
) = navigate(BillingInfoRoute)

fun NavController.goToShippingRoute(
) = navigate(ShippingInfoRoute)