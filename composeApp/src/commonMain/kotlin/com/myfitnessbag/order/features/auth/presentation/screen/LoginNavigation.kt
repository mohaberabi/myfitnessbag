package com.myfitnessbag.order.features.auth.presentation.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.myfitnessbag.order.features.layout.presentation.navigateToHomeLayout
import kotlinx.serialization.Serializable


@Serializable
data object LoginRoute

fun NavGraphBuilder.loginScreen(
    onShowSnackBar: (String) -> Unit,
    rootNavController: NavHostController,
) = composable<LoginRoute> {
    LoginScreenRoot(
        onShowSnackBar = onShowSnackBar,
        onGoHomeScreen = {
            rootNavController.navigateToHomeLayout()
        },
        onGoBack = {},
    )
}