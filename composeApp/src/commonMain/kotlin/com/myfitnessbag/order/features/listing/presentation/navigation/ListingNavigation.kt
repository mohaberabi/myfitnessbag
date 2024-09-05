package com.myfitnessbag.order.features.listing.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.myfitnessbag.order.core.domain.model.CategoryModel
import com.myfitnessbag.order.core.util.extensions.decodeAsList
import com.myfitnessbag.order.core.util.extensions.encode
import com.myfitnessbag.order.features.cart.presentation.screen.goToCartScreen
import com.myfitnessbag.order.features.listing.presentation.screen.ListingScreenRoot
import com.myfitnessbag.order.features.product_details.presentation.navigation.goToProductDetailScreen
import kotlinx.serialization.Serializable


@Serializable
data class ListingRoute(
    val categoryIndex: Int = 0,
    val categoriesJson: String
)


fun NavGraphBuilder.listingScreen(
    rootNavController: NavController
) = composable<ListingRoute> {
    val route = it.toRoute<ListingRoute>()
    ListingScreenRoot(
        onGoBack = { rootNavController.popBackStack() },
        categoryIndex = route.categoryIndex,
        categories = route.categoriesJson.decodeAsList(),
        onProductClick = { id -> rootNavController.goToProductDetailScreen(id) },
        onCartClick = { rootNavController.goToCartScreen() }
    )
}


fun NavController.gotToListingScreen(
    categoryIndex: Int? = null,
    categories: List<CategoryModel>,
) = navigate(ListingRoute(categoryIndex ?: 0, categories.encode())) {
    restoreState = true
}



