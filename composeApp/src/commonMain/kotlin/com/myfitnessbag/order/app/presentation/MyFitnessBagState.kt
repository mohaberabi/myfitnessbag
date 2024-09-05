package com.myfitnessbag.order.app.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope


data class MyFitnessBagState(
    val scope: CoroutineScope,
    val rootNavController: NavHostController,
    val snackBarState: SnackbarHostState,
)


@Composable
fun rememberMyFitnessBagState(
): MyFitnessBagState {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    return remember {
        MyFitnessBagState(
            scope = scope,
            rootNavController = navController,
            snackBarState = snackBarHostState
        )
    }
}