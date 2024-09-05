package com.myfitnessbag.order.features.search.presentation.screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.myfitnessbag.order.features.layout.domain.LayoutRoutes
import com.myfitnessbag.order.features.product_details.presentation.navigation.goToProductDetailScreen
import kotlinx.serialization.Serializable


@Serializable
data object SearchRoute

fun NavGraphBuilder.searchScreen(
    rootNavController: NavHostController,
) = composable<SearchRoute> {
    SearchScreenRoot(
        onGoBack = { rootNavController.popBackStack() },
        onProductClick = { id -> rootNavController.goToProductDetailScreen(id) }
    )
}


fun NavController.goToSearchScreen(
) = navigate(SearchRoute)