package com.myfitnessbag.order.features.layout.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.myfitnessbag.order.core.util.constants.AppDeepLinks
import com.myfitnessbag.order.features.cart.presentation.screen.cartScreen
import com.myfitnessbag.order.features.cart.presentation.screen.goToCartScreen
import com.myfitnessbag.order.features.home.presentation.screen.homeScreen
import com.myfitnessbag.order.features.layout.domain.LayoutItem
import com.myfitnessbag.order.features.layout.domain.LayoutRoutes
import com.myfitnessbag.order.features.orders.ordersScreen
import com.myfitnessbag.order.features.profile.presentation.screen.profileScreen
import com.myfitnessbag.order.features.search.presentation.screen.goToSearchScreen
import com.myfitnessbag.order.features.search.presentation.screen.searchScreen
import kotlinx.serialization.Serializable


@Serializable
data object LayoutRoute


@Composable
fun HomeLayoutNavHost(
    rootNavController: NavHostController,
    layoutNavController: NavHostController,
    onShowSnackBar: (String) -> Unit,
    modifier: Modifier = Modifier,
    startRoute: Any? = null
) {
    NavHost(
        modifier = modifier,
        navController = layoutNavController,
        startDestination = startRoute ?: LayoutRoutes.Home
    ) {
        homeScreen(
            rootNavController = rootNavController,
        )
        ordersScreen()
        profileScreen(
            onShowSnackBar = onShowSnackBar,
            rootNavController = rootNavController,
        )
    }

}

fun NavGraphBuilder.homeLayout(
    onShowSnackBar: (String) -> Unit,
    rootNavController: NavHostController,
) = composable<LayoutRoute>(
    deepLinks = listOf(
        navDeepLink {
            uriPattern = AppDeepLinks.DEFAULT
        }
    )
)
{
    val layoutNavController = rememberNavController()
    val entry by layoutNavController.currentBackStackEntryAsState()
    HomeLayoutRoot(
        currentBottomRoute = entry?.currentBottom() ?: LayoutItem.Home,
        onGoSearch = { rootNavController.goToSearchScreen() },
        onBottomNavigated = { layoutNavController.navigateBottom(it) },
        onCartClick = { rootNavController.goToCartScreen() }
    ) {
        HomeLayoutNavHost(
            modifier = Modifier.padding(it),
            rootNavController = rootNavController,
            onShowSnackBar = onShowSnackBar,
            layoutNavController = layoutNavController,
        )
    }

}

private fun NavBackStackEntry.currentBottom(): LayoutItem {
    val route = this.destination.route ?: return LayoutItem.Home
    return when {
        route.contains(LayoutRoutes.Profile.toString()) -> LayoutItem.Profile
        route.contains(LayoutRoutes.Cart.toString()) -> LayoutItem.Cart
        route.contains(LayoutRoutes.Orders.toString()) -> LayoutItem.Orders
        else -> LayoutItem.Home
    }
}

fun NavController.navigateToHomeLayout() {
    navigate(
        LayoutRoute,
    ) {
        popUpTo(LayoutRoute) {
            inclusive = true
        }
    }
}

fun NavController.navigateBottom(
    item: LayoutItem,
) =
    navigate(item.route) {
        graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
