package com.myfitnessbag.order.features.product_details.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.myfitnessbag.order.features.listing.presentation.navigation.ListingRoute
import com.myfitnessbag.order.features.product_details.presentation.screen.ProductDetailsScreenRoot
import kotlinx.serialization.Serializable


@Serializable
data class ProductDetailRoute(
    val productId: Int,
)


fun NavGraphBuilder.productDetailScreen(
    rootNavController: NavController,
    onShowSnackBar: (String) -> Unit,
) = composable<ProductDetailRoute> {
    val route = it.toRoute<ProductDetailRoute>()
    ProductDetailsScreenRoot(
        productId = route.productId,
        onBack = { rootNavController.popBackStack() },
        onProductClick = { id -> rootNavController.goToProductDetailScreen(id) },
        onShowSnackBar = onShowSnackBar
    )
}

fun NavController.goToProductDetailScreen(
    id: Int
) = navigate(ProductDetailRoute(id)) {
    popUpTo<ListingRoute>() {
        saveState = true
    }
}