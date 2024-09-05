package com.myfitnessbag.order.features.home.presentation.screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.myfitnessbag.order.features.layout.domain.LayoutRoutes
import com.myfitnessbag.order.features.listing.presentation.navigation.gotToListingScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.homeScreen(
    rootNavController: NavController
) = composable<LayoutRoutes.Home> {
    HomeScreenRoot(
        onCategoryClick = { index, cats ->
            rootNavController.gotToListingScreen(
                index,
                cats
            )
        },
    )
}