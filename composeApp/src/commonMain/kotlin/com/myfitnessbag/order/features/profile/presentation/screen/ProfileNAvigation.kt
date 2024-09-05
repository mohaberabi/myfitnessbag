package com.myfitnessbag.order.features.profile.presentation.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.myfitnessbag.order.features.account.presentation.goToAccountScreen
import com.myfitnessbag.order.features.layout.domain.LayoutRoutes


fun NavGraphBuilder.profileScreen(
    onShowSnackBar: (String) -> Unit,
    rootNavController: NavHostController,
) = composable<LayoutRoutes.Profile> {
    ProfileScreenRoot(
        onShowSnackBar = onShowSnackBar,
        onGoAccount = { rootNavController.goToAccountScreen() }
    )
}