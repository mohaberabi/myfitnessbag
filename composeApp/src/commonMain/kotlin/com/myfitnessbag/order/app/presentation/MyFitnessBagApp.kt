package com.myfitnessbag.order.app.presentation

import AppTheme
import androidx.compose.runtime.*
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import com.myfitnessbag.order.app.presentation.navigation.AppNavHost
import com.myfitnessbag.order.app.presentation.viewmodel.MainAppState
import com.myfitnessbag.order.app.presentation.viewmodel.MainAppViewModel
import com.myfitnessbag.order.core.domain.model.AppLocales
import com.myfitnessbag.order.core.presentation.compose.AppScaffold
import com.myfitnessbag.order.core.util.constants.AppShortcuts
import com.myfitnessbag.order.features.auth.presentation.screen.LoginRoute
import com.myfitnessbag.order.features.layout.domain.LayoutItem
import com.myfitnessbag.order.features.layout.presentation.LayoutRoute
import kotlinx.coroutines.launch
import org.koin.compose.currentKoinScope
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


val LocalAppLocalization = staticCompositionLocalOf { AppLocales.En.code }

@OptIn(
    ExperimentalCoilApi::class,
    KoinExperimentalAPI::class,
)
@Composable
fun MyFitnessBagApp(
    viewModel: MainAppViewModel = koinViewModel(),
) {
    val koinScope = currentKoinScope()
    setSingletonImageLoaderFactory {
        koinScope.get<ImageLoader>()
    }
    val myFitnessBagState = rememberMyFitnessBagState()

    val mainAppState by viewModel.state.collectAsState()

    MyFitnessBagAppBody(
        myFitnessBagState = myFitnessBagState,
        mainAppState = mainAppState,
    )
}


@Composable
fun MyFitnessBagAppBody(
    myFitnessBagState: MyFitnessBagState,
    mainAppState: MainAppState,
) {


    if (mainAppState.loadedData) {
        CompositionLocalProvider(
            LocalAppLocalization provides mainAppState.local,
        ) {
            AppTheme(
                local = mainAppState.local,
            ) {
                AppScaffold(
                    snackBarHostState = myFitnessBagState.snackBarState,
                ) {
                    AppNavHost(
                        navController = myFitnessBagState.rootNavController,
                        startRoute = if (mainAppState.didLogIn) LayoutRoute else LoginRoute,
                        onShowSnackBar = { message ->
                            myFitnessBagState.scope.launch {
                                myFitnessBagState.snackBarState.showSnackbar(message)
                            }
                        }
                    )
                }
            }
        }
    }


}
